import java.util.*;
import java.sql.*;
import java.util.Scanner;

public class JdbcDemo {

   // Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   // static final String DB_URL = "jdbc:mysql://localhost/companydb";
   static final String DB_URL = "jdbc:mysql://localhost/shop?useSSL=false";
   // Database credentials
   static final String USER = "root";// add your user
   static final String PASS = "Kota@2020";// add password

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;
      try {
         Class.forName(JDBC_DRIVER);
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         stmt = conn.createStatement();
         Scanner scan = new Scanner(System.in); // Create a Scanner object

         clearScreen();//clearing the screen for menu
         /*BEGINNNG SCREEN*/
         System.out.println("+---------------------------------+");
         System.out.println("|      ~~WELCOME TO MYSHOP~~      |");
         System.out.println("+---------------------------------+");
         System.out.println("|        IMT2020003               |");
         System.out.println("+---------------------------------+");
         System.out.println("|        Karanjit Saha            |");
         System.out.println("+---------------------------------+");
         main_menu(stmt, scan);

         scan.close();
         stmt.close();
         conn.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   } // end main
   /*MAIN MENU SHOWN IN THE FIRST SCREEN*/
   static void main_menu(Statement stmt, Scanner scan) {
      System.out.println("|Please Select an option:         |");
      System.out.println("+---------------------------------+");
      System.out.println("|0.| Exit                         |");
      System.out.println("+---------------------------------+");
      System.out.println("|1.| Customer                     |");
      System.out.println("+---------------------------------+");
      System.out.println("|2.| Shop Staff                   |");
      System.out.println("+---------------------------------+");
      System.out.println("|3.| Shop Owner                   |");
      System.out.println("+---------------------------------+");

      System.out.print("\n\nENTER YOUR CHOICE : ");
      int choice = Integer.parseInt(scan.nextLine());
      clearScreen();

      switch (choice) {
         case 0:
            System.out.println("+---------------------------------------------------+");
            System.out.println("|Thank you for visiting us!!, Have a nice day!!     |");
            System.out.println("+---------------------------------------------------+");
            System.out.println("|                                         ~MYSHOP   |");
            System.out.println("+---------------------------------------------------+");
            System.exit(0);
         case 1:
            Customer_menu(stmt, scan);
            break;
         case 2:
            // check_ShopStaff(stmt, scan);
            ShopStaff_menu(stmt, scan);
            break;
         case 3:
            // check_super_admin(stmt, scan);
            super_admin_menu(stmt, scan);
            break;
         default:
            clearScreen();
            System.out.println("Your entered choice is invalid. Please enter a valid choice.\n");
            break;
      }
      main_menu(stmt, scan);
   }
   /*CUSTOMER MENU */
   static void Customer_menu(Statement stmt, Scanner scan) {
      System.out.println("+----------------------------------+");
      System.out.println("|Please select appropriate option: |");
      System.out.println("+----------------------------------+");
      System.out.println("|0.| Exit                          |");
      System.out.println("+----------------------------------+");
      System.out.println("|1.| List of available products    |");
      System.out.println("+----------------------------------+");
      System.out.println("|2.| Bill Amount                   |");
      System.out.println("+----------------------------------+");
      

      System.out.print("\n\nENTER YOUR CHOICE : ");
      int choice = Integer.parseInt(scan.nextLine());
      clearScreen();

      switch (choice) {
         case 0:
            return;
         case 1:
            list_of_products(stmt, scan, true);
            break;
         case 2:
            compute_bill(stmt, scan);
            break;
         default:
            clearScreen();
            System.out.println("Please Enter a Valid Choice!!\n");
            break;
      }
      Customer_menu(stmt, scan);
   }

