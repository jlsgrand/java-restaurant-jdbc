package co.simplon.restaurant.model;

import co.simplon.restaurant.database.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;

public class BillItem {
    private int quantity;
    private int dishId;
    private int billId;

    public BillItem(int quantity, int dish, int bill) {
        this.quantity = quantity;
        this.dishId = dish;
        this.billId = bill;
    }

    public void save(DatabaseConnection databaseConnection) {
        String insertCmd= "INSERT INTO bill_item (bill_idx, dish_idx, quantity) VALUES(?, ?, ?);";
        try (PreparedStatement stmt = databaseConnection.getConnection().prepareStatement(insertCmd)){
            // Préparation de la requête
            stmt.setInt(1, billId);
            stmt.setInt(2, dishId);
            stmt.setInt(3, quantity);

            // Exécution de la requête
            stmt.execute();
        } catch (SQLException exception) {
            System.out.println("Une erreur est survenue lors de la sauvegarde d'une ligne de facture");
            exception.printStackTrace();
        }
    }
}
