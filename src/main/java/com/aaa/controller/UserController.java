package com.aaa.controller;

import com.aaa.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(User user){
        Subject currentUser = SecurityUtils.getSubject();
        //当用户还没有认证/登录
        if (!currentUser.isAuthenticated()) {
            //用户密码信息
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassWord());
            //记住我
            token.setRememberMe(true);
            try {
                //调用登录方法：将token-->委托给安全管理器进行认证-->进入认认证器，调用Realm获取用户的信息进行匹配
                currentUser.login(token);
                return "index";
            } catch (UnknownAccountException uae) { //账号不存在
                throw new UnknownAccountException("账号不存在");
            } catch (IncorrectCredentialsException ice) { //密码不匹配
                throw new IncorrectCredentialsException("密码错误");
            } catch (LockedAccountException lae) { //账户锁定
                throw new LockedAccountException("账户锁定");
            }
            catch (AuthenticationException ae) { //认证异常
                throw new AuthenticationException("登录失败");
            }
        }
        return "false";
    }
}
