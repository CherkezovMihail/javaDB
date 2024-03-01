import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter username default (root): ");
//        String user = sc.nextLine();
//        user = user.equals("") ? "root" : user;
//        System.out.println();
//
//        System.out.print("Enter password default (empty):");
//        String password = sc.nextLine().trim();
//        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "12345678");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT * FROM employees WHERE salary < ?");

        String salary = sc.nextLine();
        stmt.setDouble(1, Double.parseDouble(salary));
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            System.out.println(rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getString("salary"));
        }
        connection.close();

    }
}