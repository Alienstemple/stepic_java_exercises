package com.BorisovskayaINC;

/*
Класс, в котором скрыта логика настоящей почты
*/
public /*static*/ class RealMailService implements MailService {

    @Override
    public Sendable processMail(Sendable mail) {
        // Здесь описан код настоящей системы отправки почты.
        return mail;
    }
}