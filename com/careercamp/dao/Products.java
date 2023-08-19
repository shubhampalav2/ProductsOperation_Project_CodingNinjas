package com.careercamp.dao;
import java.util.Random;

public class Products{
int prodId;
String prodName;
double price;
int quantity;
double discount;
public static int prodIndex=0;
//Database for Products
public static Products productsDb[]=new Products[10]; 

public Products() //Constructor 
	{	
		Random R = new Random();
		prodId = R.nextInt();
		price =  Math.round(R.nextDouble()*100.0)/100.0;
		discount = Math.round(R.nextDouble()*100.0)/100.0;
		quantity = R.nextInt();
		prodName = generateString();
		prodIndex++;
	}

    private String generateString() { // for Product names
        char nameArray [] = new char[10];
        Random ran = new Random();
        nameArray[0] = (char)ran.nextInt(65,91); // For First Letter Capital
        for(int i=1;i<nameArray.length;i++)
        {
            
            nameArray[i]= (char) ran.nextInt(97, 123);
        }
        String ProdName = new String(nameArray);
        
        return ProdName;
}

public static void expand()							//Expanding Db
	{
		Products exp[] = new Products [productsDb.length];
		// Copy for backup
		for(int i=0; i<productsDb.length;i++)
		{
			exp[i]=productsDb[i];
		}
		productsDb = new Products[2*exp.length];
		//Loading back in Db
		for(int i=0; i<exp.length;i++)
		{
			productsDb[i]=exp[i];
		}
    
	}
	public static void create(int numProd)
	{
		
		
		for(int i=0;i<numProd;i++)
		{
			productsDb[i] = new Products();                           // Database Generated
		}
		return;
	}
	
	public void display()									//Display/Print
	{
		System.out.println("Product ID : "+ prodId);
		System.out.println("Product Name : "+ prodName);
		System.out.println("Price : "+ price);
		System.out.println("Quantity : "+ quantity);
		System.out.println("Discount : "+ discount);
		System.out.println();
	}
	public static void search(int Id)                        //Search by Id
	{
		try {
		int i;
		for( i=0;i<prodIndex;i++)
		{
			if(productsDb[i].prodId==Id)
			{
				productsDb[i].display();
				return;
			}
			
		}
		if(i==prodIndex)
		{
			throw new ProductsNotFoundException();
		}
		}
		catch(ProductsNotFoundException e)
		{System.out.println("Error : No such Product Found");}
		return;
		
	}
	public static void search(String name)                        //Search by Name
	{
		try {
		int i;
		for( i=0;i<prodIndex;i++)
		{
			if(productsDb[i].prodName.equals(name))
			{
				productsDb[i].display();
				return;
			}
			
		}
		if(i==prodIndex)
		{
			throw new ProductsNotFoundException();
		}
		}
		catch(ProductsNotFoundException e)
		{System.out.println("Error : No such Product Found");}
		return;
		
	}
	public static void update(int id,int value,int c)    //c is choice //Update prodID or quantity
	{
		try {
		int i;
		for( i=0;i<prodIndex;i++)
		{
			if(productsDb[i].prodId==id)
			{
				if(c==1)
				{
					productsDb[i].prodId=value;
				}
				else if(c==4)
				{
					productsDb[i].quantity=value;
				}
				return;
			}
			
		}
		if(i==prodIndex)
		{
			throw new ProductsNotFoundException();
		}
		}
		catch(ProductsNotFoundException e)
		{System.out.println("Error : No such Product Found");}
		
		return;
	}
	public static void update(int id,String str)                         //Update name
	{
		try {
		int i;
		for( i=0;i<prodIndex;i++)
		{
			if(productsDb[i].prodId==id)
			{
				productsDb[i].prodName=str;
				return;
			}
			
		}
		if(i==prodIndex)
		{
			throw new ProductsNotFoundException();
			
		}
		}
		catch(ProductsNotFoundException e)
		{System.out.println("Error : No such Product Found");}
		return;
	}
	public static void update(int id,double value,int c)    //c is choice //Update price or discount
	{
		try {
		int i;
		for( i=0;i<prodIndex;i++)
		{
			if(productsDb[i].prodId==id)
			{
				if(c==3)
				{
					productsDb[i].price=value;
				}
				else if(c==5)
				{
					productsDb[i].discount=value;
				}
				
				return;
			}
			
		}
		if(i==prodIndex)
		{
			throw new ProductsNotFoundException();
		}
		}
		catch(ProductsNotFoundException e)
		{System.out.println("Error : No such Product Found");}
		return;
	}

