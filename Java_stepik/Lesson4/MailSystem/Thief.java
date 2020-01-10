package com.BorisovskayaINC;

public /*static*/ class Thief implements MailService {
    private int minVal;
    public static int stolenValue = 0;  // статич перем, сохраняет значение от вызова ф-и к вызову
    public Thief(int minVal) {          // (нужно ее пополнять)
        this.minVal = minVal;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            if (((MailPackage) mail).getContent().getPrice() >= minVal) {
                stolenValue += ((MailPackage) mail).getContent().getPrice();
                Package newContent = new Package("stones instead of " +
                        ((MailPackage) mail).getContent().getContent(), 0);
                MailPackage newMail = new MailPackage(mail.getFrom(), mail.getTo(), newContent);
                return newMail;
            }
        }
        return mail;
    }
    public int getStolenValue() {
        return  stolenValue;
    }
}
