package fr.projava.triangle.Controllers;

import fr.projava.triangle.Models.Message;
import fr.projava.triangle.Models.User;
import fr.projava.triangle.Views.ChatWindowController;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConversationController {
    public static void sendMessage(String id, User user, String message) throws SQLException {
        ThreadController.sendTCP(user,message); //TODO : should be a blocking call !
        //DB controller : add to DB
        DatabaseController.addMessage(id,user.getIpInetAddress().getHostAddress(),message, true);
    }

    public static void receiveMessage(User user,String remoteIP, String message) throws SQLException {
        //Supposedly, message was received by TCP listener => called once everything is okay
        DatabaseController.addMessage(user.getId(),remoteIP,message, false);

    }
    public static ArrayList<Message> loadHistory(String remoteIP,String id) throws SQLException {
        return DatabaseController.loadHistory(remoteIP,id);
    }
}
