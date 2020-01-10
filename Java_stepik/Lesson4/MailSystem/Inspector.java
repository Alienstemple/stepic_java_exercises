package com.BorisovskayaINC;

public /*static*/ class Inspector implements MailService {
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
    public Inspector() {
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            try {
                if (((MailPackage) mail).getContent().getContent() == WEAPONS ||
                        ((MailPackage) mail).getContent().getContent() == BANNED_SUBSTANCE) {
                    throw new IllegalPackageException("Cap! We have got " + ((MailPackage) mail).getContent().getContent() + " here!");
                }
                if (((MailPackage) mail).getContent().getContent().contains("stones instead of")) {
                    throw new StolenPackageException("Cap! The thief was here!");
                }
            } catch (RuntimeException e) {    // may be initialization of exception (throw new)
                throw new RuntimeException(e);
            } catch (Error e) {
                throw new Error(e);
            }
        }
        return mail;
    }
}
