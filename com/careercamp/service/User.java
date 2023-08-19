package com.careercamp.service;
import java.util.*;
import com.careercamp.dao.Products;

public class User {

public static void main(String []args){
//Number of Products we are taking as 10 
int numberProducts=10;
Products.create(numberProducts);

Scanner sc = new Scanner(System.in); 
int choice = 9;
while(choice>=1)
{	
    // choose from Menu
    switch(choice) 
    { 
        
        case 1 : 												// Display all
            for(int num=0;num<numberProducts;num++)
            {
                Products.productsDb[num].display();
            }
            Products.Menu();
            choice = sc.nextInt();
            break;
        case 2 : // Add a Product
            Products.add(numberProducts);					
            numberProducts++;
            Products.Menu();
            choice = sc.nextInt();
            break;
        case 3 : // delete a Product
            System.out.println("Enter Product Id of what you want to delete");
            int pid =sc.nextInt();
            Products.delete(pid);
            System.out.println("Deletion Success");
            numberProducts--;
            Products.Menu();
            choice = sc.nextInt();
            break;
        case 4 : //update a product
            System.out.println("Enter Product Id of what you want to update");
            pid =sc.nextInt();
            System.out.println("What value you want to update");
            System.out.println("Press 1 for Product Id");
            System.out.println("Press 2 for Product Name");
            System.out.println("Press 3 for Price");
            System.out.println("Press 4 for quantity");
            System.out.println("Press 5 for discount");
            int c=sc.nextInt();
            System.out.println("Enter Value");
            if(c==2)
            {
                Products.update(pid,sc.next());
            }
            else if(c==1 || c==4)
            {
                Products.update(pid,sc.nextInt(),c);
            }
            else if(c==3 || c==5)
            {
                Products.update(pid,sc.nextDouble(),c);
            }
            else
            {System.out.println("Invalid Choice");}
            Products.Menu();
            choice = sc.nextInt();
            break;
        case 5 : //search a product by Id
            System.out.println("Enter the Produt ID");
            Products.search(sc.nextInt());
            Products.Menu();
            choice = sc.nextInt();
            break;
        case 6 : //search a product by Name
            System.out.println("Enter the Produt Name");
            Products.search(sc.next());
            Products.Menu();
            choice = sc.nextInt();
            break;
        case 7 : //display all products in order of price
            System.out.println("Enter A for Ascending and D for Descending");
            String AD=sc.next();
            Products.displaySort(AD,0);
            Products.Menu();
            choice = sc.nextInt();
            break;
        case 8 : //display all products in order of discount
            System.out.println("Enter A for Ascending and D for Descending");
            AD=sc.next();
            Products.displaySort(AD,1);
            Products.Menu();
            choice = sc.nextInt();
            break;
        case 9 : // Reprint Menu
            Products.Menu();
            choice = sc.nextInt();
            break;
            
        default : 
            choice = sc.nextInt();
            break;
        
        
         
    } 

} 
System.out.println("The End");
sc.close();

}
}
