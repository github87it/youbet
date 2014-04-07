/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import java.io.File;
import org.cheetah.youbet.config.Config;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.json.JsonTableRow;
import org.cheetah.youbet.service.GenericService;
import org.cheetah.youbet.service.PalinsestoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
//        DecimalFormat df = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.ENGLISH);
//        df.applyPattern("#.####");
//        System.out.println(df.format(3.45E-4*100));
//        
//        System.out.println((2.0+3.5));
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
//        PalinsestoService ps = (PalinsestoService) ctx.getBean("palinsestoService");
//        Palinsesto palinsesto = ps.findByPk(17,12695);
//        System.out.println("------");
//        System.out.println(palinsesto);
//        System.out.println("------");
//        JsonTableRow r = new JsonTableRow();
//        r.setResult("ciao");
//        ObjectMapper mapper = new ObjectMapper();
//        Hibernate4Module hbm = new Hibernate4Module();
//        hbm.enable(Hibernate4Module.Feature.USE_TRANSIENT_ANNOTATION);
//        mapper.registerModule(hbm);
        System.out.println(ctx.getBean(GenericService.class).getProbabilita1(14984, 3, 5.0));
        System.out.println(ctx.getBean(GenericService.class).getProbabilitaX(14984, 3, 5.0));
        System.out.println(ctx.getBean(GenericService.class).getProbabilita2(14984, 3, 5.0));
        System.out.println(ctx.getBean(GenericService.class).getProbabilita12(14984, 3, 5.0));
        System.out.println(ctx.getBean(GenericService.class).getProbabilitaX2(14984, 3, 5.0));
        System.out.println(ctx.getBean(GenericService.class).getProbabilita1X(14984, 3, 5.0));
        
//        System.out.println(mapper.writeValueAsString(r));
    }

}
