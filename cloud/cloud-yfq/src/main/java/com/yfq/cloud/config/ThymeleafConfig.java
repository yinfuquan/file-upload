package com.yfq.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
@Configuration
public class ThymeleafConfig {

    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        return resolver;
    }


    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }



    public ITemplateResolver templateResolver(){
        SpringResourceTemplateResolver  resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/templates/");//设置地址前缀
        resolver.setSuffix(".html");//设置后缀
        resolver.setCacheable(false);//设置不缓存
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;

    }

}
