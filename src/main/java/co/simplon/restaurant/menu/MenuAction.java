package co.simplon.restaurant.menu;

import co.simplon.restaurant.database.DatabaseConnection;

public abstract class MenuAction {
    private final String name;

    public MenuAction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract void doAction(DatabaseConnection databaseConnection);
}
