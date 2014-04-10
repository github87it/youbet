/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 *
 * @author edoardo
 */
@JsonAutoDetect
public class JsonTableColumn {
        private String columnName ;
    private String columnValue;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

}
