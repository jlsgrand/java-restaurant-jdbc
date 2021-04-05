package co.simplon.restaurant;

import co.simplon.restaurant.database.DatabaseConnection;
import co.simplon.restaurant.menu.AddBillAction;
import co.simplon.restaurant.menu.ListBestDishesAction;
import co.simplon.restaurant.menu.ListBestTablesAction;
import co.simplon.restaurant.menu.Menu;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException {
        // Je me connecte à la base de données
        String url = "jdbc:postgresql://localhost:5432/restaurant";
        String user = "postgres";
        String password = "postgres";
        DatabaseConnection dbConnect = new DatabaseConnection(url, user, password);

        // Je crée mon menu
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        menu.addToActionList(new AddBillAction("Ajouter une note", scanner));
        menu.addToActionList(new ListBestDishesAction("Lister les meilleurs plats"));
        menu.addToActionList(new ListBestTablesAction("Lister les meilleures tables"));

        // Je récupère les choix utilisateur
        System.out.println("Bonjour et bienvenue dans le programme de saisie de ticket de caisse");
        System.out.println("Que voulez vous faire ?");

        int menuChoice;
        do {
            menuChoice = menu(menu, scanner);
            if (menuChoice < 0) {
                System.out.println("Bye bye");
            } else if (menuChoice < menu.getActionList().size()) {
                menu.getActionList().get(menuChoice).doAction(dbConnect);
            } else {
                System.out.println("Veuillez rentrer un choix valide !");
            }
        } while (menuChoice >= 0);

        dbConnect.closeConnection();
    }

    public static int menu(Menu menu, Scanner scanner) {
        menu.displayMenu();
        int menuChoice = scanner.nextInt();
        scanner.nextLine();

        return menuChoice;
    }
}
