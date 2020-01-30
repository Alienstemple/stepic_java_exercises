package com.BorisovskayaINC;

import sun.nio.ch.IOStatus;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class EmbeddedInputStream {
    private static final int BUFFER_SIZE = EmbeddedOutputStream.BUFFER_SIZE;

    private final byte[] buffer;
    private int bufferPosition;
    private int bufferLimit;
    private boolean streamDataFinished;

    public EmbeddedInputStream(InputStream in) throws IOException {
        super(Objects.requireNonNull(in, "in"));   // TODO: ???
        this.buffer = new byte[BUFFER_SIZE];
        this.bufferPosition = 0;
        this.bufferLimit = 0;
    }

    @Override
    public int read() throws IOException {
        byte[] bytes = new byte[1];
        int bytesRead = read(bytes);       // TODO: ???
        return bytesRead == 1 ? bytes[0] : IOStatus.EOF;
    }
}
