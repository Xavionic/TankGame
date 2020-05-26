package com.tank;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Map2 extends JPanel implements ActionListener {

    private final int PANJANG = 300;
    private final int LEBAR = 300;
    private final int UKURAN = 15;
    private int delay = 150;

    private int player_x;
    private int player_y;

    private int target_x;
    private int target_y;

    private Enemy[] musuh = new Enemy[10];

    private boolean kiri = false;
    private boolean kanan = true;
    private boolean atas = false;
    private boolean bawah = false;
    private boolean idup = true;

    private Timer timer1;

    public Map2() {
        addKeyListener(new TAdapter());
        setBackground(Color.darkGray);
        setFocusable(true);
        setPreferredSize(new Dimension(PANJANG, LEBAR));
        loadGambar();
        inisialisasiPermainan();
    }

    private ImageIcon gambarPlayer;
    private ImageIcon peluru;
    private ImageIcon target;

    private void loadGambar() {
        gambarPlayer = new ImageIcon("src/com/resources/tankRight.png");
        peluru = new ImageIcon("src/com/resources/bulletRight.png");
        target = new ImageIcon("src/com/resources/naziLogo.png");
    }

    private void inisialisasiPermainan() {
        player_x = 5 * UKURAN;
        player_y = 5 * UKURAN;
        projectile_x = -5*UKURAN;
        projectile_y = -5*UKURAN;
        aturUlangTarget();
        musuh[0] = new Enemy(UKURAN, 3*UKURAN);
        relocateMusuh();
        timer1 = new Timer(delay, this);
        timer1.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gambar(g);
    }

    private void gambar(Graphics g) {
        if (idup) {
            tampilkanSkor(g);
            g.drawImage(gambarPlayer.getImage(), player_x, player_y, this);
            if (peluruMuncul){
                g.drawImage(peluru.getImage(), projectile_x, projectile_y, this);
            }
            g.drawImage(target.getImage(), target_x, target_y, this);
            g.drawImage(musuh[0].gambarMusuh.getImage(), musuh[0].koorX, musuh[0].koorY, this);
            Toolkit.getDefaultToolkit().sync();
        } else {
            kalah(g);
        }
    }

    private void tampilkanSkor(Graphics g){

        String msg = "Skor : " + getSkor();
        Font small = new Font("Times New Roman", Font.BOLD, 18);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg,(PANJANG - metr.stringWidth(msg)) / 2, 18);
    }

    private int projectile_x;
    private int projectile_y;
    private boolean trigerring;

    private void aturUlangTarget(){
        int rX = ((int)(Math.random() * 100))/5;
        int rY = ((int)(Math.random() * 100))/5;

        target_x = rX * UKURAN;
        target_y = rY * UKURAN;
    }

    private void tentukanApakahPeluruSedangTerpakai(){
        peluruMuncul = projectile_x >= 0 && projectile_x < PANJANG && projectile_y >= 0 && projectile_y < LEBAR;
    }

    private boolean peluruMuncul;

    private int skor = 0;

    private int getSkor(){
        return skor;
    }

    private void apakahPeluruMengenaiTargetnya(){
        if ((projectile_x == target_x && projectile_y == target_y)|| (player_x == target_x && player_y == target_y)){
            peluruMuncul = false;
            targetKena = true;
        }else peluruMuncul = true;
    }

    private boolean targetKena = false;


    private void targetting(){
        apakahPeluruMengenaiTargetnya();
        if (targetKena){
            aturUlangTarget();
            skor++;
            targetKena = false;
            projectile_x = -5 * UKURAN;
            peluruAtas = true;
            peluruBawah = false;
            peluruKiri = false;
            peluruKanan = false;
        }
    }

    private boolean peluruKanan;
    private boolean peluruKiri;
    private boolean peluruAtas;
    private boolean peluruBawah;

    private void shot(){
        tentukanApakahPeluruSedangTerpakai();

        if (trigerring && !peluruMuncul){
            projectile_x = player_x;
            projectile_y = player_y;
            trigerring = false;
            if (kiri){
                peluru = new ImageIcon("src/com/resources/bulletLeft.png");
                peluruKiri = true;
                peluruAtas = false;
                peluruKanan = false;
                peluruBawah = false;
            }

            if (kanan){
                peluru = new ImageIcon("src/com/resources/bulletRight.png");
                peluruKiri = false;
                peluruAtas = false;
                peluruKanan = true;
                peluruBawah = false;
            }

            if (atas){
                peluru = new ImageIcon("src/com/resources/bulletUp.png");
                peluruKiri = false;
                peluruAtas = true;
                peluruKanan = false;
                peluruBawah = false;
            }

            if (bawah){
                peluru = new ImageIcon("src/com/resources/bulletDown.png");
                peluruKiri = false;
                peluruAtas = false;
                peluruKanan = false;
                peluruBawah = true;
            }
        }

        if (peluruKiri){
            projectile_x -= UKURAN;
        }

        if (peluruKanan){
            projectile_x += UKURAN;
        }

        if (peluruAtas){
            projectile_y -= UKURAN;
        }

        if (peluruBawah){
            projectile_y += UKURAN;
        }

    }

    private void kalah(Graphics g) {

        String msg = "Kamu Kalah! skor akhir : "+ getSkor();
        Font small = new Font("Times New Roman", Font.BOLD, 18);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (PANJANG - metr.stringWidth(msg)) / 2, LEBAR / 2);
    }

    private void move() {
        if (kiri) {
            player_x -= UKURAN;
        }

        if (kanan) {
            player_x += UKURAN;
        }

        if (atas) {
            player_y -= UKURAN;
        }

        if (bawah) {
            player_y += UKURAN;
        }
    }

    private void checkCollision() {

        if (player_y >= LEBAR) {
            idup = false;
        }

        if (player_y < 0) {
            idup = false;
        }

        if (player_x >= PANJANG) {
            idup = false;
        }

        if (player_x < 0) {
            idup = false;
        }

        if (!idup) {
            timer1.stop();
        }
    }

    int a = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (idup) {
            checkCollision();
            shot();
            targetting();
            checkEnemy();
            if (a%4 == 0){
                musuhGerak();
            }
            a++;
        }
        repaint();
    }

    public void checkEnemy(){
        if (musuh[0].koorX == projectile_x && musuh[0].koorY == projectile_y){
            skor += 3;
            peluruMuncul = false;
            projectile_x = -5 * UKURAN;
            projectile_y = -5 * UKURAN;
            relocateMusuh();
        }
    }

    public void musuhGerak(){
        int jarakX = player_x - musuh[0].koorX ;
        int jarakY = player_y - musuh[0].koorY ;
        jarakX = jarakX >= 0? jarakX : -jarakX;
        jarakY = jarakY >= 0? jarakY : -jarakY;
        if (musuh[0].idup){
            if (player_x == musuh[0].koorX && player_y == musuh[0].koorY){
                this.idup = false;
            }

            if (player_x >= musuh[0].koorX && jarakX >= jarakY){
                musuh[0].koorX += UKURAN;
                musuh[0].gambarMusuh = new ImageIcon("src/com/resources/tankRight3.png");
            }

            if (player_x < musuh[0].koorX && jarakX >= jarakY){
                musuh[0].koorX -= UKURAN;
                musuh[0].gambarMusuh = new ImageIcon("src/com/resources/tankLeft3.png");
            }

            if (player_y >= musuh[0].koorY && jarakY > jarakX){
                musuh[0].koorY += UKURAN;
                musuh[0].gambarMusuh = new ImageIcon("src/com/resources/tankDown3.png");
            }

            if (player_y < musuh[0].koorY && jarakY > jarakX){
                musuh[0].koorY -= UKURAN;
                musuh[0].gambarMusuh = new ImageIcon("src/com/resources/tankUp3.png");
            }
        }else{
        }
    }

    public void relocateMusuh(){
        int r = (int)(Math.random() * 100);
        if (r<25){
            musuh[0].koorX = -1 * UKURAN;
            musuh[0].koorY = -1 * UKURAN;
        }else if (r >= 25 && r < 50){
            musuh[0].koorX = -1 * UKURAN;
            musuh[0].koorY = 21 * UKURAN;
        }else if (r >= 50 && r < 75){
            musuh[0].koorX = 21 * UKURAN;
            musuh[0].koorY = 21 * UKURAN;
        }else if (r >= 75){
            musuh[0].koorX = 21 * UKURAN;
            musuh[0].koorY = -1 * UKURAN;
        }
    }

    private boolean paused = false;

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_P){
                if (!paused){
                    paused = true;
                    timer1.stop();
                }else {
                    paused = false;
                    timer1.start();
                }
            }

            if (key == KeyEvent.VK_SPACE){
                trigerring = true;
            }

            if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && (!kanan) && !paused) {
                if (kiri){
                    move();
                }else{
                    kiri = true;
                    atas = false;
                    bawah = false;
                    gambarPlayer = new ImageIcon("src/com/resources/tankLeft.png");
                }
            }

            if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && (!kiri) && !paused) {
                if (kanan){
                    move();
                }else {
                    kanan = true;
                    atas = false;
                    bawah = false;
                    gambarPlayer = new ImageIcon("src/com/resources/tankRight.png");
                }
            }

            if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && (!bawah) && !paused) {
                if (atas){
                    move();
                }else {
                    atas = true;
                    kanan = false;
                    kiri = false;
                    gambarPlayer = new ImageIcon("src/com/resources/tankUp.png");
                }
            }

            if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && (!atas) && !paused) {
                if (bawah){
                    move();
                }else {
                    bawah = true;
                    kanan = false;
                    kiri = false;
                    gambarPlayer = new ImageIcon("src/com/resources/tankDown.png");
                }
            }
        }
    }
}