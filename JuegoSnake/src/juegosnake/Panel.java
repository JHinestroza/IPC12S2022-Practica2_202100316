/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegosnake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Hinestroza
 */
public class Panel extends JPanel {
    Color colofondo = Color.black;
    int tam, tammax, can;
    List<int[]> snake;  // es como array pero con cantida variable
    int[] comida;

    public Panel(int tammax, int can) {
        this.tammax = tammax;
        this.can = can;
        this.tam = tammax / can;
        
    }
    @Override
    public void paint(Graphics pintar) {
        super.paint(pintar); // parta regraficar
        pintar.setColor(colofondo);
        for (int i = 0; i < can; i++) {
            for (int j = 0; j < can; j++) { //cuadrado, misma cantidad
                pintar.fillRect(i * tam, j * tam, tam - 1, tam - 1);
            }
        }

    }
}
