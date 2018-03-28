package matterdb;
import java.io.*;
import java.util.*;
import matterdb.parser.SqlParser;

/**
 * University of Nevada Reno
 * Department of Computer Science and Engineering
 * CS657 Programming Assignment
 * Spring 2018
 * @author Isayas Adhanom
 * @version 1.0 03/27/2018
 */
public class DMatter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * We create a new Engine object here to hold all our databases which in turn hold 
         * all our tables, columns and rows. All operations will be done on this engine object.
         */
        Engine currentEngine = new Engine();
         
        boolean keepGoing = true;
        /**
         * We check if the database engine can be started first, before starting any operations. 
         * If the engine can not start we explain the error to the user and exit gracefully.
         */
        if(currentEngine.start()){           
            System.out.println("dMatter database engine started. Please input your SQL commands, terminated by a semicolon.\nEnter exit command to close.");
            while(true){
                try{  
                    /** The following  statements will initialize our SqlParser object 
                     *  and redirect our input stream to it's parser method. We continue 
                     * to do this until we encounter the exit command.
                     */
                    SqlParser parser = new SqlParser(System.in); 
                    keepGoing = parser.Start(System.out, currentEngine);                    
                    
                    if (!keepGoing) {
                        break;
                    }
                    //System.out.println(cmd);
                    //Pass the command object from our parser to our engine's commandProcessor method.
//                    currentEngine.commandProcessor(cmd);                               
                    
                    /** Catch all exceptions here, inform the user about the error and continue accepting input.
                     * Most exceptions here will ParseExceptions, which means they are syntax errors.
                     */
                    
                    } catch(Exception e){
                        System.out.println("There was an error: " + e );
                    }
            } 
        }  
        
        //Save all changes to file and stop our DB engine.
        currentEngine.close();
    }
    
}
