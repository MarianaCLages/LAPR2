package app.domain.shared.exceptions;

public class HistoricalDaysEmptyException extends Exception{

    public HistoricalDaysEmptyException(){
        super("Significance level can not be empty! Please enter a valid significance level");
    }

}
