package com.BorisovskayaINC;

import java.io.*;
public class Main {

    public static void main(String[] args) throws IOException {
        int prev, next;
        while((prev = System.in.read()) > 0) {
            next = System.in.read();
            if (prev == 13 && next == 10) {  // пропуск символа и печать 10 (\n)
                System.out.write(10);
            } else {
                System.out.write(prev);
                if (next > 0) {
                    System.out.write(next);
                }
            }

        }
        System.out.flush();
    }
}
