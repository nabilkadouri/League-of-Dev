package rpg.utils;

public class InvalideActionChoisieException extends Exception{
    public InvalideActionChoisieException (String message, NumberFormatException e) {
        super(message);
    }
}
