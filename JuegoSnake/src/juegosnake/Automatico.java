/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegosnake;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hinestroza
 */
public class Automatico implements Runnable{
    Serpiente panel;
    int intervalo = 1000;

    public Automatico(Serpiente panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            panel.Movimiento();
            panel.repaint();
            try {
                Thread.sleep(intervalo);
            } catch (InterruptedException ex) {
                Logger.getLogger(Automatico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void Intervalo(int intervalo) {
        this.intervalo = intervalo;
    }
}
