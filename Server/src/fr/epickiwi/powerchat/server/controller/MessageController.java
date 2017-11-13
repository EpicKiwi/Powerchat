package fr.epickiwi.powerchat.server.controller;

import fr.epickiwi.powerchat.log.Logger;
import fr.epickiwi.powerchat.protocol.action.MessageAction;
import fr.epickiwi.powerchat.server.Client;

public class MessageController extends Controller<MessageAction> {

    public MessageController(Client client, MessageAction action) {
        super(client, action);
    }

    @Override
    public void run() {
        MessageAction messAction = (MessageAction) action;
        if(messAction.getAuthor().getId().equals(this.client.getUser().getId())){
            Logger.info(this.client.getUser().getUsername()+ " : "+messAction.getContent());
            this.client.getServer().sendBroadcast(action);
        }
    }

}
