package matterdb;
import java.io.Serializable;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment 1
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 02/22/2018
 */
public class Column implements Serializable{
    private String type;
    private String name;
    private int size;
    
    public Column(String type, String name, int size){
        this.type = type;
        this.name = name;
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }   

    @Override
    public String toString() {
        return  (name + " " + type + ((size == 0)?"":("(" + size + ")")));
    }   
    
}