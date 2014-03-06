/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.cheetah.youbet.entities.Incontro;
import org.cheetah.youbet.entities.IncontroPK;
import org.cheetah.youbet.reader.CodeReader;
import org.cheetah.youbet.service.IncontroService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.cheetah.youbet.entities.LogError;
import org.cheetah.youbet.entities.Manifestazione;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.service.IncontroService.Stat;
import org.cheetah.youbet.service.LogErrorService;
import org.cheetah.youbet.service.ManifestazioneService;
import org.cheetah.youbet.service.PalinsestoService;

/**
 *
 * @author edoardo
 */
@Component(value = "downloadScores")
public class DownloadScores {

    @Autowired
    @Qualifier("incontroService")
    private IncontroService incontroService;

    @Autowired
    @Qualifier("palinsestoService")
    private PalinsestoService palinsestoService;

    @Autowired
    @Qualifier("manifestazioneService")
    private ManifestazioneService manifestazioneService;

    @Autowired
    @Qualifier("logErrorService")
    private LogErrorService errorService;

    public void execute() throws Exception {
        List<Date> dates = new ArrayList<Date>();
        Date maxDate = incontroService.findByMaxDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getStartDate());
        if (maxDate != null) {
            calendar.setTime(maxDate);
        }
//        calendar.set(Calendar.YEAR, 2012);
//        calendar.set(Calendar.DAY_OF_MONTH, 22);
//        calendar.set(Calendar.MONTH, 10);

        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        String _date = "";
        while (!(_date = dateToString(calendar.getTime(), "dd/MM/yyyy")).equals(dateToString(today.getTime(), "dd/MM/yyyy"))) {
//            if (!IncontroHelper.findMatchesByDate(con, calendar)) {
            dates.add(calendar.getTime());
//            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }
//        dates.remove(dates.size()-1);
        Map<String, String> presentiSuDB = new HashMap<String, String>();
        List<String> errori = new ArrayList<String>();
        try {
            int row = 0;

            first:
            for (Date date : dates) {
                loadRisultatiHtml(dateToString(date, "yyyyMMdd"));
            }
        } catch (Exception e) {
            throw e;

        }
    }

    private List<Incontro> loadRisultatiHtml(String data) throws Exception {
        int anno = Integer.parseInt(data.substring(0, 4));
        int mese = Integer.parseInt(data.substring(4, 6));
        int giorno = Integer.parseInt(data.substring(6));

        System.out.println("Risultatati del " + giorno + "/" + mese + "/" + anno);

        Map<String, String> datas = new HashMap<String, String>();
        datas.put("anno_corrente", Integer.toString(anno));
        datas.put("mese_corrente", Integer.toString(mese));
        datas.put("giorno_corrente", Integer.toString(giorno));
        datas.put("cerca_risultati", "Visualizza");
        Document doc = CodeReader.read("http://statistiche.pianetascommesse.it/risultati.php", datas, CodeReader.POST_METHOD);
        Elements elements = doc.getElementsByTag("table");
        List<Incontro> palinsestos = new ArrayList<Incontro>();
        for (Iterator<Element> it = elements.iterator(); it.hasNext();) {
            Element element = it.next();
            Elements titolo3 = element.getElementsByClass("titolo3");
            if (titolo3.text() != null && titolo3.text().trim().equalsIgnoreCase("calcio")) {
                Elements titolo2 = element.getElementsByClass("titolo2");
                String txt = titolo2.text();
                String competizione = txt.substring(txt.indexOf("-") + 1).trim();
                String palinsesto = txt.substring(0, txt.indexOf("-"));
                palinsesto = palinsesto.substring(palinsesto.indexOf(" ") + 1).trim();
                element = it.next();//prossima tabella con i risultati
                Elements rows = element.getElementsByTag("tr");
                System.out.println("Pal. " + palinsesto);
                List<Incontro> incontros = createAvvenimentos(palinsesto, competizione, data, rows);//, competizione);
                for (Incontro incontro : incontros) {
                    Palinsesto _palinsesto_ = palinsestoService.findByPk(incontro.getIncontroPK().getIdPalinsesto(), incontro.getIncontroPK().getIdAvvenimento());
                    if (_palinsesto_ != null) {
                        Manifestazione m = _palinsesto_.getIdManifestazione();
                        m.setDescrizioneLunga(incontro.getCompetizione());
                        manifestazioneService.save(m);
                        incontro.setCompetizione(m.getDescrizione());
                    }
                    
                }
                
                incontroService.saveAll(incontros);
//                addAll(palinsestos, incontros);
//                incontroService.saveAll(incontros);
//                p.setAvvenimentos(avvenimentos);

            }
        }
        return palinsestos;
    }

