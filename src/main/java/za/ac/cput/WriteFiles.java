
package za.ac.cput;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class WriteFiles 
{
    ArrayList<Stakeholder> stakeholder;
    ArrayList<Customer> customer;
    ArrayList<Supplier> supplier;
    ObjectInputStream input;
    
    public void openFiles()
    {
        try
        {
            input = new ObjectInputStream( new FileInputStream( "stakeholder.ser" ) );               
        }
        catch (IOException ioe){
            System.out.println(ioe);
            System.exit(1);
        }
    }
    
    public void closeFiles()
    {
        try 
        {
            input.close();
        } 
        catch (IOException ioe)
        {            
            System.out.println(ioe);
        } 
    }
    
    public void readStakeholders()
    {
        stakeholder = new ArrayList<Stakeholder>();
        
        try 
        {
            while(true)
            {
                stakeholder.add((Stakeholder)input.readObject());
            }
        } 
        catch (EOFException eofe) 
        {
            System.out.println(eofe);
        }
        catch(ClassNotFoundException clfe)
        {
            System.out.println(clfe);
        }
        catch (IOException ioe)
        {            
            System.out.println(ioe);
        } 
        
    }
    
    public void populateCustomer()
    {
        customer = new ArrayList<Customer>();
        try
        {
            for (int i = 0; i < stakeholder.size(); i++) 
            {
                if (stakeholder.get(i) instanceof Customer) 
                {
                     customer.add((Customer)stakeholder.get(i));   
                }
            }
        }
        catch (Exception e)
        {            
            System.out.println(e);
        } 
    }
    
    public void sortCustomer()
    {
        customer.sort((c1, c2)
                      -> c1.getStHolderId().compareTo(
                          c2.getStHolderId()));
    }
   
    public void populateSupplier()
    {
        supplier = new ArrayList<Supplier>();
        try
        {
            for (int i = 0; i < stakeholder.size(); i++) 
            {
                if (stakeholder.get(i) instanceof Customer) 
                {
                     supplier.add((Supplier)stakeholder.get(i));   
                }
            }
        }
        catch (Exception e)
        {            
            System.out.println(e);
        } 
    }
    
    public void sortSupplier()
    {
        supplier.sort((s1, s2)
                      -> s1.getName().compareTo(
                          s2.getName()));
    }
    
    
    public void writeToFiles()
    {
        try{
            readStakeholders();
            populateCustomer();
            sortCustomer();
            populateSupplier();
            sortSupplier();
            System.out.println(customer);
            System.out.println(supplier);
        }
        catch (Exception e)
        {            
            System.out.println(e);
        } 
        finally
        {
            closeFiles();
        }
    }
    
}
