package com.BorisovskayaINC;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class YetAnotherArchiveWriter implements AutoCloseable {

    private final DataOutputStream outputStream;   // выходной поток байтов с доп операциями для java-примитивов

    public YetAnotherArchiveWriter(Path outputFile) throws IOException {   // оборачиваем экземпляр OutputStream с параметром-вых.файлом и флагами
        this.outputStream = new DataOutputStream(Files.newOutputStream(    // экземпляром DataOutputStream (для примитивов)
                outputFile,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));// если нет - создать, стереть перед записью
    }
    public void addDirectoryRecursively(Path directory) throws IOException {  // рекурсивный обход всех вложенных директорий
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {  // walkFileTree - спец метод; new - объявление анонимного класса
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
                addFile(file, directory, attrs);  // переопределяем только один метод visitFile ВНУТРИ ВЫЗОВА Ф-И
                return FileVisitResult.CONTINUE;  // возвращ. значение управляет процессом обхода (продолжить, пропустить поддерево)
            }
        });
    }
    private void addFile(Path file, Path baseDirectory, BasicFileAttributes fileAttributes) throws IOException {
        outputStream.writeUTF(baseDirectory.relativize(file).toString()); // относит. директория, спец метод для записи строки
        outputStream.writeLong(fileAttributes.creationTime().toMillis());  // время создания
        outputStream.writeLong(fileAttributes.lastModifiedTime().toMillis()); // последняя модификация
        try (OutputStream fileContentStream = new EmbeddedOutputStream(outputStream)) { // содержимое файла
            Files.copy(file, fileContentStream); // точка останова - 00 - разбили файл на куски, префикс длины - 2 байта
        }
    }
    @Override
    public void close() throws IOException { outputStream.close(); }
}
