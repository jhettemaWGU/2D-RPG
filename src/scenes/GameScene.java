package scenes;

import entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;

public class GameScene extends JFrame {
    private int screenWidth = 800;
    private int screenHeight = 600;
    private Player player;


    public GameScene() {

        setTitle("Samek's Adventures");
        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        player = new Player(100, 100, 50, 50);

        GamePanel gamePanel = new GamePanel(player);
        add(gamePanel);
        setVisible(true);
    }


    public class GamePanel extends JPanel {
        private Player player;
        private Set<Integer> pressedKeys;
        private boolean swordActive;

        public GamePanel(Player player) {
            this.player = player;
            setFocusable(true);
            pressedKeys = new HashSet<>();
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    pressedKeys.add(e.getKeyCode());
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        swingSword();
                    }
                    movePlayer();
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    pressedKeys.remove(e.getKeyCode());
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        swordActive = false;
                    }
                }
            });
        }

        private void movePlayer() {
            int dx = 0, dy = 0;

            if (pressedKeys.contains(KeyEvent.VK_W) || pressedKeys.contains(KeyEvent.VK_UP)) {dy = -1;}
            if (pressedKeys.contains(KeyEvent.VK_S) || pressedKeys.contains(KeyEvent.VK_DOWN)) {dy = 1;}
            if (pressedKeys.contains(KeyEvent.VK_A) || pressedKeys.contains(KeyEvent.VK_LEFT)) {dx = -1;}
            if (pressedKeys.contains(KeyEvent.VK_D) || pressedKeys.contains(KeyEvent.VK_RIGHT)) {dx = 1;}

            if (dx != 0 || dy != 0) {
                player.move(dx, dy);
                repaint();
            }
        }

        private void swingSword() {
            swordActive = true;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            player.render(g);

            if (swordActive) {
                renderSword(g);
            }
        }

        private void renderSword(Graphics g) {
            g.setColor(Color.BLUE);
            int swordWidth = 10;
            int swordHeight = 20;
            int swordX = player.getX() + player.getWidth();
            int swordY = player.getY() + (player.getHeight() / 2) - (swordHeight / 2);
            g.fillRect(swordX, swordY, swordWidth, swordHeight);
        }
    }
}
