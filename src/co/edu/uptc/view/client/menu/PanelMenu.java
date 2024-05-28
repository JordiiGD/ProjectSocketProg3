package co.edu.uptc.view.client.menu;

import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.view.customize.Components;
import co.edu.uptc.view.customize.UIButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class PanelMenu extends JPanel {

    private JButton playButton;
    private JButton configButton;
    private JButton exitButton;
    private Font textFont;
    private DashBoard dashBoard;
    private UIButton buttonUI;
    private boolean painting;
    private Dimension buttonSize = new Dimension(300,40);
    private Color buttonColor = new Color(247, 178, 49);

    public PanelMenu(DashBoard dashBoard) {
        this.dashBoard = dashBoard;
        painting = true;
        threadPaint();
        initComponents();
    }

    public void initComponents() {
        setLayout(new GridBagLayout());
        buttonUI = new UIButton(buttonColor);
        CustomFont customFont = new CustomFont();
        textFont = customFont.customizeFont(FilePathConstants.FONT_PATH, 1, 20);
        addComponents();
    }

    public void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        createPlayButton();
        createConfigButton();
        createExitButton();

        gbc.insets = new Insets(10,10,10,10);
        add(playButton, gbc);
        gbc.gridx = 1;
        add(configButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(exitButton, gbc);
    }

    public void createPlayButton() {

        playButton = Components.customizeButtonWithTextOnly(textFont, Color.WHITE,
                Color.RED, buttonSize, "Jugar");
        playButton.setUI(buttonUI);
        playButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dashBoard.gamePanel();
            }

        });

    }

    public void createConfigButton() {

        configButton = Components.customizeButtonWithTextOnly(textFont, Color.WHITE,
                new Color(0,0,0), buttonSize, "Opciones");

        configButton.setUI(buttonUI);
        configButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dashBoard.menuConfig();
            }
        });
    }

    public void createExitButton() {

        exitButton = Components.customizeButtonWithTextOnly(textFont, Color.WHITE,
                Color.RED, buttonSize, "Salir");
        exitButton.setUI(buttonUI);
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dashBoard.setVisible(false);
                System.exit(0);
            }

        });

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image image = new ImageIcon("src/data/images/fondo.jpg").getImage();
        g.drawImage(image, 0,0, dashBoard.getWidth(), dashBoard.getHeight(), null);
        Image image1 = new ImageIcon("src/data/images/logo.png").getImage();
        g.drawImage(image1, ((dashBoard.getWidth()/2) -((dashBoard.getWidth()/4)/2)), 70, (dashBoard.getWidth()/4), (dashBoard.getHeight()/4), null);
    }

    public void threadPaint(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(painting){
                    try {
                        Thread.sleep(80);
                        repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

    public void start(){
        painting = true;
    }

    public void stop(){
        painting = false;
    }
}
