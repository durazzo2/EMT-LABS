package mk.ukim.finki.emtlabb.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {
        super("Passwords do not match exception.");
    }
}

