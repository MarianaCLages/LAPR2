package app.domain.shared.exceptions;

public class HistoricalDaysInvalidException extends Exception{

    public HistoricalDaysInvalidException(){
        super("Please type a valid number! (You can not type a number of days that surpass a year!) Please try again.");
    }

}
