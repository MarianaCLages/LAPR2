package app.domain.shared.exceptions;

public class ChoiceBoxEmptyException extends Exception{

    public ChoiceBoxEmptyException(){
        super("Choice box empty! Please choose 1 option!");
    }

}
