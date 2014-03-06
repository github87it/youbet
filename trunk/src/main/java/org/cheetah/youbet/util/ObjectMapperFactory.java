/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.orm.jpa.JpaTransactionManager;

/**
 *
 * @author edoardo
 */
public final class ObjectMapperFactory {

    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

    }

    private ObjectMapperFactory() {

    }
    
//    public static ObjectMapper getInstance(){
//        return fac.mapper;
//    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return this;
//    }
    
    

}
