/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate3.HibernateExceptionTranslator;

/**
 *
 * @author edoardo
 */
@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = "org.cheetah")
@ImportResource(value = {"classpath:/repository.xml"})//,"classpath:/dispatcher-servlet.xml","classpath:/hrpolite-security.xml"})
public class Config {
    
    
     @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
    
    
}
