
package it2c.bolambot.lims;

import java.util.Scanner;


public class IT2CBOLAMBOTLIMS {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
       
        do{
            System.out.println("WELCOME TO LIBRARY INVENTORY");
            System.out.println("1. BOOKS ");
            System.out.println("2. INVENTORY ");
            System.out.println("3. EXIT ");

            
            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            IT2CBOLAMBOTLIMS book = new IT2CBOLAMBOTLIMS(); 
            
              while (action < 1 || action > 3) {
                System.out.print("Invalid selection, Try Again: ");
                action = sc.nextInt();
             }
            switch (action){
                case 1:
                    BookInfo bk = new BookInfo();
                    bk.bDetails();
                    break;  
                case 2: 
                    Inventory ss = new Inventory();
                    ss.cDetails();
                    
                case 3:
                    System.out.println("Exit Selected...type 'yes' to continue:" );
                    String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                    }
                    break;
                    
                    
                    
            }
            
            

        }while(exit);

    }
}
