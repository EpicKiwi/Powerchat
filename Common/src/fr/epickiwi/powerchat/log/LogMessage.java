package fr.epickiwi.powerchat.log;

import java.util.Date;

public class LogMessage {

    private Date date;
    private LogLevel level;
    private String message;

    public LogMessage(LogLevel level, String message) {
        this.date = new Date();
        this.level = level;
        this.message = message;
    }

    @Override
    public String toString() {
        return this.getDate()+" | ["+this.getLevel().name()+"] "+this.getMessage();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
