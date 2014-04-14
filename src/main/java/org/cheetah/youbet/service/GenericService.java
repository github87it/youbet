/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cheetah.youbet.json.JsonTable;
import org.cheetah.youbet.json.JsonTableColumn;
import org.cheetah.youbet.json.JsonTableRow;
import org.cheetah.youbet.util.ObjectMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.cheetah.youbet.util.helper.IncontroHelper.*;

/**
 *
 * @author edoardo
 */
@Service(value = "genericService")
@Transactional
public class GenericService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void updateManifestazioneTable() {
        String sql = "UPDATE "
                + "MANIFESTAZIONE M "
                + "set "
                + "DESCRIZIONE_LUNGA=(SELECT distinct  COMPETIZIONE FROM INCONTRO I,PALINSESTO P WHERE P.ID_PALINSESTO=I.ID_PALINSESTO AND "
                + "P.ID_AVVENIMENTO=I.ID_AVVENIMENTO AND M.ID_MANIFESTAZIONE=P.ID_MANIFESTAZIONE) ";
        jdbcTemplate.update(sql);
    }

    public String findIncontroSquadraByRisultatoAndCompetizioneJsonFormat( String squadra,  String competizione,int typeTeam) throws JsonProcessingException {
        return ObjectMapperFactory.mapper.writeValueAsString(findIncontroSquadraByRisultatoAndCompetizione(squadra,competizione,typeTeam));
    }
    public String findIncontroSquadraByRisultatoAndCompetizioneJsonFormat( String squadra,  String competizione,int maxMatch,int typeTeam) throws JsonProcessingException {
        return ObjectMapperFactory.mapper.writeValueAsString(findIncontroSquadraByRisultatoAndCompetizione(squadra,competizione,maxMatch,typeTeam));
    }
    
    
    public JsonTable findIncontroSquadraByRisultatoAndCompetizione(final String squadra, final String competizione,int typeTeam)  {
        return findIncontroSquadraByRisultatoAndCompetizione(squadra, competizione, 10,typeTeam);

    }

    public JsonTable findIncontroSquadraByRisultatoAndCompetizione(final String squadra, final String competizione, final int maxMatch,int typeTeam) {
        System.out.println("TypeTeam: "+squadra+"-"+typeTeam);
        if(typeTeam!=HOME_TEAM && typeTeam!=AWAY_TEAM){
            throw new RuntimeException("Valore di typeTeam non valido!");
        }
        String team = (typeTeam==HOME_TEAM?"HOME_TEAM":"AWAY_TEAM");
        System.out.println("Team: "+team);
        String sql = "select count(*) TOTALE,"+team+",GOL_HOME,GOL_AWAY FROM (SELECT * FROM INCONTRO WHERE "+team+"=? AND COMPETIZIONE=? ORDER BY DATA_EVENTO DESC LIMIT ?) A  GROUP BY "+team+",GOL_HOME,GOL_AWAY  ORDER BY 1 DESC";
        
        JsonTable table = new JsonTable();
        List<JsonTableRow> jsonTableRows = jdbcTemplate.query(sql, new Object[]{squadra, competizione, maxMatch}, new RowMapper<JsonTableRow>() {

            public JsonTableRow mapRow(ResultSet rs, int rowNum) throws SQLException {
                JsonTableRow row = new JsonTableRow();
                ResultSetMetaData rsmd = rs.getMetaData();
                List<JsonTableColumn> columns = new ArrayList<JsonTableColumn>();
                for (int c = 0; c < rsmd.getColumnCount(); c++) {
                    JsonTableColumn column = new JsonTableColumn();
                    column.setColumnName(rsmd.getColumnName(c + 1));
                    column.setColumnValue(rs.getString(c + 1));
                    columns.add(column);
                }
                row.setColumns(columns);
                return row;
            }
        });
        table.setRows(jsonTableRows);
        System.out.println("--------------------------------------------");
        System.out.println(sql);
        System.out.println("--------------------------------------------");
        return table;
    }

}
