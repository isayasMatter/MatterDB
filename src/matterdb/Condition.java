/*
 * This file was prepared as part of the CS657 HW1 assignment  at University of Nevada, Reno byIsayas Adhanom.
 * Use it at your own risk.
 * Spring 2018.
 */
package matterdb;

/**
 *
 * @author user
 */
public class Condition {
    public String columnName;
    public String conditionType;
    public String conditionValue;

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
