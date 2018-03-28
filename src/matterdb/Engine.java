package matterdb;
import java.util.*;
import java.io.*;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment 1
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 02/22/2018
 */
public class Engine implements Serializable{
    private Database currentDb;
    private static HashMap<String, Database> databases;
    private static final String FILE_NAME = "dbmatter_file.dbm"; 
    
    /**
     * Constructor
     */
    public Engine(){
        databases = new HashMap<String, Database>();        
    }
   
    /**
     * Creates a new database and adds it to the list of databases.
     * @param name the name of the new database.
     * @return boolean whether the database was created successfully or not.
     */
    public boolean createDb(String name){
        if(databases.containsKey(name)){
            System.out.println("!Failed to create database " + name + " because it already exists.");
            return false;
        }else{            
            Database newDb = new Database(name);
            databases.put(name, newDb);
            System.out.println("Database " + name + " created!" );
            saveDatabases();            
            return true;  
        }
    }
    
    /**
     * 
     * @param name
     * @return 
     */
    public boolean useDb(String name){
        if(databases.containsKey(name)){
            Database db = databases.get(name);
            currentDb = db;
            System.out.println("Using Database " + name);
            return true;
        } else{
            System.out.println("!Failed to use database " + name + " because it does not exist.");
            return false;
        }
    }
    
    /**
     * 
     * @param name
     * @return 
     */
    public boolean dropDb(String name){
        if(databases.containsKey(name)){
            databases.remove(name);
            System.out.println("Database " + name + " deleted.");
            saveDatabases();
            return true;
        }else{
            System.out.println("!Failed to delete database " + name + " because it does not exist.");
            return false;
        }
    }    
    
    /**
     * 
     * @return 
     */
    public boolean showDbs(){
        // Get a keyset of the entries
      Set<String> names = databases.keySet();      
            
      // Display databases
      for(String name:names){         
         System.out.println(name);         
      }
      System.out.println();
      return true;
    }
    
    /**
     * 
     * @param cmd 
     */
    public void createTable(Command cmd){
        currentDb.createTable(cmd.getObjectName(), cmd.getColumnList());  
        saveDatabases();
    }
    
    /**
     * 
     * @param tableName 
     */
    public void dropTable(String tableName){
        currentDb.dropTable(tableName);
        saveDatabases();
    }
    
    /**
     * 
     * @param cmd 
     */
    public void alterTable(Command cmd){
        currentDb.alterTable(cmd.getObjectName(), cmd.getColumnList());
    }
    
    /**
     * 
     * @param cmd 
     */
    public void selectTable(Command cmd){
        currentDb.selectTable(cmd.getObjectName());
    }
    
    public void insertIntoTable(Command cmd){
        List<String> cellValues = cmd.getValueList();
        String[] values = new String[cellValues.size()];
        values = cellValues.toArray(values);

        currentDb.insertIntoTable(cmd.getObjectName(), values);
        saveDatabases();
    }
    
    public void deleteFromTable(Command cmd){
        currentDb.deleteFromTable(cmd.getObjectName(), cmd.getCondition());
        saveDatabases();
    }
    
    public void updateTable(Command cmd){
        currentDb.updateTable(cmd);
        saveDatabases();
    }
    
    /**
     * 
     * @param cmd
     * @return 
     */
    public boolean commandProcessor(Command cmd){
        String cmdType = cmd.getCommandType();
        String objName = cmd.getObjectName();
        List columns = cmd.getColumnList();
        
        switch(cmdType){
            case "CREATE_DATABASE":
                createDb(objName);
                break;
            case "USE_DATABASE":
                useDb(objName);                
                break;
            case "DROP_DATABASE":
                dropDb(objName);
                break;
            case "SHOW_DATABASES":
                showDbs();
                break;
            case "CREATE_TABLE":
                if (currentDb != null){
                    createTable(cmd);
                } else{
                    System.out.println("You are not using any database. Please select a database to use first.");
                }
                break;
            case "DROP_TABLE":
                if (currentDb != null){
                    dropTable(objName);
                } else{
                    System.out.println("You are not using any database. Please select a database to use first.");
                }
                break;
            case "ALTER_TABLE":
                if (currentDb != null){
                    alterTable(cmd);
                } else{
                    System.out.println("You are not using any database. Please select a database to use first.");
                }
                break;
            case "SELECT":
                if (currentDb != null){
                    selectTable(cmd);
                } else{
                    System.out.println("You are not using any database. Please select a database to use first.");
                }
                break;
            case "INSERT":
                if (currentDb != null){
                    insertIntoTable(cmd);
                }else{
                    System.out.println("You are not using any database. Please select a database to use first.");
                }
                break;
            case "DELETE":
                if (currentDb != null){
                    deleteFromTable(cmd);
                }else{
                    System.out.println("You are not using any database. Please select a database to use first.");
                }
                break;
            case "UPDATE":
                if (currentDb != null){
                    updateTable(cmd);
                }else{
                    System.out.println("You are not using any database. Please select a database to use first.");
                }
                break;
            default:
                System.out.println("Command not recognized. Please try again.");
                break;
        }
        return true;
    }
    
    /**
     * 
     * @return 
     */
    public boolean start(){        
        try
        {   
            File tmp = new File(FILE_NAME);
            boolean exists = tmp.exists();
            
            if(!exists){
                System.out.println("Database file does not exist. Creating a new one ...");
                saveDatabases();
            }
            // Reading the object from a file
            FileInputStream file = new FileInputStream(FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(file);
             
            // Method for deserialization of object
            databases = (HashMap)in.readObject();
             
            in.close();
            file.close();             
           
        }
         
        catch(IOException ex)
        {
            System.out.println("The database engine could not start. The database file is missing or corrupt.");
            return false;
        }
         
        catch(ClassNotFoundException ex)
        {
            System.out.println("The database engine could not start. The database file seems to have a different structure.");
            return false;
        }        
        return true;
    }
    
    /**
     * 
     * @return 
     */
    public boolean close(){ 
        if(saveDatabases()){
            System.out.println("Database engine closed successfuly.");            
        }
        return true;
    }
    /**
     * 
     * @return 
     */
    private boolean saveDatabases(){
        try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Method for serialization of object
            out.writeObject(databases);
             
            out.close();
            file.close();           
        }
         
        catch(IOException ex)
        {
            System.out.println("Error in saving database file. " + ex.getMessage());
            return false;
        }
        
        return true;
    }
    
}
