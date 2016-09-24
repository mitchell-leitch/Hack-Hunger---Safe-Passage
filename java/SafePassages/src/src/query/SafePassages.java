package query;

import entity.*;

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

    private static final String SELECT_SCHOOLS =
            "select school_nam, rt_num, schoolid, geom from safepassages";

    private static final String SELECT_DEPOSITORIES =
                "select name, streetaddress, st_x(geom), st_y(geom), city, zip, state, isschool, hasbreakfast, haslunch, hassupper, haspmsnack from depositories";

    private static final String SELECT_NEAR_DEPOSITORIES = "select sf.school_nam, sf.rt_num, d.name, d.streetaddress, st_distance(sf.geom::geography, d.geom::geography)\n" +
            "  from safepassages sf,\n" +
            "       depositories d\n" +
            "   where st_distance(sf.geom::geography, d.geom::geography) < 1000\n" +
            "     and sf.school_nam='";

    private static final String SELECT_DEPOSITORES_WITH_METRICS = "select  d.name, d.streetaddress, st_x(d.geom), st_y(d.geom), d.city, d.zip, d.state, d.isschool, d.hasbreakfast, d.haslunch, d.hassupper, d.haspmsnack, sp.school_nam nearestsafepassage, ST_DISTANCE(d.geom::geography, sp.geom::geography) as distance,\n" +
            "( \n" +
            " select avg(subdist.adp::float/subdist.eligibles::float) engagement \n" +
            " from public.depositories subd \n" +
            " inner join public.distributions subdist \n" +
            " on subd.streetaddress = subdist.streetaddress \n" +
            " and subd.streetaddress = d.streetaddress\n" +
            " ) engagement\n" +
            "\n" +
            "from public.depositories d, public.safepassages sp\n" +
            "\n" +
            "where sp.schoolid = (\n" +
            " select subsp.schoolid \n" +
            " from public.depositories subd, public.safepassages subsp \n" +
            " where subd.streetaddress=d.streetaddress \n" +
            " order by ST_DISTANCE(subd.geom, subsp.geom) \n" +
            " limit 1\n" +
            " )";

    private static final String SELECT_CRIMES = "select crimetime, st_x(geom), st_y(geom) from crimes";

    public List<Crime> getCrimes(){
        try {
            List<Crime> crimes = new ArrayList<>();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_CRIMES);

            System.out.println("Querying " + SELECT_CRIMES);

            while (rs.next()) {
                Crime crime = new Crime();

                crime.setxCoordinate(rs.getDouble("st_x"));
                crime.setyCoordinate(rs.getDouble("st_y"));
                crime.setCrimeTime(rs.getString("crimetime"));

                crimes.add(crime);
            }

            return crimes;
        } catch (SQLException se){
            System.out.println("Error reading crimes");
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public List<School> getSchoolNames()  {
        try {
            List<School> schools = new ArrayList<>();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_SCHOOLS);

            System.out.println("Querying " + SELECT_SCHOOLS);

            while (rs.next()) {
                School school = new School();

                school.setName(rs.getString("school_nam"));
                school.setSchoolId(rs.getLong("schoolid"));
                school.setRouteNumber(rs.getLong("rt_num"));
                school.setGeometry(rs.getString("geom"));

                schools.add(school);
            }

            return schools;
        } catch (SQLException se){
            System.out.println("Error reading school names");
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public List<MetricedDepository> getDepositoriesWithMetrics(){
        try {
            List<MetricedDepository> depositories = new ArrayList<>();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_DEPOSITORES_WITH_METRICS);

            System.out.println("QUERYING " + SELECT_DEPOSITORES_WITH_METRICS);

            while (rs.next()) {
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

                MetricedDepository metricedDepository = new MetricedDepository();
                metricedDepository.setEngagementScore(rs.getDouble("engagement"));
                metricedDepository.setNearestRouteDistance(rs.getDouble("distance"));
                metricedDepository.setDepository(depo);

                depositories.add(metricedDepository);
            }

            return depositories;

        } catch (SQLException se) {
            System.out.println("Error reading school names");
            se.printStackTrace();
            throw new RuntimeException(se);
        }

    }

    public List<SchoolDepositoryPair> findNearbyDepositories(String schoolName){
        try {

            System.out.println("school name " + schoolName);

            String sql = SELECT_NEAR_DEPOSITORIES + schoolName + "'";

            System.out.println("sql " + sql);

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            List<SchoolDepositoryPair> pairs = new ArrayList<>();

            while(rs.next()){
                School school = new School();
                Depository depository = new Depository();

                school.setName(rs.getString("school_nam"));
                school.setRouteNumber(rs.getLong("rt_num"));
                depository.setName(rs.getString("name"));
                depository.setAddress(rs.getString("streetaddress"));

                SchoolDepositoryPair pair = new SchoolDepositoryPair();
                pair.setDepository(depository);
                pair.setSchool(school);
                pair.setDistance(rs.getDouble("st_distance"));
                pairs.add(pair);
            }

            return pairs;
        } catch (SQLException se){
            System.out.println("Error reading school names");
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public List<Depository> getDepositories() {
        try {
            List<Depository> depositories = new ArrayList<>();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_DEPOSITORIES);

            while (rs.next()) {
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

        } catch (SQLException se) {
            System.out.println("Error reading school names");
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }
}
