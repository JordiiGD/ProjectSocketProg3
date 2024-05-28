package co.edu.uptc.presenter;

import co.edu.uptc.model.ManagerModelServer;
import co.edu.uptc.net.Server;

import java.net.ServerSocket;

public class MangerPresenterServer implements ContractGame.PresenterServer{

    private ContractGame.Server view;
    private ContractGame.ModelServer model;

    @Override
    public void setModel(ContractGame.ModelServer model) {
        this.model = model;
    }

    @Override
    public void setServer(ContractGame.Server server) {
        this.view = server;
    }

    @Override
    public void startServer() {
        makeMVP();
        Server serverSocket = new Server();
        serverSocket.setPresenter(this);
        setServer(serverSocket);
        serverSocket.run();
    }

    public void makeMVP(){
        ManagerModelServer manager = new ManagerModelServer();
        manager.setPresenter(this);
        setModel(manager);
    }
}
