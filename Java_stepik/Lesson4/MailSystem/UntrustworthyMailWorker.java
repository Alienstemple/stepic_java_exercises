package com.BorisovskayaINC;

public /*static*/ class UntrustworthyMailWorker implements MailService {
    private MailService[] mailServiceArr;
    private RealMailService realMailService = new RealMailService();
    public UntrustworthyMailWorker(MailService[] mailServiceArr) {
        this.mailServiceArr = mailServiceArr;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        for (MailService service : mailServiceArr) {
            service.processMail(mail);
        }
        realMailService.processMail(mail);
        return mail;
    }

    public RealMailService getRealMailService() {
        return realMailService;
    }
}
