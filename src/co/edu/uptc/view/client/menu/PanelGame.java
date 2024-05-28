package co.edu.uptc.view.client.menu;

import co.edu.uptc.pojos.Ball;
import co.edu.uptc.pojos.Racket;
import co.edu.uptc.utils.PropertiesUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelGame extends JPanel {

    private DashBoard dashBoard;
    private Ball ball;
    private Racket racket;
    public boolean running = false;

    public PanelGame(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        moveRacket();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon("src/data/images/suelo.jpg").getImage();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        image = new ImageIcon("src/data/images/mesa.png").getImage();
        g.drawImage(image, 100, 100, getWidth()-200, getHeight()-200, null);
        g.setColor(Color.YELLOW);
        if(ball != null) {
            g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        }
        g.setColor(Color.RED);
        if(racket != null) {
            g.fillRect(racket.getX(), racket.getY(), racket.getWidth(), racket.getHeight());
        }
    }

    public void threadPaint(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while(running){
                    try {
                        Thread.sleep(1);
                        ball = dashBoard.view.presenter.getBall();
                        racket = dashBoard.view.presenter.getRacket();
                        repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

    public void moveRacket(){
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(KeyEvent.getKeyText(e.getKeyCode()).equals(PropertiesUtil.getExitGameButtonKeyProperty())){
                    dashBoard.menuPanel();
                }
                dashBoard.view.presenter.sendKey(e.getKeyChar());
            }
        });
        this.setFocusable(true);
    }

    public void start(){
        running = true;
        threadPaint();
        this.requestFocus();
    }

    public void stop(){
        running = false;
    }

}
