package hs.service.impl;

import hs.dao.RoleDaoI;
import hs.dao.UserDaoI;
import hs.dao.UserRoleDaoI;
import hs.model.TbRole;
import hs.model.TbUser;
import hs.model.TbUserRole;
import hs.pageModel.Combobox;
import hs.pageModel.DataGrid;
import hs.pageModel.User;
import hs.service.UserServiceI;
import hs.util.MD5Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

    private UserDaoI userDao;
    private RoleDaoI roleDao;
    private UserRoleDaoI userRoleDao;

    @Autowired
    public void seTbUserDao(UserDaoI userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDaoI roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setUserRoleDao(UserRoleDaoI userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public List<Combobox> combox() {
        List<Combobox> rl = new ArrayList<Combobox>();
        List<TbUser> l = userDao.find("from TbUser");
        if (l != null && l.size() > 0) {
            for (TbUser t : l) {
                Combobox r = new Combobox();
                r.setId(t.getId());
                r.setText(t.getName());
                rl.add(r);
            }
        }
        return rl;
    }
    @Override
    public User saveRegisterMessage(User user) {
        TbUser tbUser = new TbUser();
        BeanUtils.copyProperties(user, tbUser, new String[] { "password" });
        tbUser.setPassword(MD5Util.md5(user.getPassword()));
        tbUser.setId(UUID.randomUUID().toString());
        tbUser.setCreatedatetime(new Date());
        userDao.save(tbUser);
        BeanUtils.copyProperties(tbUser, user);
        this.saveRoleUser(user, tbUser);
        return user;
    }

    private void saveRoleUser(User user, TbUser tbUser) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("user", tbUser);
        userDao.excuteHQL("DELETE TbUserRole t WHERE t.tbUser= :user", m);
        if(user.getRoleIds()!=null){
            for(String id : user.getRoleIds().split(",")){

                TbUserRole tbUserRole = new TbUserRole();
                tbUserRole.setId(UUID.randomUUID().toString());
                tbUserRole.setTbRole(roleDao.getById(TbRole.class, (Serializable)id.trim()));
                tbUserRole.setTbUser(tbUser);
                userRoleDao.save(tbUserRole);
            }
        }

    }

    @Override
    public User login(User user) {
        TbUser TbUser = new TbUser();
        user.setPassword(MD5Util.md5(user.getPassword()));
        BeanUtils.copyProperties(user, TbUser);
        List<TbUser> reslut = userDao.findByExample(TbUser);
        StringBuilder rids = new StringBuilder();
        if (reslut != null && reslut.size() > 0) {
            BeanUtils.copyProperties(reslut.get(0), user);
            if(reslut.get(0).getTbUserRoles() != null) {
                for (TbUserRole item : reslut.get(0).getTbUserRoles()) {
                    TbRole tbRole = item.getTbRole();
                    rids.append(tbRole.getId()+",");
                }
            }
            user.setRoleIds(rids.toString());
            return user;
        } else {
            return null;
        }
    }

    @Override
    public DataGrid getUserList(User user) {
        DataGrid d = new DataGrid();
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = addCondition(user, params);
        String totalhql = "SELECT count(*)" + hql;
        List<TbUser> l = userDao.find(hql, params, user.getPage(), user.getRows());
        List<User> nl = new ArrayList<User>();
        changeModel(l, nl);
        d.setRows(nl);
        d.setTotal(userDao.count(totalhql, params));
        return d;
    }

    /**
     * 将数据库模型转换成页面模型
     *
     * @param l
     * @param nl
     */
    private void changeModel(List<TbUser> l, List<User> nl) {
        if (l != null && l.size() > 0) {
            for (TbUser t : l) {
                User u = new User();
                BeanUtils.copyProperties(t, u);
                Set<TbUserRole> tbUserRoles = t.getTbUserRoles();
                String roleIds = "";
                String roleNames = "";
                boolean b = false;
                if (tbUserRoles != null && tbUserRoles.size() > 0) {
                    for (TbUserRole tbUserRole : tbUserRoles) {
                        if (tbUserRole.getTbRole() != null) {
                            if (b) {
                                roleIds += ",";
                                roleNames += ",";
                            }
                            roleIds += tbUserRole.getTbRole().getId();
                            roleNames += tbUserRole.getTbRole().getName();
                            b = true;

                        }
                    }
                }
                u.setRoleIds(roleIds);
                u.setRoleNames(roleNames);
                nl.add(u);
            }
        }
    }

    /**
     * 生成查询hql语句
     *
     * @param user
     * @param params
     * @return
     */
    private String addCondition(User user, Map<String, Object> params) {
        String hql = "FROM TbUser t WHERE 1=1";
        if (user.getUsername() != null && !user.getUsername().trim().equals("")) {
            hql += " AND t.username LIKE :username";
            params.put("username", "%%" + user.getUsername() + "%%");
        }
        if (user.getCreatedatetimeStart() != null) {
            hql += " AND t.createdatetime>= :createdatetimeStart ";
            params.put("createdatetimeStart", user.getCreatedatetimeStart());
        }
        if (user.getCreatedatetimeEnd() != null) {
            hql += " AND t.createdatetime<= :createdatetimeEnd ";
            params.put("createdatetimeEnd", user.getCreatedatetimeEnd());
        }
        if (user.getModifydatetimeStart() != null) {
            hql += " AND t.modifydatetime>= :modifydatetimeStart ";
            params.put("modifydatetimeStart", user.getModifydatetimeStart());
        }
        if (user.getModifydatetimeEnd() != null) {
            hql += " AND t.modifydatetime<= :modifydatetimeEnd ";
            params.put("modifydatetimeEnd", user.getModifydatetimeEnd());
        }
        if (user.getSort() != null) {
            hql += " ORDER BY " + user.getSort() + " " + user.getOrder();
        }
        return hql;
    }

    @Override
    public void removeUser(String ids) {
        String hql = "delete TbUser t where t.id in(";
        String[] nids = ids.split(",");
        for (int i = 0; i < nids.length; i++) {
            if (i > 0) {
                hql += ",";
            }
            hql += "'" + nids[i] + "'";
        }
        hql += ")";
        userDao.excuteHQL(hql);
    }

    @Override
    public void editUser(User user) {
        TbUser tbUser = userDao.getById(TbUser.class, user.getId());
        BeanUtils.copyProperties(user, tbUser, new String[] { "password" });
        if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
            tbUser.setPassword(MD5Util.md5(user.getPassword()));
        }
        saveRoleUser(user, tbUser);
    }

    @Override
    public void editUserPwd(User user) {
        TbUser tbUser = userDao.getById(TbUser.class, user.getId());
        tbUser.setPassword(MD5Util.md5(user.getPassword()));
    }
}
