package matterdb;
import java.util.List;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 03/27/2018
 */
public class Command {    
    private String commandType;
    private String objectName;
    private String objectName2 = null;
    private String objectCode = null;
    private String objectCode2 = null;
    private String joinType = null;
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
        this.objectName = objectName.toLowerCase();
    }
    
    /**
     * Constructor
     * @param commandType the type of SQL command
     * @param objectName the name of the object we want to manipulate
     * @param columnList 
     */
    public Command(String commandType, String objectName, List columnList){
        this.commandType = commandType;
        this.objectName = objectName.toLowerCase();
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
        return objectName.toLowerCase();
    }
    
    /**
     * 
     * @param objectName the name of the object we want to manipulate.
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName.toLowerCase();
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
    
    /**
     * 
     * @return 
     */
    public Condition getCondition() {
        return condition;
    }
    
    /**
     * 
     * @param condition 
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }    
    
    /**
     * 
     * @return 
     */
    public String getUpdateColumnName() {
        return updateColumnName.toLowerCase();
    }

    /**
     * 
     * @param updateColumnName 
     */
    public void setUpdateColumnName(String updateColumnName) {
        this.updateColumnName = updateColumnName.toLowerCase();
    }
    
    /**
     * 
     * @return 
     */
    public String getUpdateColumnValue() {
        return updateColumnValue;
    }

    /**
     * 
     * @param updateColumnValue 
     */
    public void setUpdateColumnValue(String updateColumnValue) {
        this.updateColumnValue = updateColumnValue;
    }
    
    /**
     * 
     * @return 
     */
    public List getValueList() {
        return valueList;
    }

    /**
     * 
     * @param valueList 
     */
    public void setValueList(List valueList) {
        this.valueList = valueList;
    }

    /**
     * 
     * @return 
     */
    public List getSelectedColumns() {
        return selectedColumns;
    }

    /**
     * 
     * @param selectedColumns 
     */
    public void setSelectedColumns(List selectedColumns) {
        this.selectedColumns = selectedColumns;
    }

    public String getObjectName2() {
        return (objectName2 == null?null:objectName2.toLowerCase());
    }

    public void setObjectName2(String objectName2) {
        this.objectName2 = objectName2.toLowerCase();
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String obejctCode) {
        this.objectCode = obejctCode;
    }

    public String getObjectCode2() {
        return objectCode2;
    }

    public void setObjectCode2(String objectCode2) {
        this.objectCode2 = objectCode2;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    @Override
    public String toString() {
        return "Command{" + "commandType=" + commandType + ", objectName=" + objectName + ", objectName2=" + objectName2 + ", objectCode=" + objectCode + ", objectCode2=" + objectCode2 + ", joinType=" + joinType + ", condition=" + condition + ", valueList=" + valueList + '}';
    }
    
    
}
