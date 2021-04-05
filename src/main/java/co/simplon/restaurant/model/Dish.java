package co.simplon.restaurant.model;

import co.simplon.restaurant.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dish {
    private int id;
    private String name;
    private float price;

    public Dish(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Dish(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static List<Dish> getAllDishes(DatabaseConnection connection) {
        List<Dish> dishList = new ArrayList<>();
        try {
            String selectReq = "SELECT * FROM dish";
            PreparedStatement statement = connection.getConnection().prepareStatement(selectReq);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                dishList.add(new Dish(set.getInt("id"),
                        set.getString("name"),
                        set.getFloat("price")));
            }

            set.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return dishList;
    }

    @Override
    public String toString() {
        return id + ". " + name + " (" + price + "€)";
    }
}
