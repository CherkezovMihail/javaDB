package villiansName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainsName {
    private static final String GET_VILLAINS_NAMES = "select v.name, count(distinct mv.minion_id) c " +
            "from villains as v " +
            "join minions_villains mv on v.id = mv.villain_id " +
            "group by v.id " +
            "having c > ? " +
            "order by c desc; ";

    private static final String VILLAIN_LABEL = "name";
    private static final String COUNT_LABEL = "c";

    public static void main(String[] args) throws SQLException {

        Connection sqlConnection = Utils.getSqlConnection();

        final PreparedStatement stm = sqlConnection.prepareStatement(GET_VILLAINS_NAMES);

        stm.setInt(1, 15);

        ResultSet resultSet = stm.executeQuery();

        while (resultSet.next()) {
            String villainName = resultSet.getString(VILLAIN_LABEL);
            int countOfMinions = resultSet.getInt(COUNT_LABEL);

            System.out.println(villainName + " " + countOfMinions);
        }

        sqlConnection.close();
    }
}
