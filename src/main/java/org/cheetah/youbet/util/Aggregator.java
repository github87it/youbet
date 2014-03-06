/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cheetah.youbet.bean.json.JSONSchedina;
import org.cheetah.youbet.entities.Classe;
import org.cheetah.youbet.entities.Disciplina;
import org.cheetah.youbet.entities.Manifestazione;
import org.cheetah.youbet.reader.CodeReader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static org.cheetah.youbet.util.ObjectMapperFactory.mapper;
import static org.cheetah.youbet.reader.CodeReader.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import org.cheetah.youbet.bean.json.JSONQuote;
import org.cheetah.youbet.entities.Esito;
import org.cheetah.youbet.entities.EsitoPK;
import org.cheetah.youbet.entities.Palinsesto;
import org.cheetah.youbet.entities.PalinsestoPK;
import org.cheetah.youbet.entities.Quota;
import org.cheetah.youbet.entities.QuotaPK;
import org.cheetah.youbet.service.ClasseService;
import org.cheetah.youbet.service.DisciplinaService;
import org.cheetah.youbet.service.EsitoService;
import org.cheetah.youbet.service.IncontroService;
import org.cheetah.youbet.service.ManifestazioneService;
import org.cheetah.youbet.service.PalinsestoService;
import org.cheetah.youbet.service.QuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */
@Component(value = "aggregator")
public class Aggregator {

    @Autowired
    @Qualifier(value = "manifestazioneService")
    private ManifestazioneService manifestazioneService;

    @Autowired
    @Qualifier(value = "disciplinaService")
    private DisciplinaService disciplinaService;

    @Autowired
    @Qualifier(value = "classeService")
    private ClasseService classeService;

    @Autowired
    @Qualifier(value = "palinsestoService")
    private PalinsestoService palinsestoService;

    @Autowired
    @Qualifier("incontroService")
    private IncontroService incontroService;

    @Autowired
    @Qualifier(value = "esitoService")
    private EsitoService esitoService;

    @Autowired
    @Qualifier(value = "quotaService")
    private QuotaService quotaService;

    private static String menuUrl = "http://www.sisal.it/PalinsestoNuovo/pubblico/menu/sport.jsp?sezione=sport&filtro=tutti";

    private static String listaClassi = "http://www.sisal.it/PalinsestoNuovo/pubblico/sportSchedinaAjax.jsp?sezione=sport&disciplina={0}&manifestazione={1}&filtro=tutti";

    private static String listaQuote = "http://www.sisal.it/PalinsestoNuovo/pubblico/sportSchedinaAjax.jsp?sezione=sport&disciplina={0}&manifestazione={1}&filtro=tutti&classe={2}";

