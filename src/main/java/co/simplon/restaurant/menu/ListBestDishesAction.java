package co.simplon.restaurant.menu;

import co.simplon.restaurant.database.DatabaseConnection;

import java.sql.*;

public class ListBestDishesAction extends MenuAction {
    public ListBestDishesAction(String name) {
        super(name);
    }

    @Override
    public void doAction(DatabaseConnection databaseConnection) {
        String selectCmd = "select name, sum(quantity * price) as total from bill\n" +
                "join bill_item bi on bill.id = bi.bill_idx\n" +
                "join dish d on bi.dish_idx = d.id\n" +
                "group by name\n" +
                "order by total desc";
        try (PreparedStatement stmt = databaseConnection.getConnection().prepareStatement(selectCmd)){
            // Exécution de la requête
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getString("name"));
                System.out.print(" - ");
                System.out.print(rs.getFloat("total"));
                System.out.println("€");
            }
        } catch (SQLException exception) {
            System.out.println("Une erreur est survenue lors de la récupération des meilleurs plats.");
            exception.printStackTrace();
        }
    }
}
