
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import query.SafePassages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.eclipse.jetty.server.Server;
import web.*;

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

        ContextHandler schoolHandler = new ContextHandler();
        schoolHandler.setContextPath("/school_names");
        schoolHandler.setHandler(new SchoolNameHandler(safePassages));

        ContextHandler depositoryHandler = new ContextHandler();
        depositoryHandler.setContextPath("/vanillaDepositories");
        depositoryHandler.setHandler(new DepositoryHandler(safePassages));

        ContextHandler nearbyDepositoriesHandler = new ContextHandler();
        nearbyDepositoriesHandler.setContextPath("/nearbyDepositories");
        nearbyDepositoriesHandler.setHandler(new NearbyRepositoriesHandler(safePassages));

        ContextHandler metricedDepositoryHandler = new ContextHandler();
        metricedDepositoryHandler.setContextPath("/depositories");
        metricedDepositoryHandler.setHandler(new MetricedDepositoryHandler(safePassages));

        ContextHandler crimeHandler = new ContextHandler();
        crimeHandler.setContextPath("/crimes");
        crimeHandler.setHandler(new CrimeHandler(safePassages));

        ContextHandlerCollection handlerCollection = new ContextHandlerCollection();
        handlerCollection.setHandlers(
                new Handler[] {
                        schoolHandler,
                        depositoryHandler,
                        nearbyDepositoriesHandler,
                        metricedDepositoryHandler,
                        crimeHandler
                });

        server.setHandler(handlerCollection);

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

}
