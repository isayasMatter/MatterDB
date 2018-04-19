package matterdb;
import java.util.*;
import java.io.Serializable;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.1 03/27/2018
 */
public class Database implements Serializable{
    private String name;
    private HashMap<String, Table> tables;
    
    /**
     * Constructor
     * @param name 
     */
    public Database(String name){
        this.name = name;
        tables = new HashMap();
    }
    
    /**
     * Creates a table in the current database.
     * @param name name of the table.
     * @param columns definitions of the columns.
     * @return 
     */
    public boolean createTable(String name, List<Column> columns){
        
        if (!tableExists(name)){
            Table newTable = new Table(name);
            
            //Set<String> columnNames = columns.keySet();
            columns.forEach((col) -> newTable.addColumn(col));            
            
            tables.put(name, newTable);
            if(!name.equals("temp_54321")){
                System.out.println("Table " + name + " created.");
            }
            return true;
        }else {
            System.out.println("!Failed to create table " + name + " because it already exists.");
            return false;
        }
    }
    
    /**
     * Alters the structure of a table in the current database.
     * @param name of the table.
     * @param columns definition of the columns.
     * @return 
     */
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
    
    /**
     * Drops a table from a database.
     * @param name
     * @return 
     */
    public boolean dropTable(String name){
        if (tableExists(name)) {
            this.tables.remove(name);
            if(!name.equals("temp_54321")){
                System.out.println("Table " + name + " deleted.");
            }
            return true;
        }else {            
            System.out.println("!Failed to delete table " + name + " because it does not exist.");
            return false;
        }
    }
    
    /**
     * Prints the data and structure of a table.
     * @param name
     * @param selectedColumns
     * @param condition 
     */
    public void selectTable(Command cmd){
        
        Table table;
        if(tableExists(cmd.getObjectName())){
            table = (Table) this.tables.get(cmd.getObjectName());
            System.out.println(table.getColumns(cmd.getSelectedColumns()));
            table.printTuples(cmd.getSelectedColumns(), cmd.getCondition());
        }else{
            System.out.println("!Failed to query table " + cmd.getObjectName() + " because it does not exist");
        }
    }
    
    /**
     * A method that uses nested loop join to join two tables.
     * @param cmd 
     */
    public void selectJoinTables(Command cmd){
        
        Table table1 = this.tables.get(cmd.getObjectName());
        Table table2 = this.tables.get(cmd.getObjectName2());
        
        LinkedHashMap newMap = new LinkedHashMap();
        
        newMap.putAll(table1.getColumnDefinitions());
        newMap.putAll(table2.getColumnDefinitions());
        
        List<Column> newList = new ArrayList<>(newMap.values());
        
        this.createTable("temp_54321", newList);
        cmd.setObjectName("temp_54321");
        
        String column1 = cmd.getCondition().columnName;
        String column2 = cmd.getCondition().conditionValue;
        
        column1 = column1.substring(column1.lastIndexOf(".") + 1);
        column2 = column2.substring(column2.lastIndexOf(".") + 1);
        
        boolean flag = false;
        for(int key1: table1.getTuples().keySet()){
            Tuple tuple1 = table1.getTuples().get(key1);
            for (int key2: table2.getTuples().keySet()){
                Tuple tuple2 = table2.getTuples().get(key2);
                if(tuple1.cells.get(column1).equals(tuple2.cells.get(column2))){
                    Tuple newTuple = new Tuple();
                    newTuple.cells.putAll(tuple1.cells);
                    newTuple.cells.putAll(tuple2.cells);               
                    this.tables.get("temp_54321").insertTuple(newTuple);
                    flag = true;
                }                
            }
            if(cmd.getJoinType()!= null && cmd.getJoinType().equals("left outer join")){
                if(flag==false){
                    Tuple newTuple = new Tuple();
                    newTuple.cells.putAll(tuple1.cells);
                    this.tables.get("temp_54321").insertTuple(newTuple);
                }
            }
            flag = false;
        }
        
        cmd.setCondition(null);
        this.selectTable(cmd);
        this.dropTable("temp_54321");
    
    }
    
    /**
     * Inserts a tuple into a table.
     * @param name
     * @param values 
     */
    public void insertIntoTable(String name, String[] values){
        Table table;
        if(tableExists(name)){
            table = (Table) this.tables.get(name);
            table.insertTuple(values);
        } else {
            System.out.println("!Failed to insert to table " + name + " because it does not exist");
        }
    }
    
    /**
     * Checks whether a table exists.
     * @param name
     * @return 
     */
    public boolean tableExists(String name){
        return (this.tables.get(name) != null);
    }
    
    /**
     * Deletes a tuple from a table.
     * @param name
     * @param condition 
     */
    public void deleteFromTable(String name, Condition condition){
        Table table;
        if(tableExists(name)){
            table = (Table) this.tables.get(name);
            table.deleteTuples(condition);
        } else {
            System.out.println("!Failed to delete from table " + name + " because it does not exist");
        }
    }
    
    /**
     * Updates the values a table.
     * @param cmd 
     */
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
