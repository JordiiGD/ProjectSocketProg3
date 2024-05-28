package co.edu.uptc.view.client.menu;

import co.edu.uptc.pojos.Player;
import co.edu.uptc.presenter.ContractGame;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class DashBoard extends JFrame {

    public ManagerView view;
    private PanelMenu panelMenu;
    private MenuConfig menuConfig;
    private PanelGame panelGame;

    public DashBoard() {
        setLayout(new BorderLayout());
        initComponents();
    }

    public void initComponents(){
        setBounds(1,1,1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMenu = new PanelMenu(this);
        menuConfig = new MenuConfig(this);
        panelGame = new PanelGame(this);
        this.getContentPane().add(panelMenu, BorderLayout.CENTER);
    }

    public void menuPanel(){
        panelMenu.setVisible(true);
        menuConfig.setVisible(false);
        panelGame.setVisible(false);
        panelMenu.start();
        panelGame.stop();
        menuConfig.stop();
        this.getContentPane().add(panelMenu,BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void gamePanel(){
        panelMenu.setVisible(false);
        menuConfig.setVisible(false);
        panelGame.setVisible(true);
        menuConfig.stop();
        panelMenu.stop();
        this.getContentPane().add(panelGame, BorderLayout.CENTER);
        revalidate();
        view.presenter.startBall();
        panelGame.start();
        repaint();
    }

    public void menuConfig(){
        panelMenu.setVisible(false);
        menuConfig.setVisible(true);
        panelGame.setVisible(false);
        menuConfig.start();
        panelGame.stop();
        panelMenu.stop();
        this.getContentPane().add(menuConfig,BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void run(){
        setVisible(true);
    }

    public static void main(String[] args) {
        DashBoard dashBoard = new DashBoard();
        dashBoard.run();
    }

}
