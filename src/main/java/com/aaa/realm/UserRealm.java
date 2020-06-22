package com.aaa.realm;

import com.aaa.entity.User;
import com.aaa.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //强制转换从前端获取到的token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从token中获取用户名
        String username = token.getUsername();
        //根据token获取到的用户名，到数据库中查询该的用户的信息
        User dbUser = userService.findByName(username);
        //判断这个用户信息是否为空
        if (dbUser==null){
            throw new UnknownAccountException("该用户"+dbUser.getUserName()+"不存在");
        }
        /**
         * dbUser.getUserName() 从数据库中拿到的用户名
         * dbUser.getPassWord() 从数据库中拿到的密码（加密后的密码）
         * ByteSource.Util.bytes(dbUser.getUserName()) 当初存入数据库中对密码进行加密用到的盐值
         * getName()    获取当前realm的名字 提供的默认方法
         */
        //根据传入的token，返回一个在数据库中查询后的身份信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(dbUser.getUserName(),dbUser.getPassWord(),ByteSource.Util.bytes(dbUser.getUserName()),this.getName());
        return info;
    }

    /**
     * 获取用户对应的权限信息
     * @param principalCollection 身份集合：可以通过该对象获取认证后的用户信息
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //getPrimaryPrincipal:获取主身份信息，获取用户名
        String userName = principalCollection.getPrimaryPrincipal().toString();

        //根据用户名查询得到该用户对应的角色列表
        Set<String> dbRoles = userService.findByRoleName(userName);
        System.out.println("该用户拥有的权限共："+dbRoles.size()+"条");
        // //根据用户名查询得到该用户对应的资源列表

        //权限集合：绑定菜单列表
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //绑定用户对应的角色列表
        info.setRoles(dbRoles);

        //绑定用户对应的权限列表
//         info.setStringPermissions(dbRoles);
        return info;
    }
}
