/*
    RunWriteFiles.java
    This file contains the main method
    Wilbur Smith (220003033)
    8 June 2021
 */
package za.ac.cput;

public class RunWriteFiles 
{
    public static void main(String[] args) 
    {
        WriteFiles w1 = new WriteFiles();
        
        w1.openFiles();
        w1.writeToFiles();
    }
    
}
