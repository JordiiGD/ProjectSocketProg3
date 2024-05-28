package co.edu.uptc.net;

import co.edu.uptc.pojos.InfoClient;
import co.edu.uptc.pojos.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket{

    private Socket socket;
    private Server server;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientSocket(Socket socket){
        this.socket = socket;
        try {
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        hear();
    }

    public void hear() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (output != null) {
                            InfoClient infoClient = (InfoClient) input.readObject();
                            char code = infoClient.getId();
                            server.moveRacket(code);
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

    public void write(Player player) {
        try {
            output.writeObject(player);
            output.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
