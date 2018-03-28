/*
 * This file was prepared as part of the CS657 HW1 assignment  at University of Nevada, Reno byIsayas Adhanom.
 * Use it at your own risk.
 * Spring 2018.
 */
package matterdb;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author user
 */
public class Tuple implements Serializable{
    LinkedHashMap cells = new LinkedHashMap();
    
    public Tuple(LinkedHashMap<String, String> columns){
        this.cells = columns;
//        Set<String> columnNames = columns.keySet();
//        
//        for(String columnName:columnNames){
//            addCell(columnName, columns.get(columnName));
//        }
    }
    
    public void addCell(String columnName, String cellValue){
        Cell newCell = new Cell(columnName, cellValue);
        this.cells.put(columnName,newCell);
    }

    public String printTuple(List<String> selectedColumns){
        String tupleString = "";
        
        List<String> columnNames = new ArrayList<String>();
        
        if (!selectedColumns.contains("*")){
            columnNames = selectedColumns;
        } else {
            columnNames.addAll(this.cells.keySet());
        }   
        
        for(String columnName:columnNames){
            tupleString += (this.cells.get(columnName) + "|");
        }
        
        tupleString = tupleString.substring(0,tupleString.length()-2);
        return tupleString;
    }    
}
