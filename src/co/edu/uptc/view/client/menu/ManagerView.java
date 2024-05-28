package co.edu.uptc.view.client.menu;

import co.edu.uptc.pojos.Player;
import co.edu.uptc.presenter.ContractGame;

import javax.swing.*;

public class ManagerView extends JFrame implements ContractGame.View {

    public ContractGame.PresenterClient presenter;;
    private Player player;

    public ManagerView(){
        this.player = player;
    }

    @Override
    public void setPresenter(ContractGame.PresenterClient presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initMenu() {

    }

    @Override
    public void startGame() {
        DashBoard dashBoard = new DashBoard();
        dashBoard.run();
    }

    @Override
    public void serPlayer(Player player) {
        this.player = player;
    }
}
