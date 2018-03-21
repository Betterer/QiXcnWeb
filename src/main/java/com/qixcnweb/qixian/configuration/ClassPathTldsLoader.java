package com.qixcnweb.qixian.configuration;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.boot.autoconfigure.security.SpringBootWebSecurityConfiguration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 指定Spring Security 页面标签 .tld文件的加载地址
 * Created by dingxiaochi on 2018/3/10.
 */
public class ClassPathTldsLoader {

    /**
     * 指定Spring Security标签文件(.tld)文件路径
     * security.tld 可以在spring-security-taglibs这个jar包中找到
     */
    private static final String SECURITY_TLD = "/security.tld";

    final private List<String> classPathTlds;

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public ClassPathTldsLoader(String... classPathTlds) {
        super();
        if(ArrayUtils.isEmpty(classPathTlds)){
            this.classPathTlds = Arrays.asList(SECURITY_TLD);
        }else{
            this.classPathTlds = Arrays.asList(classPathTlds);
        }
    }

    @PostConstruct
    public void loadClassPathTlds() {
        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTlds);
    }
}
