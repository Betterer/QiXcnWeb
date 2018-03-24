package com.qixcnweb.qixian.configuration;

import com.qixcnweb.qixian.handler.LoginSuccessHandler;
import com.qixcnweb.qixian.handler.LogoutSuccessHandler;
import com.qixcnweb.qixian.service.CustomUserDetailsService;
import com.qixcnweb.qixian.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * Spring Security配置类
 * Created by dingxiaochi on 2018/3/7.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers(new String[]{"/js/**","/css/**","/img/**","/images/**","/fonts/**","/**/favicon.ico"});
    }


    @Autowired
    @Qualifier("rememberMeDataSource")
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //***************************************  设置登录/登出和操作成功之后的跳转页面 ****************************************//
        http.authorizeRequests()
                //其他地址的访问均需验证权限
                .anyRequest().permitAll()
                .antMatchers("/user/info").authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/index")//登录成功后默认跳转到"/index"
                .successHandler(loginSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index")//退出登录后的默认url是"/home"
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds(1209600)
                .tokenRepository(tokenRepository());

        //***************************************  CSRF 安全令牌(拦截所有POST请求) ****************************************//
        http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository()).requireCsrfProtectionMatcher(new RequestMatcher() {
            @Override
            public boolean matches(HttpServletRequest httpServletRequest) {
                return httpServletRequest.getMethod().equals("POST");
            }
        });
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService())
             .passwordEncoder( new PasswordEncoder(){

                 @Override
                 public String encode(CharSequence rawPassword) {
                     return MD5Utils.encode((String)rawPassword);
                 }

                 @Override
                 public boolean matches(CharSequence rawPassword, String encodedPassword) {
                     return encodedPassword.equals(MD5Utils.encode((String)rawPassword));
                 }
             });
        //记住我功能使用
        auth.eraseCredentials(false);
    }

    /**
     * 记住我功能注入token
     * @return
     */
    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl j=new JdbcTokenRepositoryImpl();
        j.setDataSource(dataSource);
        return j;
    }


    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    /**
     * 自定义UserDetailsService，从数据库中读取用户信息
     * @return
     */
    @Bean
    public CustomUserDetailsService customUserDetailsService(){
        return new CustomUserDetailsService();
    }


    /**
     * 用户登录成功后的业务处理类
     * @return
     */
    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new LogoutSuccessHandler();
    }


    /**
     * 指定spring secutiry 的 .tld文件加载
     * @return
     */
    @Bean
    public ClassPathTldsLoader classPathTldsLoader(){
        return new ClassPathTldsLoader();
    }

}
