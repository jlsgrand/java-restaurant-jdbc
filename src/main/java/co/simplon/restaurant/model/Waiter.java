package co.simplon.restaurant.model;

import co.simplon.restaurant.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Waiter {
    private int id;
    private String firstName;
    private String lastName;

    private List<Bill> billList = new ArrayList<>();

    public Waiter(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Waiter(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static List<Waiter> getAllWaiters(DatabaseConnection connection) {
        List<Waiter> waiterList = new ArrayList<>();
        String selectReq = "SELECT * FROM waiter";

        try (PreparedStatement statement = connection.getConnection().prepareStatement(selectReq)) {
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                waiterList.add(new Waiter(set.getInt("id"),
                        set.getString("first_name"),
                        set.getString("last_name")));
            }

            set.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return waiterList;
    }

    @Override
    public String toString() {
        return id + ". " + firstName + " " + lastName;
    }
}
