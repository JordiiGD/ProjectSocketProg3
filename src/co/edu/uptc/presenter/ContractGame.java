package co.edu.uptc.presenter;

import co.edu.uptc.pojos.Ball;
import co.edu.uptc.pojos.Player;
import co.edu.uptc.pojos.Racket;

public interface ContractGame {

    public interface ModelServer{
        public void setPresenter(PresenterServer presenter);
    }

    public interface ModelClient{
        public void setPresenter(PresenterClient presenter);
        public void sendKey(char keyCode);
        public void run();
        public void startBall();
        public Ball getBall();
    }

    public interface View{
        public void setPresenter(PresenterClient presenter);
        public void initMenu();
        public void startGame();
        public void serPlayer(Player player);
    }

    public interface PresenterServer{
        public void setModel(ModelServer model);
        public void setServer(Server server);
        public void startServer();
    }

    public interface PresenterClient{
        public void setModel(ModelClient model);
        public void setView(View view);
        public void setClient(Client client);
        public void startClient();
        public void updateClients(Player player);
        public void sendKey(char keyCode);
        public void startBall();
        public Ball getBall();
        public Racket getRacket();
    }

    public interface Server{
        public void setPresenter(PresenterServer presenter);
        public void run();
    }

    public interface Client{
        public void setPresenter(PresenterClient presenter);
        public Player getPlayer();
    }
}
