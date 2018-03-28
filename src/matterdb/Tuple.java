/*
 * This file was prepared as part of the CS657 HW assignment  at University of Nevada, Reno by Isayas Adhanom.
 * Use it at your own risk.
 * Spring 2018.
 */
package matterdb;
import java.io.Serializable;
import java.util.*;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 03/27/2018
 */
public class Tuple implements Serializable{
    LinkedHashMap cells = new LinkedHashMap();
    
    /**
     * Constructor
     * @param columns 
     */
    public Tuple(LinkedHashMap<String, String> columns){
        this.cells = columns;
//        Set<String> columnNames = columns.keySet();
//        
//        for(String columnName:columnNames){
//            addCell(columnName, columns.get(columnName));
//        }
    }
    
    /**
     * Add a cell to the tuple.
     * @param columnName
     * @param cellValue 
     */
    public void addCell(String columnName, String cellValue){
        Cell newCell = new Cell(columnName, cellValue);
        this.cells.put(columnName,newCell);
    }

    /**
     * Formats a tuple as a string.
     * @param selectedColumns
     * @return 
     */
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
