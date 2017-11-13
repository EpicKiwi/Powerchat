package fr.epickiwi.powerchat.log;

import java.io.IOException;
import java.util.ArrayList;

public class FileWriter implements Runnable,IWriter {

    private String logFile;
    private Thread thread;
    private ArrayList<LogMessage> waitingMessages;
    private boolean working;

    public FileWriter(String logFile) {
        this.working = true;
        this.logFile = logFile;
        this.thread = new Thread(this);
        this.waitingMessages = new ArrayList<>();
        this.thread.start();
    }

    @Override
    public synchronized void write(LogMessage message) {
        this.waitingMessages.add(message);
    }

    @Override
    public void run() {
        java.io.FileWriter file = null;
        try {
            file = new java.io.FileWriter(this.logFile,true);
        } catch (IOException e) {
            Logger.error("File writer disabled due to error");
            Logger.exception(e);
            this.working = false;
        }

        try {
            while (working) {
                if (waitingMessages.size() > 0) {
                    LogMessage[] messages = new LogMessage[this.waitingMessages.size()];
                    messages = this.waitingMessages.toArray(messages);
                    this.waitingMessages.clear();
                    for (int i = messages.length - 1; i >= 0; i--) {
                        LogMessage message = messages[i];
                        file.write(
                                message.getDate() + " | [" + message.getLevel().name() + "] " + message.getMessage()+"\n");
                        file.flush();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Logger.warningException(e);
                }
            }

            if(file != null)
                file.close();
        } catch (IOException e){
            Logger.exception(e);
            Logger.error("File writer disabled due to error");
            this.working = false;
        }
    }
}
