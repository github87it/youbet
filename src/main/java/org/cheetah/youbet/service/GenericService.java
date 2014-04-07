/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cheetah.youbet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.cheetah.youbet.json.JsonTable;
import org.cheetah.youbet.json.JsonTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edoardo
 */
@Service(value = "genericService")
@Transactional
public class GenericService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getProbabilita1(final int idPalinsesto, final int idAvvenimento, final double percentuale) throws SQLException, JsonProcessingException {
        String sql = "SELECT SUM(PERCENTUALE) FROM POISSON WHERE ID_AVVENIMENTO=? AND ID_PALINSESTO=?  AND PERCENTUALE>? AND  GOL_HOME>GOL_AWAY";
        final String columnName = "VITTORIA_CASA";

        return getProbabilitaEsito1X2(sql, idAvvenimento, idPalinsesto, percentuale, columnName);
    }

    public String getProbabilitaX(final int idPalinsesto, final int idAvvenimento, final double percentuale) throws SQLException, JsonProcessingException {
        String sql = "SELECT SUM(PERCENTUALE) FROM POISSON WHERE ID_AVVENIMENTO=? AND ID_PALINSESTO=?  AND PERCENTUALE>? AND  GOL_HOME=GOL_AWAY";
        final String columnName = "PAREGGIO";

        return getProbabilitaEsito1X2(sql, idAvvenimento, idPalinsesto, percentuale, columnName);
    }

    public String getProbabilita2(final int idPalinsesto, final int idAvvenimento, final double percentuale) throws SQLException, JsonProcessingException {
        String sql = "SELECT SUM(PERCENTUALE) FROM POISSON WHERE ID_AVVENIMENTO=? AND ID_PALINSESTO=?  AND PERCENTUALE>? AND  GOL_HOME<GOL_AWAY";
        final String columnName = "VITTORIA_FUORI";

        return getProbabilitaEsito1X2(sql, idAvvenimento, idPalinsesto, percentuale, columnName);
    }

    public String getProbabilitaX2(final int idPalinsesto, final int idAvvenimento, final double percentuale) throws SQLException, JsonProcessingException {
        String sql = "SELECT SUM(PERCENTUALE) FROM POISSON WHERE ID_AVVENIMENTO=? AND ID_PALINSESTO=?  AND PERCENTUALE>? AND  GOL_HOME<=GOL_AWAY";
        final String columnName = "DOPPIA_CHANCE_OUT";

        return getProbabilitaEsito1X2(sql, idAvvenimento, idPalinsesto, percentuale, columnName);
    }

    public String getProbabilita12(final int idPalinsesto, final int idAvvenimento, final double percentuale) throws SQLException, JsonProcessingException {
        String sql = "SELECT SUM(PERCENTUALE) FROM POISSON WHERE ID_AVVENIMENTO=? AND ID_PALINSESTO=?  AND PERCENTUALE>? AND  GOL_HOME<>GOL_AWAY";
        final String columnName = "DOPPIA_CHANCE_IN_OUT";

        return getProbabilitaEsito1X2(sql, idAvvenimento, idPalinsesto, percentuale, columnName);
    }
    
    public String getProbabilita1X(final int idPalinsesto, final int idAvvenimento, final double percentuale) throws SQLException, JsonProcessingException {
        String sql = "SELECT SUM(PERCENTUALE) FROM POISSON WHERE ID_AVVENIMENTO=? AND ID_PALINSESTO=?  AND PERCENTUALE>? AND  GOL_HOME>=GOL_AWAY";
        final String columnName = "DOPPIA_CHANCE_IN";

        return getProbabilitaEsito1X2(sql, idAvvenimento, idPalinsesto, percentuale, columnName);
    }

    private String getProbabilitaEsito1X2(String sql, final int idAvvenimento, final int idPalinsesto, final double percentuale, final String columnName) throws JsonProcessingException, DataAccessException {
        JsonTable jsonTable = new JsonTable();

        List<JsonTableRow> rows = jdbcTemplate.query(sql, new Object[]{idAvvenimento, idPalinsesto, percentuale}, new RowMapper<JsonTableRow>() {

            public JsonTableRow mapRow(ResultSet rs, int rowNum) throws SQLException {
                JsonTableRow row = new JsonTableRow();
                row.setColumnName(columnName);
                row.setColumnValue(rs.getString(1));
                return row;
            }
        });
        jsonTable.setRows(rows);
        return new ObjectMapper().writeValueAsString(jsonTable);
    }

}
