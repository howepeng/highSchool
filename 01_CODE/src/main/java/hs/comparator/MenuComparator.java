package hs.comparator;

import hs.model.TbMenu;

import java.util.Comparator;

/**
 * 菜单排序
 * 
 */
public class MenuComparator implements Comparator<TbMenu> {

	public int compare(TbMenu o1, TbMenu o2) {
		int i1 = o1.getSeq() != null ? o1.getSeq().intValue() : -1;
		int i2 = o2.getSeq() != null ? o2.getSeq().intValue() : -1;
		return i1 - i2;
	}

}
