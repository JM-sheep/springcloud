package com.aaa.dao;

import com.aaa.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface UserDao {
    @Select("select * from t_user where username=#{userName}")
    User findByName(String userName);

    @Select("select r.rolename from t_user u,t_role r,t_user_role ur where u.id=ur.userid and r.id=ur.roleid and u.username=#{userName}")
    List<String> findByRoleName(String userName);

    @Select("select m.menuname from t_user u,t_role r,t_user_role ur,t_menu m,t_menu_role mr where u.id=ur.userid and r.id=ur.roleid and r.id=mr.roleid and m.id=mr.menuid and u.username=#{userName}")
    List<String> findByMenuName(String userName);
}
