package app.domain.shared.exceptions;

public class DateInvalidException extends Exception {

    public DateInvalidException(){
        super("Interval of dates invalid! Please select a lower date in the *start* date picker!");
    }

}
