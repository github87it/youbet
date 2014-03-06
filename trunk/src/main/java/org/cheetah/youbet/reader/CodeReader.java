/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.reader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author edoardo
 */
public class CodeReader {
    
    
    public static final int POST_METHOD=1;
    public static final int GET_METHOD=2;
    
    private static  Map<Integer,String> map = new HashMap<Integer, String>();
    
    static{
        map.put(POST_METHOD, "post");
        map.put(GET_METHOD, "get");
    }

    
    /**
     * Esegue la connessione all'url specificato e scarica il documento.
     * @param url
     * @param data
     * @param httpmethod
     * @return
     * @throws Exception 
     */
    public static Document read(String url,Map<String,String> data,int httpmethod) throws Exception{
        Connection con = Jsoup.connect(url).timeout(10000);
        if(data!=null && data.size()>0){
            
            con = con.data(data);
        }
        Method method = con.getClass().getDeclaredMethod(map.get(httpmethod), null);
        return (Document) method.invoke(con, null);
    }
    /**
     * Esegue la connessione all'url specificato e scarica il documento. Http method di default è GET
     * @param url
     * @param data
     * @return
     * @throws Exception 
     */
     public static Document read(String url,Map<String,String> data) throws Exception{
         return read(url, data, GET_METHOD);
     }
     
     public static Document read(String url,int httpmethod) throws Exception{
         return read(url, new HashMap<String, String>(), httpmethod);
     }

      public static Document read(String url) throws Exception{
          return read(url,GET_METHOD);
      }

     
     
     
    
}
