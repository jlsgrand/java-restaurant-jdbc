package co.simplon.restaurant.model;

import co.simplon.restaurant.database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private int id;
    private LocalDateTime date;
    private int tableId;
    private int waiterId;
    private final List<BillItem> billItemList = new ArrayList<>();

    public Bill(int table, int waiter) {
        this.date = LocalDateTime.now();
        this.tableId = table;
        this.waiterId = waiter;
    }

    public int getId() {
        return id;
    }

    public void save(DatabaseConnection databaseConnection){
        String insertCmd= "INSERT INTO bill (id, date, waiter_idx, table_idx) VALUES(DEFAULT, ?, ?, ?);";
        try (PreparedStatement stmt = databaseConnection.getConnection().prepareStatement(insertCmd, Statement.RETURN_GENERATED_KEYS)){
            // Préparation de la requête
            stmt.setTimestamp(1, Timestamp.valueOf(date));
            stmt.setInt(2, waiterId);
            stmt.setInt(3, tableId);

            // Exécution de la requête
            stmt.execute();

            // Récupération de l'ID généré
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException exception) {
            System.out.println("Une erreur est survenue lors de la sauvegarde d'une facture");
            exception.printStackTrace();
        }
    }
}
