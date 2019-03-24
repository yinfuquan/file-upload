package com.yfq.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.ResourceServlet;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }
   @Bean
    public ServletRegistrationBean statView(){
       ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid");
       Map<String,String> map=new HashMap<>();
//       ResourceServlet
       map.put("loginUsername","admin");
       map.put("loginPassword","123456");
       bean.setInitParameters(map);
        return  bean;
    }
    @Bean
    public FilterRegistrationBean filter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> map=new HashMap<>();
        map.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }
}
