package co.edu.uptc.net;

import co.edu.uptc.pojos.Player;
import co.edu.uptc.pojos.Racket;
import co.edu.uptc.presenter.ContractGame;
import co.edu.uptc.utils.PropertiesUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements ContractGame.Server {

    private ContractGame.PresenterServer presenter;
    private ServerSocket serverSocket;
    private ArrayList<ClientSocket> sockets;
    private boolean running;
    private Player player;
    private int playerNumbers;

    public Server() {
        sockets = new ArrayList<ClientSocket>();
        running = false;
        createPlayer();
        returnInfoClient();
    }

    public void run(){
        try {
            serverSocket = new ServerSocket(PropertiesUtil.PORT);
            System.out.println("Server iniciado");
            while (!running) {
                Socket socket = serverSocket.accept();
                playerNumbers++;
                player.setPlayerNumber(playerNumbers);
                System.out.println("Cliente Conectado");
                ClientSocket clientSocket = new ClientSocket(socket);
                clientSocket.setServer(this);
                sockets.add(clientSocket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player createPlayer() {
        player = new Player();
        if(sockets.size() <= 1){
            player.setPlaying(true);
        }else {
            player.setPlaying(false);
        }
        player.setPlayerNumber(playerNumbers);
        player.setRacket(assignRacket(player));
        return player;
    }

    public Racket assignRacket(Player player) {
        Racket racket = new Racket();
        if(!player.isPlaying()){
            racket.setHeight(0);
            racket.setWidth(0);
            racket.setX(0);
            racket.setY(0);
        }else {
            if(sockets.size() == 1){
                racket.setHeight(PropertiesUtil.racketHeight);
                racket.setWidth(PropertiesUtil.racketWidth);
                racket.setX(0);
                racket.setY(0);
            }else {
                racket.setHeight(PropertiesUtil.racketHeight);
                racket.setWidth(PropertiesUtil.racketWidth);
                racket.setX(1);
                racket.setY(1);
            }
        }
        return racket;
    }

    public void returnClients(){
        for (ClientSocket clientSocket : sockets) {
            clientSocket.write(player);
        }
    }

    public void moveRacket(char keyCode){
        if((int) keyCode == Integer.parseInt(PropertiesUtil.getLeftMovementKeyProperty())){
            player.getRacket().setY(player.getRacket().getY() + 5);
        }else if((int) keyCode == Integer.parseInt(PropertiesUtil.getRightMovementKeyProperty())){
            player.getRacket().setY(player.getRacket().getY() - 5);
        }
    }

    public void returnInfoClient(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1);
                        returnClients();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void setPresenter(ContractGame.PresenterServer presenter) {
        this.presenter = presenter;
    }
}
