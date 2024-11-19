
package it2c.bolambot.lims;

import java.util.Scanner;

public class Reports {

    public void genReport() {
      Scanner sc = new Scanner(System.in);
      String response;
      
      do{
      
            System.out.println("\n---------------------------");
            System.out.println("REPORTS PANEL               |");
            System.out.println("1. VIEW ALL BOOKS           |");
            System.out.println("2. INVENTORY SUMMARY        |");
            System.out.println("3. VIEW BOOKS BY CATEGORY   |");
            System.out.println("4. EXIT                     |");
            System.out.println("---------------------------");
            
      System.out.print("Enter Selection: ");
      int action= sc.nextInt();
      Reports rp = new Reports ();
      
      while (action < 1 || action > 4) {
                System.out.print("Invalid selection, Try Again: ");
                action = sc.nextInt();
             }
      switch (action){
          
          case 1:
               rp.viewAllBooks();
              
              break;
          
          case 2:
             rp.inventorySummary();

              
              break;
          
          case 3:
              rp.booksByCategory();;
              
              break;
          
          case 4:
              
              break;
              

      }
      System.out.print("Do you want to continue?(yes/no): ");
      response = sc.next();
      
  }   while(response.equalsIgnoreCase("yes"));
}
    
    private void viewAllBooks() {
        
        String qry = "SELECT b_id, b_isbn, b_title, b_author, b_category, b_publisher, b_publicationyr FROM BookInfo";
        String[] headers = {"ID", "ISBN", "Title", "Author", "Category", "Publisher", "Publication Year"};
        String[] columns = {"b_id", "b_isbn", "b_title", "b_author", "b_category", "b_publisher", "b_publicationyr"};
        
        config conf = new config();
        conf.viewRecords(qry, headers, columns);
    }

  private void inventorySummary() {
    String qry = "SELECT BookInfo.b_id,  b_title, SUM(Inventory.quantity) AS total_quantity " +
                 "FROM Inventory " +
                 "LEFT JOIN BookInfo ON BookInfo.b_id = Inventory.b_id " +
                 "WHERE Inventory.t_type = 'restock' " +
                 "GROUP BY BookInfo.b_id, BookInfo.b_isbn, BookInfo.b_title";
    String[] headers = {"ID", "Title", "Total Quantity"};
    String[] columns = {"b_id", "b_title", "total_quantity"};
    
    config conf = new config();
    conf.viewRecords(qry, headers, columns);
}



    private void booksByCategory() {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book Category (Genre): ");
        String category = sc.nextLine();

        String qry = "SELECT b_id, b_isbn, b_title, b_author, b_category FROM BookInfo " +
                     "WHERE b_category = ?";
        String[] hrds = {"ID", "ISBN", "Title", "Author", "Category"};
        String[] clmns = {"b_id", "b_isbn", "b_title", "b_author", "b_category"};
        
        config conf = new config();
        conf.viewRecords(qry, hrds, clmns, category);


    }

}