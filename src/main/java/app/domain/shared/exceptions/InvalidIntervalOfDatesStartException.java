package app.domain.shared.exceptions;

public class InvalidIntervalOfDatesStartException extends Exception{


    public InvalidIntervalOfDatesStartException(){
        super("Invalid interval of dates! Please select a date between today and today minus the Historical days interval" );
    }

}
