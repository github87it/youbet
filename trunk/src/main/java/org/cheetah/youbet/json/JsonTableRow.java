/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edoardo
 */
@JsonAutoDetect
public class JsonTableRow {

    public List<JsonTableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<JsonTableColumn> columns) {
        this.columns = columns;
    }
    
    private List<JsonTableColumn> columns;
    
    
    
}
