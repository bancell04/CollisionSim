package dev.bancell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Block> blocks = new ArrayList<>();
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 800;
    private CollisionHandler collisionHandler = new CollisionHandler();

    public static void main(String[] args) {
        Main sim = new Main();
        sim.startSim();
        sim.runSim();
    }

    public void startSim() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Brady's Collision Simulator.");
        System.out.println("========= Block 1 =========");
        System.out.print("Enter mass of Block 1: ");
        float mass1 = scanner.nextFloat();
        System.out.print("Enter velocity of Block 1: ");
        float velocity1 = scanner.nextFloat();

        System.out.print("Enter mass of Block 2: ");
        float mass2 = scanner.nextFloat();
        System.out.print("Enter velocity of Block 2: ");
        float velocity2 = scanner.nextFloat();

        blocks.add(new Block(velocity1, mass1, 100, WINDOW_HEIGHT/2, (int) (mass1 + 50), (int) (mass1 + 50)));
        blocks.add(new Block(-velocity2, mass2, WINDOW_WIDTH - 100 - (int) (mass2 + 50), WINDOW_HEIGHT/2, (int) (mass2 + 50), (int) (mass2 + 50)));
    }

    public void runSim() {
        // Create the and draw the window
        JFrame frame = new JFrame("Brady's Collision Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        SimulationPanel panel = new SimulationPanel();
        frame.add(panel);
        frame.setVisible(true);

        // sim loop
        Timer timer = new Timer(16, e -> {
            Block b1 = blocks.get(0);
            Block b2 = blocks.get(1);

            b1.updatePos((int) (b1.getX() + b1.getVelocity()), b1.getY());
            b2.updatePos((int) (b2.getX() + b2.getVelocity()), b2.getY());

            if (b1.getX()+b1.getWidth() >= b2.getX()) {
                collisionHandler.handleCollision(b1, b2);
            }

            if (b1.getX() <= 0) {
                b1.updateVelocity(-b1.getVelocity());
            }

            if (b2.getX() >= WINDOW_WIDTH - b2.getWidth()) {
                b2.updateVelocity(-b2.getVelocity());
            }


            panel.repaint(); // redraw the panel
        });
        timer.start();
    }

    class SimulationPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Block b1 = blocks.get(0);
            Block b2 = blocks.get(1);


            // draw the two blocks
            g.setColor(Color.RED);
            g.fillRect(b1.getX(), b1.getY()-(b1.getHeight()/2), b1.getWidth(), b1.getHeight());  // Block 1

            g.setColor(Color.BLUE);
            g.fillRect(b2.getX(), b2.getY()-(b2.getHeight()/2), b2.getWidth(), b2.getHeight());  // Block 2
        }
    }
}