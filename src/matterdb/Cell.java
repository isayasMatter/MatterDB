/*
 * This file was prepared as part of the CS657 HW1 assignment  at University of Nevada, Reno byIsayas Adhanom.
 * Use it at your own risk.
 * Spring 2018.
 */
package matterdb;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class Cell implements Serializable{
    private String columnName;
    private String cellValue;
    
    public Cell(String columnName, String cellValue){
        this.columnName = columnName;
        this.cellValue = cellValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    @Override
    public String toString() {
        return cellValue;
    }    
}
