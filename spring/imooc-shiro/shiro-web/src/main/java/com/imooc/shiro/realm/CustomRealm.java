package com.imooc.shiro.realm;

import com.imooc.dao.UserDao;
import com.imooc.vo.User;
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

import javax.annotation.Resource;
import java.util.*;

public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

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

        System.out.println("从数据库中获取授权信息");

        List<String> list = userDao.getRolesByUserName(userName);
        Set<String> roles = new HashSet(list);
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

        // realmName 可随意定义
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passWord, "customRealm");
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
        User user = userDao.getUserByName(userName);
        if (user != null) {
            return user.getPassword();
        }
        return null;
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456", "0");
        System.out.println(md5Hash);
    }


}
