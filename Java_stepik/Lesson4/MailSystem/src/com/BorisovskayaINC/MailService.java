package com.BorisovskayaINC;

/*
Интерфейс, который задает класс, который может каким-либо образом обработать почтовый объект.
*/
public /*static*/ interface MailService {
    Sendable processMail(Sendable mail);
}