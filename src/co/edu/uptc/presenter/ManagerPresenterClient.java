package co.edu.uptc.presenter;

import co.edu.uptc.model.ManagerModelClient;
import co.edu.uptc.pojos.Ball;
import co.edu.uptc.pojos.Player;
import co.edu.uptc.pojos.Racket;
import co.edu.uptc.view.client.menu.ManagerView;

public class ManagerPresenterClient implements ContractGame.PresenterClient{

    private ContractGame.View view;
    private ContractGame.ModelClient modelClient;
    private ContractGame.Client client;
    private Player player;
    private boolean firstUpdate = false;

    @Override
    public void setModel(ContractGame.ModelClient model) {
        this.modelClient = model;
    }

    @Override
    public void setView(ContractGame.View view) {
        this.view = view;
    }

    @Override
    public void setClient(ContractGame.Client client) {
        this.client = client;
    }

    @Override
    public void startClient() {
        makeMVP();
        modelClient.run();
        view.serPlayer(player);
        view.startGame();
    }

    public void makeMVP(){
        ManagerView managerView = new ManagerView();
        managerView.setPresenter(this);

        ManagerModelClient managerModelClient = new ManagerModelClient();
        managerModelClient.setPresenter(this);

        setView(managerView);
        setModel(managerModelClient);
    }

    @Override
    public void updateClients(Player player) {
        if(!firstUpdate){
            this.player = player;
            firstUpdate = true;
        }else {
            this.player.setRacket(player.getRacket());
        }
    }

    @Override
    public void sendKey(char keyCode) {
        modelClient.sendKey(keyCode);
    }

    @Override
    public void startBall() {
        modelClient.startBall();
    }

    @Override
    public Ball getBall() {
        return modelClient.getBall();
    }

    @Override
    public Racket getRacket() {
        if(player != null){
            return player.getRacket();
        }
        return new Racket();
    }
}
