package co.edu.uptc.net;

import co.edu.uptc.pojos.InfoClient;
import co.edu.uptc.pojos.Player;
import co.edu.uptc.presenter.ContractGame;
import co.edu.uptc.utils.PropertiesUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements ContractGame.Client{

    private Socket socket;
    private ContractGame.PresenterClient presenter;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Player player;


    @Override
    public void setPresenter(ContractGame.PresenterClient presenter) {
        this.presenter = presenter;
    }

    public void run(){
        try {
            socket = new Socket(PropertiesUtil.IP, PropertiesUtil.PORT);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            hear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void hear (){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        Player player = (Player) input.readObject();
                        if(player != null){
                            presenter.updateClients(player);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

    public void write(InfoClient client){
        try {
            output.writeObject(client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player getPlayer(){
        return player;
    }
}
