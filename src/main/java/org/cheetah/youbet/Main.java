/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author edoardo
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        Document d = Jsoup.connect("http://www.livescore.com/soccer/austria/").get();
//        FileOutputStream fout = new FileOutputStream("/Users/edoardo/TEMP/calcio-austria-risultati.html");
//        IOUtils.write(d.toString(), fout);
//        fout.flush();
//        fout.close();
//        System.out.println(d.toString());
        DecimalFormat df = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.ENGLISH);
        df.applyPattern("#.####");
        System.out.println(df.format(3.45E-4*100));
        
        System.out.println((2.0+3.5));
        
        
    }

}
