package com.BorisovskayaINC;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.BorisovskayaINC.Main.AUSTIN_POWERS;


public /*static*/ class Spy implements MailService {
    public static final String AUSTIN_POWERS = "Austin Powers";
    private Logger LOG;
    public Spy(Logger LOG) {
        this.LOG = LOG;
    }
    @Override
    public Sendable processMail(Sendable mail) {
        if(mail instanceof MailMessage) {
            if ((mail.getFrom() == AUSTIN_POWERS) || (mail.getTo() == AUSTIN_POWERS)) {
                LOG.log(Level.WARNING, "Detected target mail correspondence: from {0} to {1} \"{2}\"",
                        new Object[] {mail.getFrom(), mail.getTo(), ((MailMessage) mail).getMessage()});
            } else {
                LOG.log(Level.INFO, "Usual correspondence: from {0} to {1}",
                        new Object[] {mail.getFrom(), mail.getTo()});
            }
        }
        return mail;
    }
}
