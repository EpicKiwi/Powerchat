package fr.epickiwi.powerchat.protocol.action;

import fr.epickiwi.powerchat.protocol.user.User;

public class MessageAction extends ChatAction {

    private String content;

    public MessageAction(User author, String content) {
        super(author);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
