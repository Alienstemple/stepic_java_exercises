package com.BorisovskayaINC;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class EmbeddedOutputStream extends FilterOutputStream {

    static final int BUFFER_SIZE = 65535; // max unsigned int - размер буфера

    private final byte[] buffer;
    private int bufferPosition; // текущая позиция в буфере
    private boolean closed;     // закрыт ли стрим

    public EmbeddedOutputStream(OutputStream out) {
        super(Objects.requireNonNull(out, "out is null"));
        this.buffer = new byte[BUFFER_SIZE];
        this.bufferPosition = 0;
    }

    @Override
    public void write(int b) throws IOException { write(new byte[] { (byte) b}); } // запись 1 байт

    @Override
    public void write(byte[] bytes) throws IOException { write(bytes, 0, bytes.length); } // запись массива байт

    @Override
    public void write(byte[] bytes, int offset, int length) throws  IOException { // отступ и длина
        while (0 < length) { // пока массив не закончится
            int bytesAddedToBuffer = addBytesToBuffer(bytes, offset, length);
            if (isBufferFull()) { // когда буфер заполнен, сбрасываем его на диск
                writeBufferToUnderlyingStream();
            }
            offset += bytesAddedToBuffer; // увеличили отступ от начала
            length -= bytesAddedToBuffer; // уменьшили длину
        }
    }

    @Override
    public void flush() throws IOException { return; }

    @Override
    public void close() throws IOException {
        if (closed) {
            return;
        }

        if (!isBufferEmpty()) {   // сбрасываем на диск непустой буфер
            writeBufferToUnderlyingStream();
        }
        writeEndOfStreamMarker();  // пишем два пустых байта - окончание потока
        closed = true;
    }

    private int addBytesToBuffer(byte[] bytes, int offset, int length) {
        int remainingBytesInBuffer = buffer.length - bufferPosition; // оставшиеся байты в потоке
        int bytesToWrite = Math.min(length, remainingBytesInBuffer); // TODO: ???
        System.arraycopy(bytes, offset, buffer, bufferPosition, bytesToWrite); // копируем массив в буфер от отступа и от позиции в буфере
        bufferPosition += bytesToWrite;
        return bytesToWrite;
    }

    private boolean isBufferFull() { return bufferPosition == buffer.length; } // буфер полон, когда текущая позиция будет на последн. эл-те

    private boolean isBufferEmpty() { return bufferPosition == 0; }

    private void writeBufferToUnderlyingStream() throws IOException {
        out.write((bufferPosition >> 8) & 0xFF);  // превращение int в 2 байта
        out.write(bufferPosition & 0xFF);
        out.write(buffer, 0, bufferPosition);
        bufferPosition = 0;
    }

    private void writeEndOfStreamMarker() throws IOException {  // 2 пустых байта - символ конца массива
        out.write(0);
        out.write(0);
    }
}
