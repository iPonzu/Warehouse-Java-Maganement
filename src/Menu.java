import java.util.Scanner;
import java.sql.SQLException;

public class Menu{
    public static void main(String[] args) {
        int op = 0;
        System.out.println("Welcome to our Warehouse Management System");

        do{
            System.out.println("1 - Register new employee");
            System.out.println("2 - Register new warehouse");
            System.out.println("3 - Register new balance");
            System.out.println("4 - List employees");
            System.out.println("5 - List warehouses");
            System.out.println("6 - List balances");

            try{
                Scanner scanner = new Scanner(System.in);
                op = scanner.nextInt();
            }catch (Exception e){
                op = 0;
            }
            switch (op) {
                case 1:
                    break;
            
                default:
                    break;
            }
        }while(op != 0);
    }
}