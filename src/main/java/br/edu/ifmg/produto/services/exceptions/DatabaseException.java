package br.edu.ifmg.produto.services.exceptions;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException() {
        super();
    }

}
