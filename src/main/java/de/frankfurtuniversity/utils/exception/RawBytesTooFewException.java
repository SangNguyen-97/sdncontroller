package de.frankfurtuniversity.utils.exception;

public class RawBytesTooFewException extends IllegalArgumentException{
    public RawBytesTooFewException(){
        super("The raw byte array is tool short.");
    }
}
