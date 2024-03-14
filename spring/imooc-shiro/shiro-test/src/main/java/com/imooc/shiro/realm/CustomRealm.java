package com.imooc.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    Map<String, String> user = new HashMap<>();

    {
        user.put("M", "124bd1296bec0d9d93c7b52a71ad8d5b");
        super.setName("customRealm");
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String userName = (String) principals.getPrimaryPrincipal();
        // 数据库获取角色和权限
        Set<String> roles = getRolesByName(userName);
        Set<String> permissions = getPermissonsByName(userName);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        authorizationInfo.addRoles(roles);

        return authorizationInfo;
    }

    private Set<String> getPermissonsByName(String userName) {
        Set<String> permissons = new HashSet<>();
        permissons.add("user:add");
        return permissons;
    }

    private Set<String> getRolesByName(String userName) {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        return roles;
    }

    // 根据主体信息得到认证信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 通过主体信息得到用户名
        String userName = (String) token.getPrincipal();

        // 通过数据库获取凭证
        String passWord = getPasswordByName(userName);
        if (passWord == null) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("M", passWord, "customRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("0"));

        return authenticationInfo;
    }

    /**
     * 从数据库中获取
     *
     * @param userName
     * @return
     */
    private String getPasswordByName(String userName) {
        return user.get(userName);
    }


    public static void str(String s) {
        s.replace("j", "l");
    }

    public static void buf(StringBuffer buf) {
        buf.append("c");
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456", "0");
        System.out.println(md5Hash);
    }


}






























