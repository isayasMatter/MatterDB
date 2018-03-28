package matterdb;
import java.io.Serializable;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment 1
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 03/27/2018
 */
public class Column implements Serializable{
    private String type;
    private String name;
    private int size;
    
    /**
     * Constructor
     * @param type data type of column.
     * @param name the name of the column.
     * @param size the size of the column.
     */
    public Column(String type, String name, int size){
        this.type = type;
        this.name = name;
        this.size = size;
    }

    /**
     * Gets the data type of the column object.
     * @return 
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the data type of the column object.
     * @param type 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the name of the column object.
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the column object.
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the size of the column object.
     * @return 
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the column object.
     * @param size 
     */
    public void setSize(int size) {
        this.size = size;
    }   

    @Override
    public String toString() {
        return  (name + " " + type + ((size == 0)?"":("(" + size + ")")));
    }   
    
}