import co.edu.uptc.presenter.ManagerPresenterClient;
import co.edu.uptc.presenter.MangerPresenterServer;

public class Main {
    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("client")){
            ManagerPresenterClient client = new ManagerPresenterClient();
            client.startClient();
        }
        if(args.length > 0 && args[0].equals("server")){
            MangerPresenterServer manger = new MangerPresenterServer();
            manger.startServer();
        }
    }
}