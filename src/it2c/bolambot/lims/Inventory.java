package it2c.bolambot.lims;

import java.sql.*;
import java.util.Scanner;

public class Inventory {

    
        public void cDetails() {
        Scanner sc = new Scanner(System.in);
        String response;
        
      do {
            System.out.println("\n---------------------------");
            System.out.println("INVENTORY PANEL             |");
            System.out.println("1. ADD INVENTORY            |");
            System.out.println("2. VIEW INVENTORY           |");
            System.out.println("3. UPDATE INVENTORY         |");
            System.out.println("4. DELETE INVENTORY         |");
            System.out.println("5. EXIT                     |");
            System.out.println("---------------------------");
            
            System.out.print("Enter Selection: ");
            int action = sc.nextInt();
            Inventory ss = new Inventory();
            
            
              while (action < 1 || action > 5) {
              System.out.print("Invalid selection, Try Again: ");
              action = sc.nextInt();
             }
      switch (action){
          
          case 1:
               ss.addInventory();
               ss.viewInventory();

              break;
          
          case 2:
               ss.viewInventory();
  
              break;
          
            case 3:
                ss.viewInventory();
                ss.updateInventory();
                ss.viewInventory();
                break;
         

            case 4:
                ss.viewInventory();
                ss.deleteInventory();
                ss.viewInventory();
              break;

            case 5:
              
              break;
              

      }
      System.out.print("Do you want to continue?(yes/no): ");
      response = sc.next();
      
  }   while(response.equalsIgnoreCase("yes"));
}
    private void addInventory() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        BookInfo bk = new BookInfo();
        bk.viewBook();
        
        System.out.print("Enter the ID of the Book: ");
        int id = sc.nextInt();
        
        String sql = "SELECT b_id FROM BookInfo WHERE b_id=?";
        
        while (conf.getSingleValue(sql, id) == 0) {
            System.out.println("Selected ID doesn't exist.");
            System.out.print("Select Book ID Again: ");
            id = sc.nextInt();
        }
        
     int i_tcopies;
        while (true) {
            System.out.print("Enter Total Copies: ");
            if (sc.hasNextInt()) {
                i_tcopies = sc.nextInt();
                if (i_tcopies > 0) {
                    break; 
                } else {
                    System.out.println("Total copies must be a positive integer.");
                }
            } else {
                System.out.println("Please enter a valid number for total copies.");
                sc.next(); 
            }
    }

    
    int i_bcopies;
    while (true) {
        System.out.print("Enter Borrowed Copies: ");
        if (sc.hasNextInt()) {
            i_bcopies = sc.nextInt();
            if (i_bcopies >= 0 && i_bcopies <= i_tcopies) {
                break; 
            } else {
                System.out.println("Borrowed copies cannot exceed total copies.");
            }
        } else {
            System.out.println("Please enter valid number for borrowed copies.");
            sc.next(); 
        }
    }

   
    int i_dcopies;
    while (true) {
        System.out.print("Enter Damaged Copies: ");
        if (sc.hasNextInt()) {
            i_dcopies = sc.nextInt();
            if (i_dcopies >= 0 && i_dcopies <= i_tcopies) {
                break; 
            } else {
                System.out.println("Damaged copies cannot exceed total copies.");
            }
        } else {
            System.out.println("Please enter valid number.");
            sc.next(); 
        }
    }

    
    int i_stocks = i_tcopies - (i_bcopies + i_dcopies);
    if (i_stocks < 0) {
        System.out.println("Borrowed and damaged copies exceed total copies. Please review your input.");
        return; 
    }

    
    String qry = "INSERT INTO Inventory (b_id, i_tcopies, i_bcopies, i_dcopies, i_stocks) VALUES (?, ?, ?, ?, ?)";
    conf.addRecord(qry, id, i_tcopies, i_bcopies, i_dcopies, i_stocks);
    
    System.out.println("Inventory added successfully.");
    System.out.println("Total stocks left: " + i_stocks);
}
    
    public void viewInventory() {
        String qry = "SELECT i_id, b_isbn, b_title, i_tcopies, i_bcopies, i_dcopies, i_stocks FROM Inventory " +
                     "LEFT JOIN BookInfo ON BookInfo.b_id = Inventory.b_id";
        String[] hrds = {"IID", "ISBN", "Title", "Total Copies", "Borrowed Copies", "Damaged Copies", "Stocks Left"};
        String[] clmns = {"i_id", "b_isbn", "b_title", "i_tcopies", "i_bcopies", "i_dcopies", "i_stocks"};
        
        config conf = new config();
        conf.viewRecords(qry, hrds, clmns);
    }
    
   
     private void updateInventory(){
         
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter Book ID: ");
        int i_id = sc.nextInt();
        
        String sql = "SELECT i_id FROM Inventory WHERE i_id = ?";
        while (conf.getSingleValue(sql, i_id) == 0) {

              System.out.print("Selected ID doesn't exist");
              System.out.print("Select Book ID Again: ");
              i_id =sc.nextInt();
        }
         
        System.out.print("Enter new Total Copies: ");
        int i_tcopies = sc.nextInt();
         
        System.out.print("Enter Borrowed Copies: ");
        int i_bcopies = sc.nextInt();
    
        System.out.print("Enter Damaged Copies: ");
        int i_dcopies = sc.nextInt();
        
        int i_stocks = i_tcopies - (i_bcopies + i_dcopies);
        
        String qry = "UPDATE Inventory SET i_tcopies = ?, i_bcopies = ?, i_dcopies = ?, i_stocks = ?WHERE i_id = ?";
        conf.updateRecord(qry, i_tcopies, i_bcopies, i_dcopies, i_stocks, i_id);
         
        System.out.println("Inventory added successfully.");
        System.out.println("Total stocks left: " + i_stocks);
             
     }
      public void deleteInventory(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("Enter Inventory ID to delete: ");
        int i_id = sc.nextInt();
        
        
        String sql = "SELECT i_id FROM Inventory WHERE i_id = ?";
        while (conf.getSingleValue(sql, i_id) == 0) {

              System.out.print("Selected ID doesn't exist");
              System.out.print("Select Book ID Again: ");
              i_id =sc.nextInt();
        }
        
         String sqlDelete = "DELETE FROM Inventory WHERE i_id = ?";
         
         conf.deleteRecord(sqlDelete, i_id);
       
      }
}

