package co.simplon.restaurant.model;

import co.simplon.restaurant.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private int id;
    private String name;
    private final List<Bill> billList = new ArrayList<>();

    public Table(String name) {
        this.name = name;
    }

    public Table(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<Table> getAllTables(DatabaseConnection connection) {
        List<Table> tableList = new ArrayList<>();
        String selectReq = "SELECT * FROM \"table\"";
        try (PreparedStatement statement = connection.getConnection().prepareStatement(selectReq)) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                tableList.add(new Table(set.getInt("id"),
                        set.getString("name")));
            }
            set.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return tableList;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }

}
