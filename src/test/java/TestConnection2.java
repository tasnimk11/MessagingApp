import fr.projava.triangle.Controllers.ThreadController;
import fr.projava.triangle.Models.User;

import java.net.*;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class TestConnection2 {


    public static String adresse;

    static {
        try {
            adresse = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    ThreadController TC=new ThreadController();
    static User u;

    public TestConnection2() throws UnknownHostException {
        u=new User(InetAddress.getByName(adresse), 1104, "Sofiene");
    }

    public static void main(String[] args) throws InterruptedException, UnknownHostException {
        u=new User(InetAddress.getByName(adresse), 1108, "Sofiene");
        ThreadController.launchListeningThreadUDP(u);
        Thread aux=new Thread();
        aux.sleep(3000);
        for(int i = 0; i < u.getContactBook().size(); i++) {
            User u1= (User) u.getContactBook().get(i);
            System.out.print(u1.getPseudo() + " " + u1.getIpInetAddress());
        }
    }

}
