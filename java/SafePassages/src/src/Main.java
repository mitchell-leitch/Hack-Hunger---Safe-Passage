
import query.SafePassages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import web.SchoolNameHandler;

public class Main {
    public static void main(String[] args){
        System.out.println("Starting");

        try {

            Connection connection = connectToDatabase();
            SafePassages safePassages = new SafePassages(connection);
            startWebServer(safePassages);

        } catch (SQLException se){
            System.out.println("Error getting connection");
            se.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            System.out.println("Error finding classes");
            cnfe.printStackTrace();
        } catch (Exception ex){
            System.out.println("Unknown error");
            ex.printStackTrace();
        }
    }

    private static void startWebServer(SafePassages safePassages) throws Exception {
        System.out.println("Starting web server");
        Server server = new Server(8080);
        server.setHandler(new SchoolNameHandler(safePassages));

        server.start();
        server.join();
    }


    private static Connection connectToDatabase() throws SQLException, ClassNotFoundException {
        System.out.println("Connecting to DB.");
        Class.forName("org.postgresql.Driver");
        System.out.println("Found driver");

        String url = "jdbc:postgresql://10.1.106.228/safepassage";
        Properties props = new Properties();

        props.setProperty("user", "safepassageuser");
        props.setProperty("password", "safepassage");
        Connection conn = DriverManager.getConnection(url, props);
        System.out.println("Connected. " + conn);
        return conn;
    }

    private static void printOutSchoolNames(Connection connection) throws SQLException {
        SafePassages sf = new SafePassages(connection);
        List<String> schoolNames = sf.getSchoolNames();
        for(String name : schoolNames){
            System.out.println(name);
        }
    }

}
