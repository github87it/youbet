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

    public void updateManifestazioneTable() {
        String sql = "UPDATE "
                + "MANIFESTAZIONE M "
                + "set "
                + "DESCRIZIONE_LUNGA=(SELECT distinct  COMPETIZIONE FROM INCONTRO I,PALINSESTO P WHERE P.ID_PALINSESTO=I.ID_PALINSESTO AND "
                + "P.ID_AVVENIMENTO=I.ID_AVVENIMENTO AND M.ID_MANIFESTAZIONE=P.ID_MANIFESTAZIONE) ";
        jdbcTemplate.update(sql);
    }

     
   

}
