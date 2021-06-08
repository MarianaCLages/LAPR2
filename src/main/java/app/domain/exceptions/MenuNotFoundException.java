package app.domain.exceptions;

public class MenuNotFoundException extends Exception {

    public MenuNotFoundException() {
        super("Menu not found! Please check the current path!");
    }

    public MenuNotFoundException(String message) {
        super(message);
    }

}