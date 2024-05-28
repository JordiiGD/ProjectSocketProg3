package co.edu.uptc.model;

import co.edu.uptc.net.Client;
import co.edu.uptc.pojos.Ball;
import co.edu.uptc.pojos.InfoClient;
import co.edu.uptc.pojos.Player;
import co.edu.uptc.presenter.ContractGame;

public class ManagerModelClient implements ContractGame.ModelClient {

    public ContractGame.PresenterClient presenter;
    private Client client = new Client();
    private ManagerGame managerGame = new ManagerGame();
    private Player player;

    @Override
    public void setPresenter(ContractGame.PresenterClient presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sendKey(char keyCode) {
        InfoClient infoClient = new InfoClient(keyCode);
        client.write(infoClient);
    }

    @Override
    public void run() {
        client.setPresenter(presenter);
        client.run();
        player = client.getPlayer();
    }

    @Override
    public void startBall() {
        managerGame.start();
    }

    @Override
    public Ball getBall() {
        return managerGame.getBall();
    }
}