    private List<Incontro> createAvvenimentos(String idPalinsesto, String competizione, String data, Elements rows) throws ParseException {//, String competizione) {
        List<Incontro> avvenimentos = new ArrayList<Incontro>();
        //salto la prima riga che è l'intestazione
        for (int i = 1; i < rows.size(); i++) {
            Elements ris1 = rows.get(i).getElementsByClass("testo_r1");
            Elements ris2 = rows.get(i).getElementsByClass("testo_r2");
            Incontro incontro1 = createAvvenimento(idPalinsesto, competizione, data, ris1);
            Incontro incontro2 = createAvvenimento(idPalinsesto, competizione, data, ris2);
            if (incontro1 != null) {
                avvenimentos.add(incontro1);
            }
            if (incontro2 != null) {
                avvenimentos.add(incontro2);
            }
        }

        return avvenimentos;
    }

    private Incontro createAvvenimento(String idPalinsesto, String competizione, String data, Elements ris) throws ParseException {
        if (ris.size() > 0) {
            String avv = ris.get(0).text();
            String desc = ris.get(1).text();
            String ora = ris.get(2).text();
            String risultato = ris.get(3).text();
            SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
            sdf.applyPattern("yyyyMMdd");
            Date d = sdf.parse(data);
//            System.out.println("---------> " + risultato + " <-----------");
            if (risultato.indexOf("/") != -1) {
                String risTempo1 = risultato.substring(0, risultato.indexOf("/"));
                String risFinale = risultato.substring(risultato.indexOf("/") + 1);
                if (risFinale.indexOf("(") != -1) {
                    risFinale = risFinale.substring(0, risFinale.indexOf("(")).trim();
                }
                IncontroPK pk = new IncontroPK(Integer.parseInt(idPalinsesto), Integer.parseInt(avv));
                Incontro incontro = new Incontro(pk);
                try {
                    incontro.setDataEvento(d);
                    incontro.setHomeTeam(desc.substring(0, desc.indexOf("-")).trim().toUpperCase());
                    incontro.setAwayTeam(desc.substring(desc.indexOf("-") + 1).trim().toUpperCase());
                    incontro.setGolHomeParziale(Integer.parseInt(risTempo1.substring(0, risTempo1.indexOf("-")).trim()));
                    incontro.setGolAwayParziale(Integer.parseInt(risTempo1.substring(risTempo1.indexOf("-") + 1).trim()));
                    incontro.setGolHome(Integer.parseInt(risFinale.substring(0, risFinale.indexOf("-")).trim()));
                    incontro.setGolAway(Integer.parseInt(risFinale.substring(risFinale.indexOf("-") + 1).trim()));
                    incontro.setSegnoFinale(incontro.getGolHome() > incontro.getGolAway() ? "1" : (incontro.getGolHome() < incontro.getGolAway() ? "2" : "X"));
                    incontro.setSegnoParziale(incontro.getGolHomeParziale() > incontro.getGolAwayParziale() ? "1" : (incontro.getGolHomeParziale() < incontro.getGolAwayParziale() ? "2" : "X"));
                    incontro.setCompetizione(competizione);
                    return incontro;
                } catch (Exception e) {
                    LogError error = new LogError();
                    error.setDescrizione(e.getMessage() + " - " + incontro);
                    error.setData(d);
                    incontro = null;
                    errorService.save(error);
                }
            }

        }
        return null;
    }

    private Date getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        calendar.set(Calendar.MONTH, 7);
        calendar.set(Calendar.YEAR, 2012);

        return calendar.getTime();
    }

    private static String dateToString(Date date, String pattern) {
        SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        sdf.applyPattern(pattern);
        String p = sdf.format(date);
        return p;
    }
}
