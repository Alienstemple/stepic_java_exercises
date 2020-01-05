package com.BorisovskayaINC;

public class SpamAnalyzer extends KeywordAnalyzer {
    private String[] keywords;

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }

    @Override
    protected String[] getKeywords() {
        return keywords;
    }

    public SpamAnalyzer(String[] keywords) {
        this.keywords = keywords;
    }


}
