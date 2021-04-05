package co.simplon.restaurant.menu;

import co.simplon.restaurant.database.DatabaseConnection;
import co.simplon.restaurant.model.*;

import java.util.List;
import java.util.Scanner;

public class AddBillAction extends MenuAction {

    private final Scanner scanner;

    public AddBillAction(String name, Scanner scanner) {
        super(name);
        this.scanner = scanner;
    }

    @Override
    public void doAction(DatabaseConnection databaseConnection) {
        // Affichage des serveurs
        System.out.println("Coisissez votre serveur");
        List<Waiter> allWaiters = Waiter.getAllWaiters(databaseConnection);
        for (Waiter waiter : allWaiters) {
            System.out.println(waiter);
        }

        int waiterId = scanner.nextInt();
        scanner.nextLine();

        // Choix de la table
        System.out.println("Coisissez votre table");
        List<Table> allTables = Table.getAllTables(databaseConnection);
        for (Table table : allTables) {
            System.out.println(table);
        }

        int tableId = scanner.nextInt();
        scanner.nextLine();

        // Creation de la facture en base de données
        Bill bill = new Bill(tableId, waiterId);
        bill.save(databaseConnection);

        int dishId = 0;
        do {
            // Choix des plats et des quantités
            System.out.println("Coisissez votre plat");
            List<Dish> possibleDishes = Dish.getAllDishes(databaseConnection);
            for (Dish dish : possibleDishes) {
                System.out.println(dish);
            }
            dishId = scanner.nextInt();
            scanner.nextLine();

            // Il faudrait vérifier aussi que l'ID existe
            if (dishId > 0) {
                System.out.println("Coisissez la quantité");
                int qty = scanner.nextInt();
                scanner.nextLine();

                BillItem billItem = new BillItem(qty, dishId, bill.getId());
                billItem.save(databaseConnection);
            }
        } while (dishId > 0);
    }
}
