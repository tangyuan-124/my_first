package com.qfxl.cm.common.config.shiro.realms;

import com.qfxl.cm.common.config.shiro.JwtToken;
import com.qfxl.cm.common.utils.JwtUtil;
import com.qfxl.cm.domain.SysUser;
import com.qfxl.cm.service.SysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 自定义realm (系统登录认证实现)
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public class CustomerRealm extends AuthorizingRealm {
     Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SysUserService sysUserService;

    /**
     * 重写此方法避免shiro报错
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = null;
        try {
            //这里工具类没有处理空指针等异常这里处理一下(这里处理科学一些)
            username = JwtUtil.getUsername(token);
        } catch (Exception e) {
            throw new AuthenticationException("token拼写错误或者值为空");
        }
        if (username == null) {
            log.error("token无效(空''或者null都不行!)");
            throw new AuthenticationException("token无效");
        }
        SysUser user = sysUserService.findUserByName(username);
        if (user == null) {
            log.error("用户不存在)");
            throw new AuthenticationException("用户不存在");
        }
        if (!JwtUtil.verify(token, username, user.getPassword())) {
            log.error("用户名或密码错误(token无效或者与登录者不匹配)");
            throw new AuthenticationException("用户名或密码错误(token无效或者与登录者不匹配)");
        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }

}
