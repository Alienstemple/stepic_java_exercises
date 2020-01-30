package com.BorisovskayaINC;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import  java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.FileTime;

public class YetAnotherArchiveReader implements AutoCloseable {
    private final DataInputStream inputStream;
    private ArchiveEntry currentEntry;

    public YetAnotherArchiveReader(Path file) throws IOException {
        inputStream = new DataInputStream(Files.newInputStream(file));
        currentEntry = null;
    }

    public void extractToDirectory(Path directory) throws IOException {
        for (ArchiveEntry entry = nextEntry(); entry != null; entry = nextEntry()) { // берем каждую запись entry
            Path targetFile = directory.resolve(entry.getName());
            extractFile(targetFile, entry); // распаковка записи
        }
    }

    // something

    public ArchiveEntry nextEntry() throws IOException {
        ArchiveEntry nextEntry = readEntry();
        if (nextEntry == null) {
            return null;
        }

        if (currentEntry != null) {
            currentEntry.getInputStream().close();
        }
        currentEntry = nextEntry;
        return currentEntry;
    }

    private ArchiveEntry readEntry() throws IOException {
        ArchiveEntry entry = new ArchiveEntry();
        try { // читаем новую запись в том же порядке, в котором записывали
            entry.setName(inputStream.readUTF());
            entry.setCreationTime(inputStream.readLong());
            entry.setLastModifiedTime(inputStream.readLong());
            entry.setInputStream(new EmbeddedInputStream(inputStream)); // вырезает служебные 2 байта
        } catch (EOFException) {
            return null;
        }
        return entry;
    }
}
