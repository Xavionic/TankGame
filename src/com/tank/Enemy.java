package com.tank;

import javax.swing.*;

public class Enemy {
    Enemy(int inisialX, int inisialY){
        this.koorX = inisialX;
        this.koorY = inisialY;
    }

    protected int koorX;
    protected int koorY;

    private boolean atas = false;
    private boolean bawah = false;
    private boolean kiri = false;
    private boolean kanan = true;
    protected boolean idup = true;

    protected ImageIcon gambarMusuh = new ImageIcon("src/com/resources/tankRight3.png");

    protected void turnLeft(){
        if (atas){
            atas = false;
            kiri = true;
            gambarMusuh = new ImageIcon("src/com/resources/tankLeft3.png");
        }

        if (kiri){
            kiri = false;
            bawah = true;
            gambarMusuh = new ImageIcon("src/com/resources/tankDown3.png");
        }

        if (bawah){
            bawah = false;
            kanan = true;
            gambarMusuh = new ImageIcon("src/com/resources/tankKanan3.png");
        }

        if (kanan){
            kanan = false;
            atas = true;
            gambarMusuh = new ImageIcon("src/com/resources/tankUp3.png");
        }
    }

    protected void turnRight(){
        if (atas){
            atas = false;
            kanan = true;
            gambarMusuh = new ImageIcon("src/com/resources/tankRight3.png");
        }

        if (kanan){
            kanan = false;
            bawah = true;
            gambarMusuh = new ImageIcon("src/com/resources/tankDown3.png");
        }

        if (bawah){
            bawah = false;
            kiri = true;
            gambarMusuh = new ImageIcon("src/com/resources/tankLeft3.png");
        }

        if (kiri){
            kiri = false;
            atas = true;
            gambarMusuh = new ImageIcon("src/com/resources/tankUp3.png");
        }
    }

    protected void move(){

    }

}
