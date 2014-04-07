/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;

/**
 *
 * @author edoardo
 */
@JsonAutoDetect
public class JsonTable {
    
    private List<JsonTableRow> rows;

    public List<JsonTableRow> getRows() {
        return rows;
    }

    public void setRows(List<JsonTableRow> rows) {
        this.rows = rows;
    }
}
