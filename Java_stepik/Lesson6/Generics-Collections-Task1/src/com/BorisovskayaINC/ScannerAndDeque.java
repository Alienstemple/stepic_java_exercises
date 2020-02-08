package com.BorisovskayaINC;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Deque;

public class ScannerAndDeque {
    public static void main(String...args) throws IOException {
        Integer tempInt;
        String tempChar;
        boolean addable = false;  // добавляем в очередь или нет

        Deque<Integer> resultDeque = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                tempInt = sc.nextInt();
                if (addable) {
                    resultDeque.addFirst(tempInt);
                    addable = false;
                } else {
                    addable = true;
                }
            } else {
                tempChar = sc.next(" ");
            }
        }

        Integer element;
        while ((element = resultDeque.pollFirst()) != null) {
            System.out.print(element);
            System.out.print(' ');
        }
    }
}
