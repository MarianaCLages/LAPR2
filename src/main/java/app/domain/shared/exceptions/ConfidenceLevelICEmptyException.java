package app.domain.shared.exceptions;

public class ConfidenceLevelICEmptyException extends Exception{

    public ConfidenceLevelICEmptyException(){
        super("Confidence Level IC can not be empty! Please enter a valid confidence level");
    }

}
