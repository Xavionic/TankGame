package com.tank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Peta extends JFrame implements ActionListener {
    private final int PANJANG = 300;
    private final int LEBAR = 300;
    private final int UKURAN = 15;
    private final int LUAS = PANJANG * LEBAR / UKURAN * UKURAN; //400

    private boolean atas = false;
    private boolean kanan = true;
    private boolean bawah = false;
    private boolean kiri = false;
    private int player_x;
    private int player_y;

    private boolean reloading = false;
    private int projectile_x;
    private int projectile_y;

    public Peta(){

    }

    private void shot(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class TAdapter extends KeyAdapter{

        public void KeyPressed(KeyEvent e){
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE){
                shot();
            }

            if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && (!kanan)) {
                if (kiri ){
                    if (player_x > 0){
                        player_x -= UKURAN;
                    }
                }else{
                    kiri = true;
                    atas = false;
                    bawah = false;
                }
            }

            if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && (!kiri)) {
                if (kanan){
                    player_x += UKURAN;
                }else {
                    kanan = true;
                    atas = false;
                    bawah = false;
                }
            }

            if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && (!bawah)) {
                if (atas){
                    player_y += UKURAN;
                }else {
                    atas = true;
                    kanan = false;
                    kiri = false;
                }
            }

            if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && (!atas)) {
                if (bawah){
                    player_y -= UKURAN;
                }else {
                    bawah = true;
                    kanan = false;
                    kiri = false;
                }
            }
        }

    }
}
