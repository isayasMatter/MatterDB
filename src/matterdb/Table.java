package matterdb;
import java.util.*;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 03/27/2018
 */
public class Table implements Serializable{
    private static final AtomicInteger id = new AtomicInteger(0); 
    private String name;     
    private LinkedHashMap columns;
    private LinkedHashMap<Integer, Tuple> tuples;
    
    /**
     * Constructor
     * @param name of the table.
     */
    public Table(String name){
        this.name = name;
        this.columns = new LinkedHashMap();
        this.tuples = new LinkedHashMap();
    }
    
    /**
     * Returns the name of the table object.
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * Adds a single columns to the table's schema.
     * @param type data type of column
     * @param name name of column.  
     * @param size the size of the column (optional)
     * @return 
     */
    public boolean addColumn(String type, String name, int size){
        Column newColumn = new Column(type, name, size);
        this.columns.put(name, newColumn);
        return true;
    }
    
    /**
     * Adds a single columns to the table's schema.
     * @param column a column object.
     * @return 
     */
    public boolean addColumn(Column column){
        this.columns.put(column.getName(), column);
        return true;
    }
    
    /**
     * Alters the definition of a column in the current table.
     * @param name name of the column.
     * @param type data type of the column.
     * @param size size of the column.
     * @return 
     */
    public boolean alterColumn(String name, String type, int size){
        return true;
    }
    
    /**
     * 
     * @param selectedColumns
     * @return the selected columns headers.
     */
    public String getColumns(List<String> selectedColumns){
        Column column;
        String allColumns = "";
        List<String> columnNames = new ArrayList<String>();
        
        if (!selectedColumns.contains("*")){
            columnNames = selectedColumns;
        } else {
            columnNames.addAll(this.columns.keySet());
        }        
        
        for(String col:columnNames){
               column =(Column) columns.get(col);
               allColumns += column + " | ";
            }
        allColumns = allColumns.substring(0,allColumns.length()-2);
        return (allColumns);
    }
    
    
    /**
     * Inserts a tuple.
     * @param values to be inserted.
     * @return boolean whether the tuple was inserted successfully or not.
     */
    public boolean insertTuple(String[] values){
        if(this.columns.size() != values.length){
            return false; 
        }
        LinkedHashMap cells = new LinkedHashMap();
        int i = 0;
        
        Set<String> columnNames = this.columns.keySet();
        for(String col:columnNames){
               cells.put(col, values[i]);
               i++;
         }
        int col_id = id.incrementAndGet();
        Tuple newTuple = new Tuple(cells);
        this.tuples.put(col_id, newTuple);
        
        System.out.println("1 new record inserted.");
        return true;
    }
    
    /**
     * Prints the values of selected columns selected tuples. 
     * @param selectedColumns the columns we want to display.
     * @param condition the condition for filtering tuples.
     */
    public void printTuples(List selectedColumns, Condition condition){
        
        Set<Integer> tups;
        
        if(condition==null){
            tups = this.tuples.keySet();
        }else{
            tups = findTuples(condition);
        }
        
        for(Integer tup:tups){
            System.out.println(this.tuples.get(tup).printTuple(selectedColumns));
        }
    }
    
    /**
     * Deletes a tuple(s) based on a condition.
     * @param condition the condition for filtering tuples.
     */
    public void deleteTuples(Condition condition){
        Set<Integer> filteredTuples = findTuples(condition);
        int count = 0;
        for(Integer tup: filteredTuples){
            this.tuples.remove(tup);
            count++;
        }
        System.out.println(count + " records deleted.");
    }
    
    /**
     * Updates the values of a column in a tuple(s).
     * @param condition the condition for filtering tuples.
     * @param columnName the name of columns whose values to update.
     * @param columnValue the new value of the columns.
     */
    public void updateTuples(Condition condition, String columnName, String columnValue){
        Set<Integer> filteredTuples = findTuples(condition);
        int count = 0;
        for(Integer tup: filteredTuples){
            this.tuples.get(tup).cells.put(columnName, columnValue);
            count++;
        }
        System.out.println(count + " records modified.");
    }
    
    /**
     * Filter tuples based on the condition and returns their keys.
     * @param condition the condition for filtering tuples.
     * @return the keys of the filtered tuples.
     */
    public Set findTuples(Condition condition){
        String conditionColumn = condition.columnName;
        String conditionValue = condition.conditionValue;
        String conditionType = condition.conditionType;
        
        Set<Integer> filteredTuples = new HashSet<Integer>();
        Set<Integer> tups = this.tuples.keySet();
        for(Integer tup:tups){
            Integer currentTupleId = tup;
            Tuple currentTuple = this.tuples.get(currentTupleId);
            Column currentColumn = (Column) this.columns.get(conditionColumn);
            
            switch(conditionType){
                case "=":
                    if (currentTuple.cells.get(conditionColumn).toString().equals(conditionValue)){
                        filteredTuples.add(currentTupleId);
                    }
                    break;
                case "!=":
                    if (!currentTuple.cells.get(conditionColumn).toString().equals(conditionValue)){
                        filteredTuples.add(currentTupleId);
                    }
                    break;
                case "<":
                    
                    if ((currentColumn.getType().equals("int")) || (currentColumn.getType().equals("float"))){
                        if (Float.parseFloat(currentTuple.cells.get(conditionColumn).toString()) < Float.parseFloat(conditionValue)){
                            filteredTuples.add(currentTupleId);
                        }
                    }
                    break;
                case ">":                    
                    if ((currentColumn.getType().equals("int")) || (currentColumn.getType().equals("float"))){
                        if (Float.parseFloat(currentTuple.cells.get(conditionColumn).toString()) > Float.parseFloat(conditionValue)){
                            filteredTuples.add(currentTupleId);
                        }
                    }
                    break;
                case "<=":
                    if ((currentColumn.getType().equals("int")) || (currentColumn.getType().equals("float"))){
                        if (Float.parseFloat(currentTuple.cells.get(conditionColumn).toString()) <= Float.parseFloat(conditionValue)){
                            filteredTuples.add(currentTupleId);
                        }
                    }
                    break;
                case ">=":
                    if ((currentColumn.getType().equals("int")) || (currentColumn.getType().equals("float"))){
                        if (Float.parseFloat(currentTuple.cells.get(conditionColumn).toString()) >= Float.parseFloat(conditionValue)){
                            filteredTuples.add(currentTupleId);
                        }
                    }
                    break;
                
            }
        }
        return filteredTuples;
    }
}
