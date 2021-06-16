package app.domain.shared.exceptions;

public class InvalidLengthException extends Exception {

    public InvalidLengthException() {
        super("The length of the x array should be equal to the number of dependent variables");
    }

}
