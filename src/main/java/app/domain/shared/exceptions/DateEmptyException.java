package app.domain.shared.exceptions;

public class DateEmptyException extends Exception{

    public DateEmptyException(){
        super("Date can not be empty! Please enter a valid interval of dates!");
    }

}
