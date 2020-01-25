import java.io.*;
public class ChecksumInputStream {
    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int byte_;
        int checkSum = Integer.rotateLeft(0,1);
        while ((byte_ = inputStream.read()) > 0) {         // проверка на считывание непустого символа, вход в рекурсию
            checkSum = Integer.rotateLeft(checkSumOfStream(inputStream), 1)^byte_;
        }
        return checkSum; // иначе - останов рекурсии, возвращаем единичный элем
        // для xor
    }
}
