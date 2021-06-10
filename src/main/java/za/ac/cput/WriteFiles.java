
package za.ac.cput;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class WriteFiles 
{
    private ArrayList<Stakeholder> stakeholder;
    private  ArrayList<Customer> customer;
    private ArrayList<Supplier> supplier;
    private ObjectInputStream input;
    private FileWriter fw1;
    private BufferedWriter bw1;
    private FileWriter fw2;
    private BufferedWriter bw2;
    private int canRent;
    private int canNotRent;
    
    public void openFiles()
    {
        try
        {
            fw1 = new FileWriter("customerOutFile.txt"); 
            bw1 = new BufferedWriter(fw1);
            
            fw2 = new FileWriter("supplierOutFile.txt"); 
            bw2 = new BufferedWriter(fw2);
            
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
            bw1.close();
            bw2.close();
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
            System.out.println("EOF is reached");
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
                if (stakeholder.get(i) instanceof Supplier) 
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
       try
        { 
           supplier.sort((s1, s2)
                      -> s1.getName().compareTo(
                          s2.getName()));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void WriteSupplier()
    {
        try 
        {
            bw2.write("-----------------SUPPLIERS--------------\n");
            bw2.write("ID \tName \t\t\tProdType \t Description \n");
            bw2.write("--------------------------------------------\n");
           
            
            for (int i = 0; i < supplier.size(); i++) 
            {
                 bw2.write(supplier.get(i).getStHolderId() + "\t" + supplier.get(i).getName() + "\t\t" + supplier.get(i).getProductType() + "\t\t" + supplier.get(i).getProductDescription() +"\n");
            }
            
        } 
        catch (IOException ioe) 
        {
            System.out.println(ioe);
        }
    
    }
    
    public void calculateRent()
    {
        try 
        {
            for (int i = 0; i < customer.size(); i++) 
            {
                if(customer.get(i).getCanRent() == true)
                {
                    canRent ++;
                }
                else
                    if(customer.get(i).getCanRent() == false)
                    {
                        canNotRent ++;
                    }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void WriteCustomer()
    {
        try 
        {
            bw1.write("-----------------CUSTOMER--------------\n");
            bw1.write("ID \t Name \t Surname \t Date fo birth\n");
            bw1.write("---------------------------------------\n");
           
            
            for (int i = 0; i < customer.size(); i++) 
            {
                 bw1.write(customer.get(i).getStHolderId() + "\t" + customer.get(i).getFirstName() + "\t" + customer.get(i).getSurName() + "\t" + customer.get(i).getDateOfBirth() + "\n");
            }
            
            bw1.write("\ngit The number of cutomers who can rent: " + canRent +"\n");
            bw1.write("The number of cutomers who can not rent: " + canNotRent);
        } 
        catch (IOException ioe) 
        {
            System.out.println(ioe);
        }
    
    }
            
    
    public void writeToFiles()
    {
        try
        {
            readStakeholders();
            populateCustomer();
            sortCustomer();
            populateSupplier();
            sortSupplier();
            calculateRent();
            WriteCustomer();
            WriteSupplier();
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
