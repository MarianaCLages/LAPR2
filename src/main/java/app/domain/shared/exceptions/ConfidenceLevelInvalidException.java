package app.domain.shared.exceptions;

public class ConfidenceLevelInvalidException extends Exception{

    public ConfidenceLevelInvalidException(){
        super("Please enter a valid significance level! (It should be between 0-100!)");
    }

}
