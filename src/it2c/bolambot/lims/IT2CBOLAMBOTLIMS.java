
package it2c.bolambot.lims;

import java.util.Scanner;


public class IT2CBOLAMBOTLIMS {
      public void addBooks(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Book Title: ");
        String book_title = sc.next();
        System.out.print("Book Genre: ");
        String book_genre = sc.next();
         System.out.print("Book Author: ");
        String book_author = sc.next();
       

        String sql = "INSERT INTO BookInventory ( book_title, book_genre, book_author) VALUES (?, ?, ?)";

        conf.addRecord(sql, book_title, book_genre, book_author);
    }
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner (System.in);
        String response;
        
        do{
            System.out.println("1. Add:");
            System.out.println("2. View:");
            System.out.println("3. Update:");
            System.out.println("4. Delete:");
            System.out.println("5. Exit:");
            
            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            
            switch (action){
                case 1:
                    IT2CBOLAMBOTLIMS demo = new  IT2CBOLAMBOTLIMS();
                    demo.addBooks();
                    
                    break;
            }
            
            System.out.println("Continue (yes/no): ");
            response = sc.next();
            
        } while(response.equals("yes"));
        System.out.println("Thank You See You Soon!");
        
    }
    
}
