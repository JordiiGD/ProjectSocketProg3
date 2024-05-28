package co.edu.uptc.model;

import co.edu.uptc.presenter.ContractGame;

public class ManagerModelServer implements ContractGame.ModelServer {

    private ContractGame.PresenterServer presenter;
    private ManagerGame managerGame;

    public ManagerModelServer() {
        managerGame = new ManagerGame();
    }

    @Override
    public void setPresenter(ContractGame.PresenterServer presenter) {
        this.presenter = presenter;
    }
}
