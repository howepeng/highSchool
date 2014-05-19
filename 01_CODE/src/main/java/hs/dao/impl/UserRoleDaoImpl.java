package hs.dao.impl;

import org.springframework.stereotype.Repository;

import hs.dao.UserRoleDaoI;
import hs.model.TbUserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<TbUserRole> implements UserRoleDaoI {

}
