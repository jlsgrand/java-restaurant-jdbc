package co.simplon.restaurant.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuAction> menuActionList = new ArrayList<>();

    public Menu() {
    }

    public void addToActionList(MenuAction menuAction) {
        this.menuActionList.add(menuAction);
    }

    public List<MenuAction> getActionList() {
        return menuActionList;
    }

    public void displayMenu() {
        for (int i = 0; i < menuActionList.size(); i++) {
            System.out.println(i + ". " + menuActionList.get(i));
        }
    }
}
