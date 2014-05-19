package hs.service.impl;

import hs.comparator.MenuComparator;
import hs.dao.MenuDaoI;
import hs.model.TbMenu;
import hs.pageModel.Menu;
import hs.pageModel.SessionInfo;
import hs.pageModel.TreeNode;
import hs.service.MenuServiceI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {

    private MenuDaoI menuDao;

    @Autowired
    public void setMenuDao(MenuDaoI menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public List<TreeNode> getTreeNode(Menu menu) {
        Map<String, Object> param = new HashMap<String, Object>();
        String hql = "from TbMenu t where t.tbMenu is null order by t.seq";
        if (menu != null && menu.getId() != null && !menu.getId().trim().equals("")) {
            hql = "from TbMenu t where t.tbMenu.id = :id order by t.seq";
            param.put("id", menu.getId());
        }
        List<TbMenu> l = menuDao.find(hql, param);
        List<TreeNode> tree = new ArrayList<TreeNode>();
        for (TbMenu t : l) {
            tree.add(this.tree(t));
        }
        return tree;
    }

    private TreeNode tree(TbMenu t) {
        TreeNode node = new TreeNode();
        node.setId(t.getId());
        node.setText(t.getText());
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("url", t.getUrl());
        node.setAttributes(attributes);
        if (t.getIconCls() != null) {
            node.setIconCls(t.getIconCls());
        } else {
            node.setIconCls("");
        }
        if (t.getTbMenus() != null && t.getTbMenus().size() > 0) {
            List<TbMenu> l = new ArrayList<TbMenu>(t.getTbMenus());
            Collections.sort(l, new MenuComparator());// 排序
            List<TreeNode> children = new ArrayList<TreeNode>();
            for (TbMenu r : l) {
                TreeNode tn = tree(r);
                children.add(tn);
            }
            node.setChildren(children);
            node.setState("open");
        }
        return node;
    }

    @Override
    public List<Menu> getAllTreeNode(SessionInfo sessionInfo) {

        List<Menu> nl = new ArrayList<Menu>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("uid", sessionInfo.getId());
        List<TbMenu> l = menuDao.find("FROM TbMenu t1 WHERE t1.id in (SELECT t2.tbMenu.id FROM TbRoleMenu t2 WHERE t2.tbRole.id IN (SELECT t3.tbRole.id FROM TbUserRole t3 WHERE t3.tbUser.id= :uid)) order by t1.seq", map);
        if (l != null && l.size() > 0) {
            for (TbMenu t : l) {
                Menu m = new Menu();
                BeanUtils.copyProperties(t, m);
                Map<String, Object> attributes = new HashMap<String, Object>();
                attributes.put("url", t.getUrl());
                m.setAttributes(attributes);
                m.setPid(t.getTbMenu() == null ? null : t.getTbMenu().getId());
                nl.add(m);
            }
        }
        return nl;
    }

    @Override
    public List<Menu> treegrid(Menu menu) {
        List<TbMenu> l;
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("id", menu.getId());
        if (menu != null && menu.getId() != null) {
            l = menuDao.find("FROM TbMenu t WHERE t.tbMenu.id = :id ORDER BY t.seq", m);
        } else {
            l = menuDao.find("FROM TbMenu t WHERE t.tbMenu IS NULL ORDER BY t.seq");
        }
        return changeModel(l);
    }

    private List<Menu> changeModel(List<TbMenu> TbMenus) {
        List<Menu> l = new ArrayList<Menu>();
        if (TbMenus != null && TbMenus.size() > 0) {
            for (TbMenu t : TbMenus) {
                Menu m = new Menu();
                BeanUtils.copyProperties(t, m);
                if (t.getTbMenu() != null) {
                    m.setPid(t.getTbMenu().getId());
                    m.setPtext(t.getTbMenu().getText());
                }
                if (countChildren(t.getId()) > 0) {
                    m.setState("closed");
                }
                l.add(m);
            }
        }
        return l;
    }

    /**
     * 统计有多少子节点
     */
    private Long countChildren(String id) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("id", id);
        return menuDao.count("SELECT count(*) FROM TbMenu t WHERE t.tbMenu.id = :id", m);
    }

    @Override
    public void add(Menu menu) {
        TbMenu t = new TbMenu();
        BeanUtils.copyProperties(menu, t);
        t.setId(UUID.randomUUID().toString());
        if (menu.getPid() != null) {
            t.setTbMenu(menuDao.getById(TbMenu.class, menu.getPid()));
        }
        menuDao.save(t);

    }

    @Override
    public void edit(Menu menu) {
        TbMenu t = menuDao.getById(TbMenu.class, menu.getId());
        BeanUtils.copyProperties(menu, t);
        if (menu.getPid() != null && !menu.getPid().equals(menu.getId())) {
            TbMenu pt = menuDao.getById(TbMenu.class, menu.getPid());
            if (pt != null) {
                if (isDown(t, pt)) {// 要将当前节点修改到当前节点的子节点中
                    Set<TbMenu> tbMenus = t.getTbMenus();// 当前要修改的节点的所有下级节点
                    if (tbMenus != null && tbMenus.size() > 0) {
                        for (TbMenu tmenu : tbMenus) {
                            if (tmenu != null) {
                                tmenu.setTbMenu(null);
                            }
                        }
                    }
                }
                t.setTbMenu(pt);
            }

        }

    }

    @Override
    public void remove(String id) {
        TbMenu t = menuDao.getById(TbMenu.class, id);
        if (t != null) {
            Set<TbMenu> menus = t.getTbMenus();
            if (menus != null && !menus.isEmpty()) {
                // 说明当前菜单下面还有子菜单
                for (TbMenu tbMenu : menus) {
                    remove(tbMenu.getId());
                }
            }
            menuDao.delete(t);
        }
    }

    /**
     * 判断是否是将当前节点修改到当前节点的子节点
     *
     * @param t
     * @param pt
     * @return
     */
    private boolean isDown(TbMenu t, TbMenu pt) {
        if (pt.getTbMenu() != null) {
            if (pt.getTbMenu().getId().equals(t.getId())) {
                return true;
            } else {
                return isDown(t, pt.getTbMenu());
            }
        }
        return false;
    }

}
