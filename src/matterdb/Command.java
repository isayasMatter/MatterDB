/*
 * This file was prepared as part of the CS657 HW1 assignment  at University of Nevada, Reno by Isayas Adhanom.
 * Use it at your own risk.
 * Spring 2018.
 */
package matterdb;
import java.util.List;

/**
 * CS657 Programming Assignment 1
 * Spring 2018
 * @author Isayas Adhanom
 */
public class Command {    
    private String commandType;
    private String objectName;
    private List columnList;
    private Condition condition;
    private String updateColumnName;
    private String updateColumnValue;
    private List valueList;
    private List selectedColumns;
    
    /**
     * Constructor
     */
    public Command(){        
        this.commandType = null;
        this.objectName = null;
        this.columnList = null;
        this.condition = null;
        selectedColumns = null;
    }
    
    /**
     * Constructor
     * @param commandType the type of SQL command
     */
    public Command(String commandType){
        this.commandType = commandType;
    }
    
    /**
     * Constructor
     * @param commandType the type of SQL command
     * @param objectName the name of the object we want to manipulate
     */
    public Command(String commandType, String objectName){
        this.commandType = commandType;
        this.objectName = objectName;
    }
    
    /**
     * Constructor
     * @param commandType the type of SQL command
     * @param objectName the name of the object we want to manipulate
     * @param columnList 
     */
    public Command(String commandType, String objectName, List columnList){
        this.commandType = commandType;
        this.objectName = objectName;
        this.columnList = columnList;
    }   
    
    /**
     * 
     * @return the type of SQL command.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * 
     * @param commandType the type of SQL command
     */
    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }
    
    /**
     * 
     * @return the name of the object we want to manipulate.
     */
    public String getObjectName() {
        return objectName;
    }
    
    /**
     * 
     * @param objectName the name of the object we want to manipulate.
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    
    /**
     * 
     * @return the list of columns for the ALTER or CREATE TABLE commands.
     */
    public List getColumnList() {
        return columnList;
    }
    
    /**
     * 
     * @param columnList the list of columns for the ALTER or CREATE TABLE commands.
     */
    public void setColumnList(List columnList) {
        this.columnList = columnList;
    }   

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }    

    public String getUpdateColumnName() {
        return updateColumnName;
    }

    public void setUpdateColumnName(String updateColumnName) {
        this.updateColumnName = updateColumnName;
    }

    public String getUpdateColumnValue() {
        return updateColumnValue;
    }

    public void setUpdateColumnValue(String updateColumnValue) {
        this.updateColumnValue = updateColumnValue;
    }

    public List getValueList() {
        return valueList;
    }

    public void setValueList(List valueList) {
        this.valueList = valueList;
    }

    public List getSelectedColumns() {
        return selectedColumns;
    }

    public void setSelectedColumns(List selectedColumns) {
        this.selectedColumns = selectedColumns;
    }
    
    
    @Override
    public String toString() {
        return "Command{" + "commandType=" + commandType + ", objectName=" + objectName + ", condition=" + condition + ", updateColumnName=" + updateColumnName + ", updateColumnValue=" + updateColumnValue + '}';
    }
    
    
    
}
