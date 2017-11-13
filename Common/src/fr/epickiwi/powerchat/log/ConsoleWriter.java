package fr.epickiwi.powerchat.log;

public class ConsoleWriter implements IWriter {
    @Override
    public void write(LogMessage message) {
        switch (message.getLevel()){
            case ERROR:
            case FATAL:
            case WARNING:
                System.err.println(
                        message.getDate()+" | ["+message.getLevel().name()+"] "+message.getMessage());
                break;
            default:
                System.out.println(
                        message.getDate()+" | ["+message.getLevel().name()+"] "+message.getMessage());
                break;
        }
    }
}
