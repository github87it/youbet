/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet;

import org.cheetah.youbet.config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 *
 * @author edoardo
 */
public class ContextSpringFactory {
    
    private AnnotationConfigApplicationContext ctx = null;
    
    
    private static ContextSpringFactory factory=null;
    
    private ContextSpringFactory() {
        this.ctx=new AnnotationConfigApplicationContext(Config.class);
    }
    
    public AbstractApplicationContext getContext(){
        return this.ctx;
    }
    
    public static ContextSpringFactory getInstance() {
        if(factory==null){
            factory=new ContextSpringFactory();
        }
        return factory;
    }
    
    
}
