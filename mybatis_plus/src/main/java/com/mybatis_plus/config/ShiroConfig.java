package com.mybatis_plus.config;

import com.mybatis_plus.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro配置类
 */
@Configuration
public class ShiroConfig {

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("md5");
        //加密次数
        credentialsMatcher.setHashIterations(2);//加密次数  2: md5(md5(*))
        //credentialsMatcher.setStoredCredentialsHexEncoded(true);//转化为16进制(与入库时保持一致)
        return credentialsMatcher;
    }

    /**
     * 加密待测试
     *
     * @param matcher
     * @return
     */
    @Bean("userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {
        UserRealm userRealm = new UserRealm();
        //userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }


    /**
     * cookie对象;
     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
     *
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * 注意：这里的SessionIdCookie 是新建的一个SimpleCookie对象,不是之前整合记住我的那个rememberMeCookie 如果配错了，
     * 就会出现session经典问题：每次请求都是一个新的session 并且后台报以下异常，解析的时候报错.因为记住我cookie是加密的用户信息,所以报解密错误
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     *
     * @return
     */
    @Bean("rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);
        // 设置登录成功跳转Url
        bean.setSuccessUrl("/main.html");
        // 设置登录跳转Url
        bean.setLoginUrl("/index.html");
        // 设置未授权提示Url
        bean.setUnauthorizedUrl("/error.html");

        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        //filterMap.put("/login","anon");
        //filterMap.put("/user/index","authc");
        //filterMap.put("/vip/index","roles[vip]");
        //filterMap.put("/druid/**", "anon");
        //filterMap.put("/static/**","anon");
        filterMap.put("/login/**", "anon");
        filterMap.put("/other.jpg", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-ui.html/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        //
        filterMap.put("/**", "authc");
        //filterMap.put("/logout", "logout");

        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    /**
     * 注入 securityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(HashedCredentialsMatcher hashedCredentialsMatcher,
                                                                  @Qualifier("rememberMeManager") RememberMeManager rememberMeManager) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //remembercookie里有用户信息----shiro会进行加密解密
        securityManager.setRememberMeManager(rememberMeManager);
        // 关联realm.
        securityManager.setRealm(userRealm(hashedCredentialsMatcher));
        return securityManager;
    }
}
