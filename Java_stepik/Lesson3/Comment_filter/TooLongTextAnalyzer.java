package com.BorisovskayaINC;

public class TooLongTextAnalyzer implements TextAnalyzer {
    private int maxLength;
    public TooLongTextAnalyzer(int maxLength) {
        this.maxLength = maxLength;
    }

    public Label processText(String text) { // предварительная реализация
        if (text.length() > maxLength)
            return Label.TOO_LONG;
        else
            return Label.OK;
    }

}
