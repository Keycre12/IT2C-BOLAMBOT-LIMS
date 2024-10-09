
package it2c.bolambot.lims;

import java.util.Scanner;


public class IT2CBOLAMBOTLIMS {
      public void addBook(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Book Title: ");
        String book_title = sc.next();
        
        System.out.print("Book Genre: ");
        String book_genre = sc.next();
         System.out.print("Book Author: ");
        String book_author = sc.next();
       

        String sql = "INSERT INTO BookInventory ( book_title, book_genre, book_author) VALUES (?, ?, ?)";
        config conf = new config();
        conf.addBook(sql, book_title, book_genre, book_author);
    }
     public void viewBooks(){
   
    String cqry = "SELECT book_id, book_title, book_genre, book_author FROM BookInventory";
    String[] hrds = {"ID", "Title", "Genre", "Author"};
    String[] clmns = {"book_id", "book_title", "book_genre", "book_author"};

    config conf = new config();
    conf.viewBooks(cqry, hrds, clmns);
    }

     
     private void updateBook(){
         Scanner sc = new Scanner(System.in);
         System.out.println("Enter Book ID: ");
         int id = sc.nextInt();
         
         System.out.print("Enter new Book Title: ");
         String book_title = sc.next();
         
         System.out.print("Enter new Book Genre: ");
         String book_genre = sc.next();
         
          System.out.print("Enter new Book Author: ");
         String book_author = sc.next();
         
         String qry = "UPDATE BookInventory SET book_title = ?, book_genre = ?, book_author = ? WHERE book_id = ? ";
         
         config conf = new config();
         conf.updateBooks(qry, book_title, book_genre, book_author, id);
             
     }
      public void DeleteBook(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt();
        
        
         String sqlDelete = "DELETE FROM BookInventory WHERE book_id = ?";
         config conf = new config();
         conf.deleteBook(sqlDelete, id);
       
}
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        IT2CBOLAMBOTLIMS book = new IT2CBOLAMBOTLIMS(); 
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
                    book.addBook();
                    
                    break;
                    
                case 2:
                   book.viewBooks();
                    break;
                    
                    case 3:
                   book.viewBooks();
                   book.updateBook();
                    break;
                    
                    case 4:
                    book.viewBooks();
                    book.DeleteBook();
                    book.viewBooks();
                    break;
                    
                    case 5: 
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid action. Please try again.");
            }
            
            System.out.print("Continue (yes/no): ");
            response = sc.next();
            
        } while(response.equalsIgnoreCase("yes"));
        System.out.println("Thank You See You Soon!");
        
    }
    
}
