package Exceptions;
//Fordi det er en unchecked Exception bruges der RunTimeException for at lave egen exception
public class NegativeAmountException extends RuntimeException {
    public  NegativeAmountException(String message) {
        super(message);
    }
}
