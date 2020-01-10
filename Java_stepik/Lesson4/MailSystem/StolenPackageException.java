package com.BorisovskayaINC;

public /*static*/ class StolenPackageException extends RuntimeException {
    public StolenPackageException(String s) {
        super(s);
    }

    public StolenPackageException(String s, Throwable cause) {
        super(s, cause);
    }

    public StolenPackageException(Throwable throwable) {
        super(throwable);
    }

    public StolenPackageException() {
    }
}
