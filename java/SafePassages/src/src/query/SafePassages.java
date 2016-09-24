package query;

import entity.Depository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SafePassages {

    private final Connection connection;

    public SafePassages(Connection connection){
        this.connection = connection;
    }

    private static final String SELECT_SCHOOL_NAMES =
            "select school_nam from safepassages";

    private static final String SELECT_DEPOSITORIES =
                "select name, streetaddress, st_x(geom), st_y(geom), city, zip, state, isschool, hasbreakfast, haslunch, hassupper, haspmsnack from depositories";

    public List<String> getSchoolNames()  {
        try {
            List<String> names = new ArrayList<>();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_SCHOOL_NAMES);
            while (rs.next()) {
                String name = rs.getString("school_nam");
                names.add(name);
            }

            return names;
        } catch (SQLException se){
            System.out.println("Error reading school names");
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public List<Depository> getDepositories(){
        try {
            List<Depository> depositories = new ArrayList<>();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_DEPOSITORIES);

            while(rs.next()){
                Depository depo = new Depository();

                depo.setName(rs.getString("name"));
                depo.setAddress(rs.getString("streetaddress"));
                depo.setCity(rs.getString("city"));
                depo.setxCoordinate(rs.getDouble("st_x"));
                depo.setyCoordinate(rs.getDouble("st_y"));
                depo.setZip(rs.getString("zip"));
                depo.setState(rs.getString("state"));
                depo.setSchool(rs.getInt("isschool") == 1);
                depo.setLunch(rs.getInt("hasLunch") == 1);
                depo.setPmSnack(rs.getInt("haspmsnack") == 1);
                depo.setBreakfast(rs.getInt("hasbreakfast") == 1);
                depo.setSupper(rs.getInt("hassupper") == 1);

                depositories.add(depo);
            }

            return depositories;

        } catch (SQLException se){
            System.out.println("Error reading school names");
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }



}
