package hr.fer.grad.proj.ttsapp.exception;

public class UnableToGenerateException extends RuntimeException {

    public UnableToGenerateException() {
        super();
    }

    public UnableToGenerateException(String message) {
        super(message);
    }
}
