package fr.epickiwi.powerchat.cliclient;

import fr.epickiwi.powerchat.log.Logger;
import fr.epickiwi.powerchat.protocol.action.*;
import fr.epickiwi.powerchat.protocol.user.User;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        Logger.info("Démarrage du client");
        String exit = "";

        try
        {
            Socket clientSocket = new Socket("localhost", 7600);
            Logger.info("Connecté au serveur");

            Scanner personnalise = new Scanner(System.in);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            final ChangeUserAction[] initialUserAction = {(ChangeUserAction) in.readObject()};
            final User[] user = {initialUserAction[0].getNewUser()};
            Logger.info("Utilisateur "+ user[0].getUsername());

            Thread readThread = new Thread(() -> {
                try {
                    Object rawAction = in.readObject();

                    while(rawAction != null){
                        ChatAction action = (ChatAction) rawAction;

                        if(action instanceof ChangeUserAction){
                            ChangeUserAction changeUser = (ChangeUserAction) action;
                            user[0] = changeUser.getNewUser();
                            Logger.info("Nouvel utilisateur "+ user[0].getUsername());
                        }  else if(action instanceof MessageAction) {
                            MessageAction messAction = (MessageAction) action;
                            Logger.info(messAction.getAuthor().getUsername()+ " : "+messAction.getContent());
                        }  else if(action instanceof UserHasChangedAction) {
                            UserHasChangedAction hasChanged = (UserHasChangedAction) action;
                            Logger.info(hasChanged.getLastUser().getUsername()+ " est maitenant connu sous le nom de "+hasChanged.getNewUser().getUsername());
                        } else if(action instanceof UserJoinAction) {
                            UserJoinAction userJoin = (UserJoinAction) action;
                            Logger.info("Bienvenue "+userJoin.getUser().getUsername());
                        } else if(action instanceof UserQuitAction) {
                            UserQuitAction userJoin = (UserQuitAction) action;
                            Logger.info("Au revoir "+userJoin.getUser().getUsername());
                        } else {
                            Logger.log(action.toString());
                        }

                        rawAction = in.readObject();
                    }

                } catch (ClassNotFoundException e) {
                    Logger.exception(e);
                } catch(IOException e){
                    Logger.info("Déconnecté");
                }
            });
            readThread.start();

            while(!exit.equals("exit"))
            {
                String message = personnalise.nextLine();
                exit = message;
                if(message.equals("change user")){
                    Logger.info("Quel nom d'utilisateur desirez vous ?");
                    String usrname = personnalise.nextLine();
                    User newUser = (User) user[0].clone();
                    newUser.setUsername(usrname);
                    out.writeObject(new ChangeUserAction(user[0],newUser));
                } else if(!exit.equals("exit")) {
                    out.writeObject(new MessageAction(user[0],message));
                }
                out.flush();
            }

            in.close();
            out.close();
            clientSocket.close();
            Logger.info("Déconnexion du serveur");
            System.exit(0);
        }
        catch (IOException e){
            Logger.fatalException(e);
        } catch (ClassNotFoundException e) {
            Logger.fatal("Impossible de traduire le message reçu");
            Logger.fatalException(e);
        }

    }

}