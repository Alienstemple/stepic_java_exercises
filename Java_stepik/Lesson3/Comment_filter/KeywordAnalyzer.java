package com.BorisovskayaINC;

public abstract class KeywordAnalyzer implements TextAnalyzer {
    protected abstract String[] getKeywords();
    protected abstract Label getLabel();
    public Label processText(String text) {
        // анализ вхождения подстроки из массива keywords в строку string
        for (int i = 0; i < (getKeywords()).length; i++) {
            if ((text.indexOf(getKeywords()[i])) != -1) {
                return getLabel();
            }
        }
        return Label.OK;
    }
}
