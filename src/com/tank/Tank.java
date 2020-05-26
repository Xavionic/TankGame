package com.tank;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Tank extends JFrame {

    public Tank() {
        add(new Map2());

        setResizable(false);
        pack();

        setTitle("Tengtengan by Sabililhaq");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JFrame tank = new Tank();
            tank.setVisible(true);
        });
    }
}