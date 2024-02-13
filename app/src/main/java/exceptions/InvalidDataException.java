package exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String s) {
        super(s);
    }
    public String what(){
        return super.getMessage();
    }

}
