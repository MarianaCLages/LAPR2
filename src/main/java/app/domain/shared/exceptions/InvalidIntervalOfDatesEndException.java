package app.domain.shared.exceptions;

public class InvalidIntervalOfDatesEndException extends Exception{

    public InvalidIntervalOfDatesEndException(){
        super("Invalid interval of dates! Please dont choose a day that surpasses the current day! (For instance if today is 12/06/2021 , in the 2 date picker don't pick a day like 13/06/2021!)");
    }

}
