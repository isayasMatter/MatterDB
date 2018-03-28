package matterdb;
import java.util.*;
import java.io.Serializable;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment 1
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 02/22/2018
 */
public class Database implements Serializable{
    private String name;
    private HashMap<String, Table> tables;
    
    public Database(String name){
        this.name = name;
        tables = new HashMap();
    }
    
    public boolean createTable(String name, List<Column> columns){
        
        if (!tableExists(name)){
            Table newTable = new Table(name);
            
            //Set<String> columnNames = columns.keySet();
            columns.forEach((col) -> newTable.addColumn(col));            
            
            tables.put(name, newTable);
            System.out.println("Table " + name + " created.");
            return true;
        }else {
            System.out.println("!Failed to create table " + name + " because it already exists.");
            return false;
        }
    }
    
    public boolean alterTable(String name, List<Column> columns){
        Column column = columns.get(0);
        if (tableExists(name)){
            this.tables.get(name).addColumn(column);           
            System.out.println("Table " + name + " modified.");
            return true;
        }else{
            System.out.println("!Failed to alter table " + name + " because it does not exist.");
            return false;
        }
    }
    
    public boolean dropTable(String name){
        if (tableExists(name)) {
            this.tables.remove(name);
            System.out.println("Table " + name + " deleted.");
            return true;
        }else{            
            System.out.println("!Failed to delete table " + name + " because it does not exist.");
            return false;
        }
    }
    
    public void selectTable(String name, List selectedColumns, Condition condition){
        Table table;
        if(tableExists(name)){
            table = (Table) this.tables.get(name);
            System.out.println(table.getColumns(selectedColumns));
            table.printTuples(selectedColumns, condition);
        }else{
            System.out.println("!Failed to query table " + name + " because it does not exist");
        }
    }
    
    public void insertIntoTable(String name, String[] values){
        Table table;
        if(tableExists(name)){
            table = (Table) this.tables.get(name);
            table.insertTuple(values);
        } else {
            System.out.println("!Failed to insert to table " + name + " because it does not exist");
        }
    }
    
    public boolean tableExists(String name){
        return (this.tables.get(name) != null);
    }
    
    public void deleteFromTable(String name, Condition condition){
        Table table;
        if(tableExists(name)){
            table = (Table) this.tables.get(name);
            table.deleteTuples(condition);
        } else {
            System.out.println("!Failed to delete from table " + name + " because it does not exist");
        }
    }
    
    public void updateTable(Command cmd){
        String tableName = cmd.getObjectName();
        Table table;
        if(tableExists(tableName)){
            table = (Table) this.tables.get(tableName);
            table.updateTuples(cmd.getCondition(), cmd.getUpdateColumnName(), cmd.getUpdateColumnValue());
        } else {
            System.out.println("!Failed to update table " + tableName + " because it does not exist");
        }
    }
}