   /* SHOP STAFF MENU */
   static void ShopStaff_menu(Statement stmt, Scanner scan) {
      System.out.println("+--------------------------------------+");
      System.out.println("|Please select appropriate option:     |");
      System.out.println("+--------------------------------------+");
      System.out.println("|0.| Exit                              |");
      System.out.println("+--------------------------------------+");
      System.out.println("|1.| List of all products              |");
      System.out.println("+--------------------------------------+");
      System.out.println("|2.| List of products presently in shop|");
      System.out.println("+--------------------------------------+");
      System.out.println("|3.| Sell a product                    |");
      System.out.println("+--------------------------------------+");
      System.out.println("|4.| Return a product                  |");
      System.out.println("+--------------------------------------+");
      System.out.println("|5.| Add a product                     |");
      System.out.println("+--------------------------------------+");
      System.out.println("|6.| Delete a product                  |");
      System.out.println("+--------------------------------------+");
      System.out.print("\n\nENTER YOUR CHOICE : ");
      int choice = Integer.parseInt(scan.nextLine());
      clearScreen();

      switch (choice) {
         case 0:
            return;
         case 1:
            list_of_products(stmt, scan, false);
            break;
         case 2:
            list_of_products(stmt, scan, true);
            break;
         case 3:
            sell_product(stmt, scan);
            break;
         case 4:
            return_product(stmt, scan);
            break;
         case 5:
            add_product(stmt, scan);
            break;
         case 6:
            delete_product(stmt, scan);
            break;
         default:
            clearScreen();
            System.out.println("Please Enter a Valid Choice!!\n");
            break;
      }
      ShopStaff_menu(stmt, scan);
   }
   /*OWNER MENU(SUPERADMIN) */
   static void super_admin_menu(Statement stmt, Scanner scan) {
      System.out.println("+--------------------------------------+");
      System.out.println("|Please select an appropriate option:  |");
      System.out.println("+--------------------------------------+");
      System.out.println("|0.| Exit                              |");
      System.out.println("+--------------------------------------+");
      System.out.println("|1.| List of Customers                 |");
      System.out.println("+--------------------------------------+");
      System.out.println("|2.| List of Shop staff members        |");
      System.out.println("+--------------------------------------+");
      System.out.println("|3.| Add a Customer                    |");
      System.out.println("+--------------------------------------+");
      System.out.println("|4.| Remove a Customer                 |");
      System.out.println("+--------------------------------------+");
      System.out.println("|5.| Add a Shop staff                  |");
      System.out.println("+--------------------------------------+");
      System.out.println("|6.| Delete a Shop staff               |");
      System.out.println("+--------------------------------------+");
      

      System.out.print("\n\nENTER YOUR CHOICE : ");
      int choice = Integer.parseInt(scan.nextLine());
      clearScreen();

      switch (choice) {
         case 0:
            return;
         case 1:
            list_of_Customers(stmt, scan);
            break;
         case 2:
            list_of_ShopStaffs(stmt, scan);
            break;
         case 3:
            add_customer(stmt, scan);
            break;
         case 4:
            delete_customer(stmt, scan);
            break;
         case 5:
            add_ShopStaff(stmt, scan);
            break;
         case 6:
            delete_ShopStaff(stmt, scan);
            break;
         default:
            clearScreen();
            System.out.println("Please Enter a Valid Choice!!\n");
            break;
      }
      super_admin_menu(stmt, scan);
   }
   /*GET LIST OF ALL PRODUCTS */
   static boolean list_of_products(Statement stmt, Scanner scan, boolean checkAvailable) {
      String sql = "select * from product";
      ResultSet rs = executeSqlStmt(stmt, sql);
      boolean noProducts = true;

      try {
         System.out.println("List of available products:\n");
         while (rs.next()) {
            String id = rs.getString("product_id");
            String name = rs.getString("product_name");
            Double price = rs.getDouble("product_price");
            String buyer = rs.getString("customer_id");

            if (checkAvailable) {
               if (buyer == null) {
                  System.out.println("Product ID : " + id);
                  System.out.println("Product Name: " + name);
                  System.out.println("Product Price: " + price);
                  System.out.println("");
                  noProducts = false;
               }
            } else {
               System.out.println("Product ID : " + id);
               System.out.println("Product Name: " + name);
               System.out.println("Product Price: " + price);
               System.out.println("Product Buyer : " + buyer);
               System.out.println("");
               noProducts = false;
            }
         }

         if (noProducts)
            System.out.println("Sorry, no products are available!\n");

         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return noProducts;
   }
   /*SELL A PRODUCT */
   static void sell_product(Statement stmt, Scanner scan) {
      try {
         boolean noProduct = list_of_products(stmt, scan, true);
         if (!noProduct) {
            System.out.print("\nEnter product id : ");
            String product_id = scan.nextLine();

            System.out.print("Enter customer id : ");
            String customer_id = scan.nextLine();

            clearScreen();

            String sql = String.format("UPDATE product SET customer_id = '%s' WHERE product_id = '%s'", customer_id, product_id);
            int result = updateSqlStmt(stmt, sql);

            if (result != 0)
               System.out.println("BUYER HAS BEEN UPDATED SUCCESFULLY!!\n");
            else
               System.out.println("SOMETHING WENT WRONG!\n");
         }
         else{
            System.out.println("Nothing to sell");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /*RETURN A PRODUCT */
   static void return_product(Statement stmt, Scanner scan) {
      try {
         System.out.print("\nEnter product id : ");
         String product_id = scan.nextLine();

         clearScreen();

         String sql = String.format("UPDATE product SET customer_id = NULL WHERE product_id = '%s'", product_id);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Product has been returned!!\n");
         else
            System.out.println("Something went wrong!\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /*ADD A PRODUCT */
   static void add_product(Statement stmt, Scanner scan) {
      try {
         System.out.print("\nEnter product name : ");
         String product_name = scan.nextLine();

         System.out.print("\nEnter product price : ");
         Double product_price = Double.parseDouble(scan.nextLine());
         clearScreen();

         String sql = String.format("INSERT INTO product(product_name,product_price) VALUES('%s', '%f');", product_name, product_price);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Product has been added successfully!!\n");
         else
            System.out.println("Something went wrong!\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /*DELETE A PRODUCT*/
   static void delete_product(Statement stmt, Scanner scan) {
      try {
         System.out.print("\nEnter product ID : ");
         String product_id = scan.nextLine();

         clearScreen();

         String sql = String.format("DELETE FROM product where product_id = '%s'", product_id);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Product has been deleted successfully!!\n");
         else
            System.out.println("Something went wrong!\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /*GET A LIST OF CUSTOMERS */
   static void list_of_Customers(Statement stmt, Scanner scan) {
      String sql = "select * from customer";
      ResultSet rs = executeSqlStmt(stmt, sql);

      try {
         System.out.println("List of customers:\n");
         while (rs.next()) {
            String customer_id = rs.getString("customer_id");
            String customer_name = rs.getString("customer_name");

            System.out.println("Customer Id : " + customer_id);
            System.out.println("Customer Name : " + customer_name);
            System.out.println("\n");
         }

         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   /*GET A LIST OF SHOF STAFF MEMBERS */
   static void list_of_ShopStaffs(Statement stmt, Scanner scan) {
      String sql = "select * from shop_staff";
      ResultSet rs = executeSqlStmt(stmt, sql);

      try {
         System.out.println("List of Shop Staff members:\n");
         while (rs.next()) {
            String staff_id = rs.getString("shop_staff_id");
            String staff_name = rs.getString("shop_staff_name");

            System.out.println("Staff Id : " + staff_id);
            System.out.println("Staff Name: " + staff_name);
            System.out.println("\n");
         }

         rs.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   /*ADD A CUSTOMER */
   static void add_customer(Statement stmt, Scanner scan) {
      try {
         // System.out.print("Enter Customer id : ");
         // String customer_id = scan.nextLine();
         System.out.print("Enter Customer name : ");
         String customer_name = scan.nextLine();

         clearScreen();

         String sql = String.format("INSERT INTO customer (customer_name)VALUES('%s')", customer_name);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Customer has been added successfully!!\n");
         else
            System.out.println("Something went wrong!\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /*ADD A SHOP STAFF  */
   static void add_ShopStaff(Statement stmt, Scanner scan) {
      try {
         // System.out.print("Enter Staff id : ");
         // String staff_id = scan.nextLine();
         System.out.print("Enter Staff name : ");
         String staff_name = scan.nextLine();
         System.out.print("Enter Joining date of Staff member : ");
         String staff_join_date = scan.nextLine();
         clearScreen();

         String sql = String.format("INSERT INTO shop_staff(shop_staff_name,shop_joining_date) VALUES( '%s', '%s')", staff_name, staff_join_date);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Staff has been added successfully!!\n");
         else
            System.out.println("Something went wrong!\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /*DELETE A CUSTOMER */
   static void delete_customer(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter customer id : ");
         String customer_id = scan.nextLine();

         clearScreen();

         String sql = String.format("DELETE FROM customer where customer_id = '%s'", customer_id);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Customer has been deleted successfully!!\n");
         else
            System.out.println("Something went wrong!\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /*DELETE A SHOP STAFF */
   static void delete_ShopStaff(Statement stmt, Scanner scan) {
      try {
         System.out.print("Enter Staff id : ");
         String staff_id = scan.nextLine();

         clearScreen();

         String sql = String.format("DELETE FROM shop_staff where shop_staff_id = '%s'", staff_id);
         int result = updateSqlStmt(stmt, sql);

         if (result != 0)
            System.out.println("Staff has been deleted successfully!!\n");
         else
            System.out.println("Something went wrong!\n");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   /*COMPUTING A BILL */
   static void compute_bill(Statement stmt, Scanner scan) {
      String sql;
      System.out.println("Enter Customer Id: ");
      String custom_id = scan.nextLine();

      try {
         sql = "select product_name , product_price from product where customer_id = '"+custom_id+"';";
         ResultSet rs = stmt.executeQuery(sql);
         Double bill_sum = 0.0;
         while (rs.next()) {
            String name = rs.getString("product_name");
            Double price = rs.getDouble("product_price");
            bill_sum += price;
            System.out.println(name + ":" + price);
         }
         sql = String.format("UPDATE customer set customer_bill = '%f' WHERE customer_id = '%s",bill_sum,custom_id);
         System.out.println("Total : " + bill_sum);
      } catch (SQLException se) {
         // Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) {
         // Handle errors for Class.forName
         e.printStackTrace();
      }
   }
   /*EXECUTES A SQL STATEMENT */
   static ResultSet executeSqlStmt(Statement stmt, String sql) {
      try {
         ResultSet rs = stmt.executeQuery(sql);
         return rs;
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }
   /*EXECUTE INSERT/UPDATE/DELETE */
   static int updateSqlStmt(Statement stmt, String sql) {
      try {
         int rs = stmt.executeUpdate(sql);
         return rs;
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return 0;
   }
   /*REMOVES ALL THE PREVIOUS OUTPUT ON THE SCREEN */
   static void clearScreen() {
      System.out.println("\033[H\033[J");
      System.out.flush();
   }
} 
