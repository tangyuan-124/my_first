package com.qfxl.cm.common.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 实现shiro的AuthenticationToken接口的类JwtToken
 *
 * @author: qfxl
 * @date: 2023-12-11
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
