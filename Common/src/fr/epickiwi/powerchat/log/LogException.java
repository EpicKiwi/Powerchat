package fr.epickiwi.powerchat.log;

public class LogException extends LogMessage {

    private Throwable exception;

    public LogException(LogLevel level, Throwable exception) {
        super(level, "");
        String message = exception.getMessage()+" ( "+exception+" )"+"\n";
        for(StackTraceElement el: exception.getStackTrace()){
            message += el.toString()+"\n";
        }
        this.setMessage(message);
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