	public static void Menu()
	{
		System.out.println();
		System.out.println("What function do you want to use : ");
		System.out.println();
		System.out.println("Press 1 to display all products");
		System.out.println("Press 2 to add a product");
		System.out.println("Press 3 delete a product");
		System.out.println("Press 4 update a product");
		System.out.println("Press 5 search a product by Id");
		System.out.println("Press 6 search a product by Name");
		System.out.println("Press 7 display all products in order of price");
		System.out.println("Press 8 display all products in order of discount");
		System.out.println("Press 9 To restart");
		System.out.println("Press 0 To end");
		System.out.println();
		return;
		
	}
	
	public static void displaySort(String ch,int a) 
	{
		//a=0 => price | a=1 => discount
			quickSort(productsDb,0,prodIndex-1,a);
		
		if(ch.equals("A"))
		{
			for(int t=0;t<prodIndex;t++)
			{
				productsDb[t].display();
			}
		}	
		else if(ch.equals("D"))
		{
			for(int t=prodIndex-1;t>=0;t--)
			{
			  productsDb[t].display();
			}
		}
	}
				public static void quickSort(Products[] arr,int si,int ei, int a) 
				{
					if(si>=ei)
					{return;}
					int pi = partition(arr,si,ei,a);
					quickSort(arr,si,pi-1,a);
					quickSort(arr,pi+1,ei,a);
					return;
					
				}
				public static int partition(Products[] arr,int si,int ei, int a)
				{	int count=0;
					int i=si;
					int j=ei;
					if(a==0) //price
					{
						double pivot=arr[si].price;
						for(int k = si+1;k<=ei;k++ )
						{
							if(arr[k].price<pivot)
							{count++;}
						}
						Products temp=arr[si+count];
						arr[si+count] = arr[si];
						arr[si]=temp;
						
						while(i<j)
						{
							if(arr[i].price<pivot)
								{i++;}
							else if(arr[j].price>=pivot)
								{j--;}
							else
							{
								temp=arr[i];
								arr[i] = arr[j];
								arr[j]=temp;
								i++;
								j--;
							}
						}
					}
					else if(a==1)
					{
						double pivot=arr[si].discount;
						for(int k = si+1;k<=ei;k++ )
						{
							if(arr[k].discount<pivot)
							{count++;}
						}
						Products temp=arr[si+count];
						arr[si+count] = arr[si];
						arr[si]=temp;
						
						while(i<j)
						{
							if(arr[i].discount<pivot)
								i++;
							else if(arr[j].discount>pivot)
								j--;
							else
							{
								temp=arr[i];
								arr[i] = arr[j];
								arr[j]=temp;
								i++;
								j--;
							}
						
						}
					}
				
					return si+count;
	}
	public static void delete(int Id) 									//delete
	{
		try {
		int i;
		for( i=0;i<prodIndex;i++)
		{
			if(productsDb[i].prodId==Id)
			{	
				productsDb[i].prodId=productsDb[prodIndex-1].prodId;
				productsDb[i].prodName=productsDb[prodIndex-1].prodName;
				productsDb[i].price=productsDb[prodIndex-1].price;
				productsDb[i].quantity=productsDb[prodIndex-1].quantity;
				productsDb[i].discount=productsDb[prodIndex-1].discount;
				
				productsDb[prodIndex-1].prodId=0;
				productsDb[prodIndex-1].prodName="";
				productsDb[prodIndex-1].price=0.0;
				productsDb[prodIndex-1].quantity=0;
				productsDb[prodIndex-1].discount=0;
				prodIndex--;
				return;
			}
			
		}
		if(i==prodIndex)
		{
			throw new ProductsNotFoundException();
		}
		}
		catch(ProductsNotFoundException e)
		{System.out.println("Error : No such Product Found");}
		return;
	}
	
	public static void add(int numProd) 											//add
	{	
		if(numProd>=productsDb.length)
		{
			expand();
		}
		
		productsDb[numProd] = new Products();
		System.out.println();
		System.out.println("The following Product is added");
		productsDb[numProd].display();
		
	}
}