package com.BorisovskayaINC;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
    public Label checkLabels(TextAnalyzer[] analyzers, String text) { // экземпляр интерфейса
        for (int i = 0; i < analyzers.length; i++) {
            if (analyzers[i].processText(text) != Label.OK) // обект класса
                return analyzers[i].processText(text);
        }
        return Label.OK;
    }
}
