package database;

import controllers.HelloApplication;
import entities.*;
import exceptions.DataBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBase {

    private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);
    public static final String DB_CONNECTION_EXCEPTION = "There was an error while trying to connect to database";
    private static final String DB_FILE = "C:\\Users\\kruno\\Documents\\Faks\\Sem V\\Programiranje u jeziku Java\\Projekti\\PROJEKT\\src\\main\\resources\\database\\Database.properties";

    public static Connection connectToDB()throws SQLException, IOException{

        Properties configuration = new Properties();
        configuration.load(new FileReader(DB_FILE));
        String dbURL = configuration.getProperty("databaseURL");
        String dbUsername = configuration.getProperty("databaseUsername");
        String dbPassword = configuration.getProperty("databasePassword");

        return DriverManager.getConnection(dbURL,dbUsername,dbPassword);
    }
    public static List<Winery> allWineries() throws DataBaseException {
        List<Winery> wineries = new ArrayList<>();
        try (Connection connection = connectToDB()){

            Statement query = connection.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM WINERY;");

            while (rs.next()){
                Winery winery = getWineryFromRS(rs);
                wineries.add(winery);
            }

        }catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
        return wineries;
    }
    public static void addLocation(Location location) throws DataBaseException{

        try (Connection connection = connectToDB()){
            //POSTALCODE NAME REGION
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO LOCATION(POSTALCODE, NAME, REGION) VALUES(?,?,?);");
            preparedStatement.setLong(1,location.getPostalCode());
            preparedStatement.setString(2,location.getTown());
            preparedStatement.setString(3, Region.regionToString(location.getRegion()));
            preparedStatement.executeUpdate();
        }catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
    }


    public static void deleteWinery(Winery winery) throws DataBaseException{

        try (Connection connection = connectToDB()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM WINERY WHERE IDWINERY  = ?;");
            preparedStatement.setLong(1,winery.getId());


            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM WINE WHERE IDWINERY = ?");
            preparedStatement2.setLong(1,winery.getId());

            ResultSet rs = preparedStatement2.executeQuery();
            while(rs.next()){
                Wine wine = getWineFromRS(rs);
                deleteWineFromWinery(wine);
            }
            preparedStatement.executeUpdate();
        }catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
    }
    public static void deleteWineFromWinery(Wine wine) throws DataBaseException{

        try (Connection connection = connectToDB()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM WINE WHERE IDWINERY = ?;");
            preparedStatement.setLong(1,wine.getWinery().getId());

            preparedStatement.executeUpdate();
        }catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
    }
    public static void deleteLocationWineryDelete(Location location) throws DataBaseException{

        try (Connection connection = connectToDB()){

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM WINERY WHERE POSTALCODE = ?;");
            preparedStatement.setLong(1,location.getPostalCode());


            preparedStatement.executeUpdate();
        }catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
    }
    public static void deleteLocation(Location location) throws DataBaseException{

        try (Connection connection = connectToDB()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM LOCATION WHERE POSTALCODE = ?;");
            preparedStatement.setLong(1,location.getPostalCode());


            deleteLocationWineryDelete(location);
            preparedStatement.executeUpdate();
        }catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
    }
    public static void addWine(Wine wineToAdd) throws DataBaseException{
        try(Connection connection = connectToDB()){

            PreparedStatement preparedStatement= connection.prepareStatement(
                    "INSERT INTO WINE(wineType, grapeName, yearOfProduction, alcoholPercentage, idWinery ,typeUniqueness) VALUES (?,?,?,?,?,?);");

            preparedStatement.setString(1,"White");
            preparedStatement.setString(2,wineToAdd.getGrapeName());
            preparedStatement.setInt(3,wineToAdd.getYearOfProduction());
            preparedStatement.setDouble(4,wineToAdd.getYearOfProduction());
            preparedStatement.setLong(5,wineToAdd.getWinery().getId());
            preparedStatement.setInt(6,5);

            preparedStatement.executeUpdate();

        }catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
    }
    private static Winery getWineryFromRS(ResultSet rs) throws DataBaseException{

        try{
            long idwinery = rs.getLong("IDWINERY");
            long postalCode = rs.getLong("POSTALCODE");
            String name = rs.getString("NAME");
            LocalDate dateOfFoundation = rs.getTimestamp("DATEOFFOUNDATION").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Location location = getLocationFromPostalCode(allLocation(),postalCode);

            return new Winery(idwinery,name,location,dateOfFoundation);
        }
        catch (SQLException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
    }
    private static Wine getWineFromRS(ResultSet rs) throws DataBaseException{

        //IDWINE WINETYPE TYPEUNIQUENESS GRAPENAME YEAROFPRODUCTION ALCOHOLPERCENTAGE IDWINERY
        try{
            long idWine = rs.getLong("IDWINE");
            String wineType = rs.getString("WINETYPE");
            Integer typeUniqueness = rs.getInt("TYPEUNIQUENESS");
            String grapeName = rs.getString("GRAPENAME");
            Integer yearOfProduction = rs.getInt("YEAROFPRODUCTION");
            Double alcoholPercentage = rs.getDouble("ALCOHOLPERCENTAGE");
            long idWinery = rs.getLong("IDWINERY");
            Winery winery =getEntity(allWineries(),idWinery);

            Wine wine;
            switch (wineType){
                case "White" -> wine = new WhiteWine(idWine,grapeName,yearOfProduction,alcoholPercentage,null,winery,new SellingDetails(3.5,0.75,5.3),typeUniqueness);
                case "Red" -> wine = new RedWine(idWine,typeUniqueness,grapeName,yearOfProduction,alcoholPercentage,null,winery,new SellingDetails(3.5,0.75,5.3));
                case "Sparkling" -> wine = new SparklingWine(idWine,typeUniqueness,grapeName,yearOfProduction,alcoholPercentage,null,winery,new SellingDetails(3.5,0.75,5.3));
                case "Rose" -> wine = new RoseWine(idWine,typeUniqueness,grapeName,yearOfProduction,alcoholPercentage,null,winery,new SellingDetails(3.5,0.75,5.3));
                case "Dessert" -> wine = new DessertWine(idWine,typeUniqueness,grapeName,yearOfProduction,alcoholPercentage,null,winery,new SellingDetails(3.5,0.75,5.3));
                default -> wine = null;
            }
            return wine;
        }
        catch (SQLException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
    }

    public static List<Location> allLocation() throws DataBaseException{

        List<Location> everyLocation = new ArrayList<>();

        try(Connection connection = connectToDB()){

            Statement query = connection.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM LOCATION;");

            while(rs.next()){
                Location location = getLocationFromRS(rs);
                everyLocation.add(location);
            }

        }catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }

        return everyLocation;
    }
    public static List<FoodType> allFoodTypes() throws DataBaseException{

        List<FoodType> everyFoodType = new ArrayList<>();

        try (Connection connection = connectToDB()){

            Statement query = connection.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM FOODTYPE;");

            while(rs.next()){
                long id = rs.getLong("IDFOODTYPE");
                String typeName = rs.getString("NAME");
                everyFoodType.add(new FoodType(id,typeName));
            }

        }
        catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }

        return everyFoodType;
    }

    public static List<Wine> allWines() throws DataBaseException{

        List<Wine> everyWine = new ArrayList<>();
        try (Connection connection = connectToDB()){

            Statement query = connection.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM WINE;");

            while(rs.next()){
                Wine wine = getWineFromRS(rs);
                everyWine.add(wine);
            }
        }
        catch (SQLException | IOException ex){
            logger.error(DB_CONNECTION_EXCEPTION);
            throw new DataBaseException(DB_CONNECTION_EXCEPTION,ex);
        }
        return everyWine;
    }
    private static Location getLocationFromRS(ResultSet rs) throws SQLException{

        long postlCode = rs.getLong("POSTALCODE");
        String name = rs.getString("NAME");
        String region = rs.getString("REGION");

        return new Location.Builder()
                .withPostalCode(postlCode)
                .withTown(name)
                .withRegion(Region.stringToRegion(region))
                .build();
    }
    private static Location getLocationFromPostalCode(List<Location> everyLocation, long postalCode){
        return everyLocation.stream()
                .filter(l -> l.getPostalCode()==postalCode)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Location with postal code %d wasn't found.", postalCode)));
    }

    private static <T extends Entity> T getEntity(List<T> entities, long id) {
        return entities.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Entity with id %d wasn't found.", id)));
    }

}
