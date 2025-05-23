package ru.ifmo.se.server.database;

import ru.ifmo.se.general.contract.Hashing;
import ru.ifmo.se.general.entity.*;
import ru.ifmo.se.general.data.OrganizationData;
import ru.ifmo.se.general.data.UserData;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Class provide database access. Implemented OrganizationData and UserData.
 *
 * @since 3.0
 * @author safarislava
 */
public class DatabaseManager implements OrganizationData, UserData {
    private Connection connection;
    private final Logger logger = Logger.getLogger(DatabaseManager.class.getName());

    /**
     * Method for database connection. Needed path to config file.
     *
     * @param host Value of host address
     * @param config Value of path to config
     */
    public void connect(String host, String config) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(config));
        } catch (IOException e) {
            logger.severe("Can't load properties file: " + e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(host, properties);
        } catch (SQLException e) {
            logger.severe("Can't connect to the database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String type() {
        return "PostgreSQL database";
    }

    @Override
    public int count() {
        int count;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT count(id) FROM organizations");

            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            logger.severe("Can't get count: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public List<Organization> getOrganizations() {
        List<Organization> organizations = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM organizations");

            while (resultSet.next()) {
                organizations.add(getOrganization(resultSet));
            }
        } catch (SQLException e) {
            logger.severe("Can't get organizations: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return organizations;
    }

    @Override
    public List<Integer> getIds() {
        List<Integer> ids = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM organizations");

            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            logger.severe("Can't get ids: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return ids;
    }

    @Override
    public Organization getOrganization(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM organizations WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            return getOrganization(resultSet);

        } catch (SQLException e) {
            logger.severe("Can't get organization: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public String insert(Organization organization, String creator) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO organizations(name, coordinate_x, coordinate_y, creation_time, annual_turnover, " +
                            "full_name, employees_count, type, zipcode, town_x, town_y, town_z, creator) " +
                            "VALUES (?, ?, ?, now(), ?, ?, ?, ?::organization_type, ?, ?, ?, ?, ?)");

            setUpdatableOrganizationFields(organization, statement);
            statement.setString(12, creator);
            statement.executeUpdate();

            return String.format("Organization %s successfully inserted%n", organization.getName());

        } catch (SQLException e) {
            logger.severe("Can't insert organization: " + e.getMessage());
            return String.format("Failed insert organization %s%n", organization.getName());
        }
    }

    @Override
    public String update(int id, Organization organization) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE organizations SET name = ?, coordinate_x = ?, coordinate_y = ?, " +
                            "annual_turnover = ?, full_name = ?, employees_count = ?, type = ?::organization_type, " +
                            "zipcode = ?, town_x = ?, town_y = ?, town_z = ?" +
                            "WHERE id = ?");

            setUpdatableOrganizationFields(organization, statement);
            statement.setInt(12, id);
            statement.executeUpdate();

            return String.format("Organization %d successfully updated%n", id);

        } catch (SQLException e) {
            logger.severe("Can't update organization: " + e.getMessage());
            return String.format("Failed update organization %d%n", id);
        }
    }

    @Override
    public String remove(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM organizations WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            return String.format("Organization %d successfully removed%n", id);

        } catch (SQLException e) {
            logger.severe("Can't remove organization: " + e.getMessage());
            return String.format("Failed remove organization %d%n", id);
        }
    }

    @Override
    public String getCreator(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT creator FROM organizations WHERE id = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getString(1);

        } catch (SQLException e) {
            logger.severe("Can't get creator: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for parsing organization from ResultSet.
     *
     * @param resultSet Value of ResultSet
     * @return Parsed organization
     * @throws SQLException If something wrong with database connection
     */
    private Organization getOrganization(ResultSet resultSet) throws SQLException {
        Organization organization = new Organization();
        Coordinates coordinates = new Coordinates();
        Address address = new Address();
        Location location = new Location();

        organization.setId(resultSet.getInt("id"));
        organization.setName(resultSet.getString("name"));

        coordinates.setX(resultSet.getDouble("coordinate_x"));
        coordinates.setY(resultSet.getFloat("coordinate_y"));
        organization.setCoordinates(coordinates);

        organization.setCreationDate(resultSet.getDate("creation_time")
                .toLocalDate().atStartOfDay(ZoneId.of("Europe/Moscow")));
        organization.setAnnualTurnover(resultSet.getLong("annual_turnover"));
        organization.setFullName(resultSet.getString("full_name"));
        organization.setEmployeesCount(resultSet.getInt("employees_count"));
        organization.setType(OrganizationType.valueOf(resultSet.getString("type")));

        address.setZipCode(resultSet.getString("zipcode"));
        location.setX(resultSet.getDouble("town_x"));
        location.setY(resultSet.getLong(("town_y")));
        location.setZ(resultSet.getFloat(("town_z")));
        address.setTown(location);
        organization.setPostalAddress(address);

        return organization;
    }

    /**
     * Method for setting arguments that doesn't set automatically
     *
     * @param organization Value of organization
     * @param statement Value of PreparedStatement
     * @throws SQLException If something wrong with database connection
     */
    private void setUpdatableOrganizationFields(Organization organization, PreparedStatement statement) throws SQLException {
        statement.setString(1, organization.getName());
        statement.setDouble(2, organization.getCoordinates().getX());
        statement.setFloat(3, organization.getCoordinates().getY());
        statement.setLong(4, organization.getAnnualTurnover());
        statement.setString(5, organization.getFullName());
        statement.setInt(6, organization.getEmployeesCount());
        statement.setString(7, organization.getType().name());
        statement.setString(8, organization.getPostalAddress().getZipCode());
        statement.setDouble(9, organization.getPostalAddress().getTown().getX());
        statement.setLong(10, organization.getPostalAddress().getTown().getY());
        statement.setFloat(11, organization.getPostalAddress().getTown().getZ());
    }

    @Override
    public String register(String username, String password) {
        try {
            if (!checkUniqueUsername(username)) return "Username is already in use\n";

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users(username, password, salt) VALUES (?, ?, ?)");

            statement.setString(1, username);
            String salt = Hashing.getSalt();
            String passwordHash = Hashing.getHash(password + salt);
            statement.setString(2, passwordHash);
            statement.setString(3, salt);

            statement.executeUpdate();
            return String.format("User %s successfully registered%n", username);
        }
        catch (SQLException e) {
            logger.severe("Can't register user: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private boolean checkUniqueUsername(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ?");

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkUserPassword(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT password FROM users WHERE username = ?");

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            String salt = getSalt(username);
            String passwordHash = Hashing.getHash(password + salt);

            if (!resultSet.next()) return false;
            return resultSet.getString(1).equals(passwordHash);

        } catch (SQLException e) {
            logger.severe("Can't check user password: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method for getting salt from users database.
     *
     * @param username Value of username
     * @return Value of salt
     */
    private String getSalt(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT salt FROM users WHERE username = ?");

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) return null;
            return resultSet.getString(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
