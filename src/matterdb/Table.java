package matterdb;
import java.util.*;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment 1
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 02/22/2018
 */
public class Table implements Serializable{
    private static final AtomicInteger id = new AtomicInteger(0); 
    private String name;     
    private LinkedHashMap columns;
    private LinkedHashMap<Integer, Tuple> tuples;
    
    public Table(String name){
        this.name = name;
        this.columns = new LinkedHashMap();
        this.tuples = new LinkedHashMap();
    }

    public String getName() {
        return name;
    }
    
    public boolean addColumn(String type, String name, int size){
        Column newColumn = new Column(type, name, size);
        this.columns.put(name, newColumn);
        return true;
    }
    
    public boolean addColumn(Column column){
        this.columns.put(column.getName(), column);
        return true;
    }
    
    public boolean alterColumn(String name, String type, int size){
        return true;
    }
    
    public String getColumns(){
        Column column;
        String allColumns = "";
        Set<String> columnNames = this.columns.keySet();
        for(String col:columnNames){
               column =(Column) columns.get(col);
               allColumns += column + " | ";
            }
        allColumns = allColumns.substring(0,allColumns.length()-2);
        return (allColumns);
    }
    
    public List columnOrder(){        
        return null;
    }
    
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
    
    public void printTuples(){
        Set<Integer> cols = this.tuples.keySet();
        
        for(Integer col:cols){
            System.out.println(this.tuples.get(col).printTuple());
        }
    }
    
    public void deleteTuples(Condition condition){
        Set<Integer> filteredTuples = findTuples(condition);
        int count = 0;
        for(Integer tup: filteredTuples){
            this.tuples.remove(tup);
            count++;
        }
        System.out.println(count + " records deleted.");
    }
    
    public void updateTuples(Condition condition, String columnName, String columnValue){
        Set<Integer> filteredTuples = findTuples(condition);
        int count = 0;
        for(Integer tup: filteredTuples){
            this.tuples.get(tup).cells.put(columnName, columnValue);
            count++;
        }
        System.out.println(count + " records modified.");
    }
    
    public Set findTuples(Condition condition){
        String conditionColumn = condition.columnName;
        String conditionValue = condition.conditionValue;
        String conditionType = condition.conditionType;
        
        Set<Integer> filteredTuples = new HashSet<Integer>();
        Set<Integer> tups = this.tuples.keySet();
        for(Integer tup:tups){
            Integer currentTupleId = tup;
            Tuple currentTuple = this.tuples.get(currentTupleId);
            
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
                    break;
                case ">":
                    break;
                case "<=":
                    break;
                case ">=":
                    break;
                
            }
        }
        return filteredTuples;
    }
}
