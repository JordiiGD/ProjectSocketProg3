package co.edu.uptc.view.client.menu;

import co.edu.uptc.utils.CustomFont;
import co.edu.uptc.utils.FilePathConstants;
import co.edu.uptc.utils.PropertiesUtil;
import co.edu.uptc.view.customize.Components;
import co.edu.uptc.view.customize.UIButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;

public class MenuConfig extends JPanel {

    private JLabel titleConfig;
    private JButton changeExitGameButton;
    private JLabel changeExitGameLabel;
    private JButton changeLeftMovementButton;
    private JLabel changeLeftMovementLabel;
    private JButton changeRightMovementButton;
    private JLabel changeRightMovementLabel;
    private JButton closeButton;
    private boolean isChangingKey;
    private boolean painting;
    private Font textFont;
    private UIButton uiButton;
    private DashBoard dashBoard;

    public MenuConfig(DashBoard dashBoard){
        this.dashBoard = dashBoard;
        this.painting = false;
        initComponents();
        threadPaint();
    }

    public void initComponents(){
        this.uiButton = new UIButton(new Color(0,0,0));
        CustomFont customFont = new CustomFont();
        textFont = customFont.customizeFont(FilePathConstants.FONT_PATH, 1, 20);

        createLabels();
        createChangeRightMovementButton();
        createChangeLeftMovementButton();
        createChangeExitGameButton();
        createCloseButton();

        addComponents();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Image image = new ImageIcon("src/data/images/fondo.jpg").getImage();
        g.drawImage(image, 0, 0, dashBoard.getWidth(), dashBoard.getHeight(), null);
    }

    public void threadPaint(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (painting){
                    try {
                        Thread.sleep(80);
                        repaint();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void addComponents(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(titleConfig, gbc);

        gbc.gridy = 1;
        add(changeLeftMovementButton, gbc);

        gbc.gridy = 2;
        add(changeRightMovementButton, gbc);

        gbc.gridy = 3;
        add(changeExitGameButton, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(closeButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(changeLeftMovementLabel, gbc);

        gbc.gridy = 2;
        add(changeRightMovementLabel, gbc);

        gbc.gridy = 3;
        add(changeExitGameLabel, gbc);
    }

    public void createLabels(){
        Color color = new Color(0,0,0);
        titleConfig = Components.customizeJLabel(Color.WHITE, color, new Dimension(300, 36), textFont, SwingConstants.CENTER);
        titleConfig.setText("CONFIGURACION");

        changeExitGameLabel = Components.customizeJLabel(Color.WHITE, color, new Dimension(300, 36), textFont, SwingConstants.CENTER);
        changeExitGameLabel.setText(PropertiesUtil.getExitGameButtonKeyProperty());

        changeLeftMovementLabel = Components.customizeJLabel(Color.WHITE, color, new Dimension(300, 36), textFont, SwingConstants.CENTER);
        changeLeftMovementLabel.setText(PropertiesUtil.getLeftMovementKeyProperty());

        changeRightMovementLabel = Components.customizeJLabel(Color.WHITE, color, new Dimension(300, 36), textFont, SwingConstants.CENTER);
        changeRightMovementLabel.setText(PropertiesUtil.getRightMovementKeyProperty());
    }

    public void cancelChangeKey() {
        Timer timer = new Timer(5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                isChangingKey = false;
                changeRightMovementLabel.setText(PropertiesUtil.getRightMovementKeyProperty());
                changeLeftMovementLabel.setText(PropertiesUtil.getLeftMovementKeyProperty());
                changeExitGameLabel.setText(PropertiesUtil.getExitGameButtonKeyProperty());

            }

        });
        timer.setRepeats(false);
        timer.start();
    }

    public void createChangeRightMovementButton() {

        changeRightMovementButton = Components.customizeButtonWithTextOnly(textFont,
                Color.WHITE, new Color(0,0,0), new Dimension(300, 40),
                "Derecha");
        changeRightMovementButton.setUI(uiButton);
        changeRightMovementButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isChangingKey) {
                    isChangingKey = true;
                    changeRightMovementLabel.setText("Asigne la nueva tecla");
                    changeRightMovementButtonBinding();
                }
            }
        });
    }

    public void changeRightMovementButtonBinding() {
        if (isChangingKey) {

            changeRightMovementLabel.setFocusable(true);
            changeRightMovementLabel.requestFocus();
            changeRightMovementLabel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    isChangingKey = false;
                    changeRightMovementLabel.setText(KeyEvent.getKeyText(e.getKeyCode()));
                    PropertiesUtil.setRightMovementKeyProperty(String.valueOf(e.getKeyCode()));
                }
            });
        }
        cancelChangeKey();
    }

    public void createChangeLeftMovementButton() {

        changeLeftMovementButton = Components.customizeButtonWithTextOnly(textFont,
                Color.WHITE, new Color(0,0,0), new Dimension(300, 40),
                "Izquierda");
        changeLeftMovementButton.setUI(uiButton);
        changeLeftMovementButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isChangingKey) {
                    isChangingKey = true;
                    changeLeftMovementLabel.setText("Asigne la nueva tecla");
                    changeLeftMovementButtonBinding();
                }
            }
        });
    }

    public void changeLeftMovementButtonBinding() {
        if (isChangingKey) {

            changeLeftMovementLabel.setFocusable(true);
            changeLeftMovementLabel.requestFocus();

            changeLeftMovementLabel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    changeLeftMovementLabel.setText(KeyEvent.getKeyText(e.getKeyCode()));
                    PropertiesUtil.setLeftMovementKeyProperty(String.valueOf(e.getKeyCode()));
                }
            });

        }
        cancelChangeKey();
    }

    public void createChangeExitGameButton() {

        changeExitGameButton = closeButton = Components.customizeButtonWithTextOnly(textFont,
                Color.WHITE, new Color(0,0,0), new Dimension(300, 40),
                "Salir del juego");
        changeExitGameButton.setUI(uiButton);
        changeExitGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isChangingKey) {
                    isChangingKey = true;
                    changeExitGameLabel.setText("Asigne la nueva tecla");
                    changeExitGameBinding();
                }
            }
        });
    }

    public void changeExitGameBinding() {
        if (isChangingKey) {

            changeExitGameLabel.setFocusable(true);
            changeExitGameLabel.requestFocus();

            changeExitGameLabel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    changeExitGameLabel.setText(KeyEvent.getKeyText(e.getKeyCode()));
                    PropertiesUtil.setExitGameButtonKeyProperty(String.valueOf(e.getKeyCode()));
//                    dashBoard.changeExitWhileInGameTextLabel();
                }
            });

        }
        cancelChangeKey();

    }

    public void createCloseButton() {
        closeButton = Components.customizeButtonWithTextOnly(textFont,
                Color.WHITE, new Color(0,0,0), new Dimension(150, 40),
                "Salir");
        closeButton.setUI(uiButton);
        closeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                isChangingKey = false;
                changeRightMovementLabel.setText(PropertiesUtil.getRightMovementKeyProperty());
                changeLeftMovementLabel.setText(PropertiesUtil.getLeftMovementKeyProperty());
                changeExitGameLabel.setText(PropertiesUtil.getExitGameButtonKeyProperty());

                dashBoard.menuPanel();
            }

        });
    }

    public void start(){
        painting = true;
    }

    public void stop(){
        painting = false;
    }


}
