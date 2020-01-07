import java.util.logging.*;
private static void configureLogging() {
    Logger A = Logger.getLogger("org.stepic.java.logging.ClassA");  // создадим два логера (для начала)
    Logger B = Logger.getLogger("org.stepic.java.logging.ClassB");
    
    A.setLevel(Level.ALL);     // установим уровни логирования для обоих в соотв с ТЗ
    B.setLevel(Level.WARNING);

    // создадим логгер для классов из org.stepic.java
    Logger HIGH_CLASS_LOGGER = Logger.getLogger("org.stepic.java"); // или просто org.stepic.java
                  // запретим передавать сообщения вышестоящ. логгерам
                  // или понадобится setLevel ALL
    HIGH_CLASS_LOGGER.setUseParentHandlers(false);   
               // создадим ConsoleHandler и привяжем к нему логгер
    ConsoleHandler HANDLER = new ConsoleHandler();
    HANDLER.setLevel(Level.ALL);
    HIGH_CLASS_LOGGER.addHandler(HANDLER);
               // создадим Formatter и сделаем его xml
    Formatter FORMATTER = new XMLFormatter();         
    HANDLER.setFormatter(FORMATTER);
}