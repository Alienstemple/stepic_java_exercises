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

    //@Override
    public int read() throws IOException {
        byte[] bytes = new byte[1];
        int bytesRead = read(bytes);       // TODO: ???
        return bytesRead == 1 ? bytes[0] : IOStatus.EOF;
    }

    //@Override
    public int read(byte[] bytes) throws IOException{
        return read(bytes, 0, bytes.length);
    }

    //@Override
    public int read(byte[] bytes, int offset, int length) throws IOException {
        int bytesRead = 0;
        while (0 < length) {
            if (isBufferEmpty() && readBufferFromUnderlyingStream() <= 0) { // TODO: как реализован?
                break;
            }
            int bytesGotFromBuffer = getBytesFromBuffer(bytes, offset, length); // TODO: как реализован?
            bytesRead += bytesGotFromBuffer;
            offset += bytesGotFromBuffer;
            length -= bytesGotFromBuffer;
        }
        return bytesRead;
    }

    //@Override
    public long skip(long bytesToSkip) throws IOException {
        for (long i = 0; i < bytesToSkip; ++i) {
            if (read() == IOStatus.EOF) {
                return i;
            }
        }
    }

    private boolean isBufferFull() { return bufferPosition == buffer.length; }

    private boolean isBufferEmpty() { return bufferPosition == 0; }

    private int readBufferFromUnderlyingStream() {
        return 0; // TODO: как реализован?
    }
    private int getBytesFromBuffer(byte[] bytes, int offset, int length) {
        return 0; // TODO: как реализован?
    }
}
