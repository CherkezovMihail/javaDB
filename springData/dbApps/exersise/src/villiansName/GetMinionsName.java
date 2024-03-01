package villiansName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionsName {

    private static final String VILLAIN_LABEL = "name";

    private static final String GET_MINIONS_NAME_AND_AGE = "select m.name, m.age " +
            "from minions as m " +
            "join minions_villains mv on m.id = mv.minion_id " +
            "where mv.villain_id = ?;";

    private static final String GET_VILLAIN_NAME = "select name from villains where id = ?;";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        final Connection sqlConnection = Utils.getSqlConnection();

        PreparedStatement psForMinions = sqlConnection.prepareStatement(GET_MINIONS_NAME_AND_AGE);
        PreparedStatement psForVillain = sqlConnection.prepareStatement(GET_VILLAIN_NAME);

        int villainId = Integer.parseInt(scanner.nextLine());

        psForMinions.setInt(1, villainId);
        psForVillain.setInt(1, villainId);

        ResultSet resultSet = psForMinions.executeQuery();
        ResultSet rs = psForVillain.executeQuery();

        if (rs.next()) {
            System.out.println("Villain: " + rs.getString(VILLAIN_LABEL));

        } else {
            System.out.println("No villain with ID 10 exists in the database.");
        }

        int row = 1;

        while (resultSet.next()) {
            String minionName = resultSet.getString("name");
            int age = resultSet.getInt("age");

            System.out.printf("%d. %s %d%n", row, minionName, age);

            row ++;
        }

        sqlConnection.close();
    }
}
