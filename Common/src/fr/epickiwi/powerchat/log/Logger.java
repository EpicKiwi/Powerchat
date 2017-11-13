package fr.epickiwi.powerchat.log;

public class Logger {

    private final static IWriter[] writers = {
            new ConsoleWriter(),
            new FileWriter("./log.txt")
    };

    public static void logMessage(LogMessage message){
        for(IWriter writer: writers){
            writer.write(message);
        }
    }

    public static void log(String message){
        logMessage(new LogMessage(LogLevel.DEBUG,message));
    }

    public static void info(String message){
        logMessage(new LogMessage(LogLevel.INFO,message));
    }

    public static void warning(String message){
        logMessage(new LogMessage(LogLevel.WARNING,message));
    }

    public static void error(String message){
        logMessage(new LogMessage(LogLevel.ERROR,message));
    }

    public static void fatal(String message){
        logMessage(new LogMessage(LogLevel.FATAL,message));
    }

    public static void exception(Throwable exception){
        logMessage(new LogException(LogLevel.ERROR,exception));
    }

    public static void warningException(Throwable exception){
        logMessage(new LogException(LogLevel.WARNING,exception));
    }

    public static void fatalException(Throwable exception){
        logMessage(new LogException(LogLevel.FATAL,exception));
    }

}
