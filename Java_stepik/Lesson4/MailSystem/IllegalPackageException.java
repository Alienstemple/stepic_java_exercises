package com.BorisovskayaINC;

public /*static*/ class IllegalPackageException extends RuntimeException { // maybe also Error, but "cannot extend multiple classes"
    public IllegalPackageException(String s) {
        super(s);
    }

    public IllegalPackageException(String s, Throwable cause) {
        super(s, cause);
    }

    public IllegalPackageException() {
    }
}
