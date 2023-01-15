package fr.projava.triangle.Views;

import fr.projava.triangle.Controllers.AccountController;
import fr.projava.triangle.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class AuthWindowController {

    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnSignUp;
    @FXML
    private TextField pseudoInput;
    @FXML
    private Label returnMessage;

    private static User user;

    public void signIn(MouseEvent mouseEvent) throws IOException, SQLException, InterruptedException {
        String p = pseudoInput.getText();
        String msg = AccountController.connectToAccount(p);
        if (msg.equals("Unable to connect") || msg.equals("Account not found."))
            returnMessage.setText(msg);
        else {
            user = AccountController.getUser();
            if (user != null) {
                System.out.println("[AuthWindow CONTROLLER] : " + "______________________ ");
                System.out.println("[AuthWindow CONTROLLER] : " + "CONNECTED USERS : ");
                user.showConnectedUsers();
                System.out.println("[AuthWindow CONTROLLER] : " + "______________________ ");
            }
            goToChat(mouseEvent);
        }
    }

    public void signUp(MouseEvent mouseEvent) throws UnknownHostException, SQLException {
        String p = pseudoInput.getText();
        String msg = AccountController.newAccount(p);
        returnMessage.setText(msg);
    }


    public void goToChat(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(AuthWindowController.class.getResource("/ChatWindow.fxml"));
        Parent root = loader.load();
        ChatWindowController cwc = loader.getController();
        cwc.setUser(user);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}