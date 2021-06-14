package app.domain.shared.exceptions;

public class RadioButtonsNotSelectedException extends Exception{

    public RadioButtonsNotSelectedException(){
        super("Please choose at least 1 of the current options in the Radio buttons! (Either Day or week)");
    }

}
