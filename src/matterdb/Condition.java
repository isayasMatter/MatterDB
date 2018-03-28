/*
 * This file was prepared as part of the CS657 HW assignment  at University of Nevada, Reno by Isayas Adhanom.
 * Use it at your own risk.
 * Spring 2018.
 */
package matterdb;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 03/27/2018
 */
public class Condition {
    public String columnName;
    public String conditionType;
    public String conditionValue;
    
    /**
     * Constructor
     */
    public Condition() {
        this.columnName = "";
        this.conditionType = "";
        this.conditionValue = "";
    }

    @Override
    public String toString() {
        return "Condition{" + "columnName=" + columnName + ", conditionType=" + conditionType + ", conditionValue=" + conditionValue + '}';
    }
    
    
}
