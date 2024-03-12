package villiansName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class AddMinion {

    private static final String GET_MINION_BY_NAME = "select * from minions where name = '?';";
    private static final String ADD_MINION = "insert into minions(name, age) values(?, ?)";
    private static final String GET_VILLAIN_BY_NAME = "select * from villains where name = '?';";
    private static final String GET_TOWN_BY_NAME = "select * from towns where name = '?';";

    public static void main(String[] args) throws SQLException {

        Connection mysqlConnection = Utils.getSqlConnection();

        Scanner scanner = new Scanner(System.in);

        String minionsInput = scanner.nextLine();

        String minionInfo = minionsInput.substring(8);
        String[] minionArgs = minionInfo.split(" ");

        String minionName = minionArgs[0];
        int minionAge = Integer.parseInt(minionArgs[1]);
        String city = minionArgs[2];
        String villainName = Arrays.stream(scanner.nextLine().split(": ")).toList().get(1);

        PreparedStatement statementForVillain = mysqlConnection.prepareStatement(GET_VILLAIN_BY_NAME);
        PreparedStatement statementForTown = mysqlConnection.prepareStatement(GET_TOWN_BY_NAME);

        if (!hasMinion(mysqlConnection)) {
            PreparedStatement statementForAddingMinion = mysqlConnection.prepareStatement(ADD_MINION);
            statementForAddingMinion.setString(1, minionName);
            statementForAddingMinion.setInt(2, minionAge);
            System.out.println(statementForAddingMinion.toString());

            statementForAddingMinion.executeUpdate();

            System.out.println(hasMinion(mysqlConnection));

        }



        System.out.println();

    }

    static boolean hasMinion (Connection mysqlConnection) throws SQLException {

        PreparedStatement statementForMinion = mysqlConnection.prepareStatement(GET_MINION_BY_NAME);
        ResultSet resultForMinion = statementForMinion.executeQuery();

        return resultForMinion.next();
    }

}