    @Transactional
    public void loadData() throws Exception {

        Document docMenu = CodeReader.read(menuUrl, CodeReader.POST_METHOD);

//        System.out.println(docMenu);
        Elements divs = docMenu.getElementsByAttributeValue("class", "menudin_voce1 menudin_voce1_green");
//        System.out.println(divs);
        Map<Integer, Disciplina> disciplinas = new HashMap<Integer, Disciplina>();
        List<Manifestazione> manifestaziones = new ArrayList<Manifestazione>();
        Map<Integer, JSONSchedina> jsons = new HashMap<Integer, JSONSchedina>();
        for (int i = 0; i < divs.size(); i++) {
            Element element = divs.get(i);
            String onclick = element.attr("onclick");
            String id = onclick.substring(onclick.indexOf("=") + 1, onclick.indexOf("&"));
            Element elDisciplina = docMenu.getElementById(id);
//            System.out.println(elDisciplina);
            Elements elDisciplinaLeaf = elDisciplina.getAllElements();
            for (int j = 0; j < elDisciplinaLeaf.size(); j++) {
                Element elManifestazioniParent = elDisciplinaLeaf.get(j);
                Elements elManifestazioni = elManifestazioniParent.children();
                for (int k = 0; k < elManifestazioni.size(); k++) {
                    Element elManifestazione = elManifestazioni.get(k);
                    String json = getJson(elManifestazione);
                    String disciplinaDesc = element.text();
                    String manifestazioneDesc = elManifestazione.text();
                    JSONSchedina jsonSchedina = mapper.readValue(json, JSONSchedina.class);
                    if (jsonSchedina.getDisciplina() == 1) {
                        Disciplina disciplina = disciplinas.get(jsonSchedina.getDisciplina());

                        if (disciplina == null) {
                            disciplina = new Disciplina();
                            disciplina.setIdDisciplina(jsonSchedina.getDisciplina());
                            disciplina.setDescrizione(disciplinaDesc);
                            disciplinas.put(jsonSchedina.getDisciplina(), disciplina);
                        }
                        Manifestazione manifestazione = new Manifestazione();
                        manifestazione.setIdDisciplina(disciplina);
                        manifestazione.setDescrizione(manifestazioneDesc);
                        manifestazione.setIdManifestazione(jsonSchedina.getManifestazione());
                        manifestaziones.add(manifestazione);
                        jsons.put(manifestazione.getIdManifestazione(), jsonSchedina);
                    }
                }
            }

            disciplinaService.save(disciplinas.values());
            manifestazioneService.save(manifestaziones);
//            System.out.println("\t"+element.text());

        }
        Map<Integer, Classe> classi = new HashMap<Integer, Classe>();
        manifestaziones = manifestazioneService.findAll();
        for (int i = 0; i < manifestaziones.size(); i++) {
            Manifestazione manifestazione = manifestaziones.get(i);
            manifestazione.setClasseCollection(new ArrayList<Classe>());
            String url = listaClassi;
            url = MessageFormat.format(url, manifestazione.getIdDisciplina().getIdDisciplina().toString(), manifestazione.getIdManifestazione().toString());
            Document doc = CodeReader.read(url, POST_METHOD);
            Elements tds = doc.getElementsByClass("textCheckSectionSpento");
            for (int j = 0; j < tds.size(); j++) {
                Element td = tds.get(j);
                Element span = td.getElementsByTag("span").get(0);
                JSONSchedina jsonSchedina = mapper.readValue(getJson(span), JSONSchedina.class);
                Classe classe = classi.get(jsonSchedina.getClasse());
                if (classe == null) {
                    classe = new Classe();
                    classe.setDescrizione(span.text());
                    classe.setIdClasse(jsonSchedina.getClasse());
//                    if (classe.getManifestazioneCollection() == null) {
//                        classe.setManifestazioneCollection(new ArrayList<Manifestazione>());
//                    }
//                    classe.getManifestazioneCollection().add(manifestazione);
//                    if(manifestazione.getClasseCollection()==null){
//                        manifestazione.setClasseCollection(new Arrayl);
//                    }

                }
                manifestazione.getClasseCollection().add(classe);
                classi.put(classe.getIdClasse(), classe);
//                Classe classe = classi.get(td)

            }
            classeService.save(classi.values());
            Set<Palinsesto> pSet = new HashSet<Palinsesto>();
            Set< Esito> eSet = new HashSet<Esito>();
            Set<Quota> qSet = new HashSet();
            for (Classe classe : classi.values()) {
                doc = CodeReader.read(MessageFormat.format(listaQuote,
                        manifestazione.getIdDisciplina().getIdDisciplina().toString(),
                        manifestazione.getIdManifestazione().toString(),
                        classe.getIdClasse().toString()), POST_METHOD);
                Elements quoteTd = doc.getElementsByClass("quote");

                for (int j = 0; j < quoteTd.size(); j++) {
                    Element element = quoteTd.get(j);
                    String json = getJson(element);
                    JSONQuote jsonQuote = mapper.readValue(json, JSONQuote.class);
                    Palinsesto palinsesto = createPalinsesto(jsonQuote);

                    if (!pSet.contains(palinsesto)) {
//                        palinsesto = new Palinsesto();
//                        PalinsestoPK pk = new PalinsestoPK(jsonQuote.getPalinsesto(), jsonQuote.getAvvenimento());
//                        palinsesto.setPalinsestoPK(pk);
                        palinsesto.setDescrizione(jsonQuote.getDesavvenimento());
                        if (jsonQuote.getDesavvenimento().indexOf("-") != -1) {
                            palinsesto.setHomeTeam(palinsesto.getDescrizione().substring(0, palinsesto.getDescrizione().indexOf("-")).trim());
                            palinsesto.setAwayTeam(palinsesto.getDescrizione().substring(palinsesto.getDescrizione().indexOf("-") + 1).trim());
                        }
                        String data = jsonQuote.getDatascad();
                        data = data.substring(0, data.indexOf("T"));
                        String time = jsonQuote.getDatascad().substring(jsonQuote.getDatascad().indexOf("T") + 1);
                        SimpleDateFormat df = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
                        df.applyPattern("yyyy-MM-dd");
                        palinsesto.setDataEvento(df.parse(data));
                        df = (SimpleDateFormat) SimpleDateFormat.getTimeInstance();
                        df.applyPattern("HH:mm:ss");
                        palinsesto.setOraEvento(df.parse(time));
                        palinsesto.setIdManifestazione(manifestazione);
                        palinsesto = palinsestoService.save(palinsesto);


                        //aggiungo il palinsesto qui perchè la prima volta sarà sempre a null
                    }
                    EsitoPK esitoPK = new EsitoPK(jsonQuote.getEsito(), jsonQuote.getClasse());
                    Esito esito = new Esito();
                    esito.setEsitoPK(esitoPK);
                    esito.setDescesito(jsonQuote.getDesesito());
                    esito = esitoService.save(esito);
                    Quota q = new Quota();
                    QuotaPK qpk = new QuotaPK(jsonQuote.getPalinsesto(), jsonQuote.getAvvenimento(), jsonQuote.getEsito(), jsonQuote.getClasse());
                    q.setQuotaPK(qpk);
                    q.setEsito(esito);
                    q.setPalinsesto(palinsesto);
                    String quota = element.text().trim();
                    if (quota.indexOf(",") != -1) {
                        quota = quota.replace(',', '.');
                    }
                    q.setQuota(new Double(quota));
                    q = quotaService.save(q);
//                    if (palinsesto.getQuotaCollection() == null) {
//                        palinsesto.setQuotaCollection(new ArrayList<Quota>());
//                    }
//
//                    palinsesto.getQuotaCollection().add(q);
                    pSet.add(palinsesto);

                    System.out.println(jsonQuote);
                }
            }

        }

    }

    private Palinsesto createPalinsesto(JSONQuote jsonQuote) {
        PalinsestoPK palPk = new PalinsestoPK(jsonQuote.getPalinsesto(), jsonQuote.getAvvenimento());
        Palinsesto palinsesto = new Palinsesto(palPk);
        return palinsesto;
    }

    private static String getJson(Element element) {
        String json = element.attr("onclick");
        json = json.substring(json.indexOf("{"), json.indexOf("}") + 1);
        return json;
    }

}
