package ro.siit;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleJdbcConnection {

    public static void main(String[] args) {
        /*
        // inainte de JDBC 4, era nevoie de:
        try {
            // FQN - fully qualified name
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            System.err.println("Can't load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        }
        */

        try (Connection conn = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/company?user=postgres&password=postgres")) {
            PreparedStatement ps = conn.prepareStatement("select first_name from employees");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
