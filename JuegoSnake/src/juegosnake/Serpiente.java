/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegosnake;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static juegosnake.JuegoSerpiente.SerpienteTam;

/**
 *
 * @author Hinestroza
 */
public class Serpiente extends JPanel{
    Color coloserpiente = Color.green;
    Color colorcomida = Color.red;
    int tam, tammax, can, res;
    List<int[]> snake = new ArrayList<>();
    int[] comida = new int[2];
    Thread hilo;
    Automatico MovimientoSerpiente;
    String direccionamiento = "derecha";
    String direccion = "derecha";
    public static int tamse =2;
    public static double nuevointervalo = 1000;
    
    public Serpiente(int tammax, int can) {
        //MovimientoSerpiente.intervalo = 856;
        this.tammax = tammax;
        this.can = can;
        this.tam = tammax / can;
        this.res = tammax % can;
        int[] a = {can / 2 - 1, can / 2 - 1};
        int[] b = {can / 2, can / 2 - 1};
        this.snake.add(a);
        this.snake.add(b);
        Comida();
        tamaño();
        itervalo();
        MovimientoSerpiente = new Automatico(this);
        hilo = new Thread(MovimientoSerpiente);
        hilo.start();
    }

    @Override
    public void paint(Graphics pintar) {
        super.paint(pintar); // parta regraficar
        pintar.setColor(coloserpiente);
        for (int[] par : snake) { //serpiente
            pintar.fillRect(res / 2 + par[0] * tam, res / 2 + par[1] * tam, tam - 1, tam - 1);
        }
        pintar.setColor(colorcomida);
        pintar.fillRect(res / 2 + comida[0] * tam, res / 2 + comida[1] * tam, tam - 1, tam - 1);
    }
    
    public void Movimiento() {
        igualardireccionamiento();
        int[] cola = snake.get(snake.size() - 1);
        int x = 0;
        int y = 0;

        switch (direccion) {
            case "derecha":
                x = 1;
                break;
            case "izquierda":
                x = -1;
                break;
            case "arriba":
                y = -1;
                break;
            case "abajo":
                y = 1;
                break;
        }
//        int[] cabeza = {Math.floorMod(cola[0] + x, can),
//            Math.floorMod(cola[1] + y, can)};
        int[] cabeza = {cola[0] + x,cola[1] + y};
        boolean comio = false;

        if (cola[0] + x == 10 || cola[0] + x == -1 //limites de la pared
                || cola[1] + y == 10 || cola[1] + y == -1) {
            JOptionPane.showMessageDialog(this, "HA CHOCADO CON LA PARED");
            Reporte();
            hilo.stop();
        }

        for (int i = 0; i < snake.size(); i++) {
            if (cabeza[0] == snake.get(i)[0] && cabeza[1] == snake.get(i)[1]) {
                comio = true;
                Reporte();
                break;
            }
        }
        if (comio) {
            JOptionPane.showMessageDialog(this, "TE HAS MORDIDO");
            Reporte();
            hilo.stop();
        } else {
            if (cabeza[0] == comida[0] && cabeza[1] == comida[1]) {
                tamaño();
                Dificultad();
                itervalo();
                snake.add(cabeza); 
                Comida();
                if (tamse>25) {
                    JOptionPane.showMessageDialog(null, "HAS GANADO");
                    Reporte(); 
                    hilo.stop(); 
                }
            } else {
                snake.add(cabeza);
                snake.remove(0);       
            }
        }
    }
    void tamaño(){
        JuegoSerpiente.SerpienteTam.setText(String.valueOf(tamse));
        tamse++;
    }
    void itervalo(){
        JuegoSerpiente.IntervaloTiempo.setText(String.valueOf((int)nuevointervalo));
    }

    public void Comida() {
        boolean busqueda = false;
        int a = (int) (Math.random()*can); // busca numeros aletorios dentro de la cantida de cuadros
        int b = (int) (Math.random()*can);
        for (int[] par : snake) {
            if (par[0] == a && par[1] == b) {
                busqueda = true;
                Comida();
                break;
            }   
        }
        if (!busqueda) {
            this.comida[0] = a;
            this.comida[1] = b;
        }
    }
    
    public void Direccionamiento (String dir){
        this.direccionamiento = dir;
    }
    public void igualardireccionamiento (){
        this.direccion = this.direccionamiento;
    }
    public void Dificultad(){
        int dificultad = JuegoSerpiente.Nivel.getSelectedIndex();
        JuegoSerpiente Jugando = new JuegoSerpiente();
        System.out.println(dificultad);
        if (dificultad == 0) {
            nuevointervalo =  (int)( MovimientoSerpiente.intervalo)*(1-0.03);
            Jugando.IntervaloTiempo.setText(String.valueOf(nuevointervalo));
            System.out.println("intervalo de "+nuevointervalo);
            MovimientoSerpiente.intervalo = (int)nuevointervalo ;
            itervalo();
        }
        if (dificultad == 1) {
            nuevointervalo =  (int)( MovimientoSerpiente.intervalo)*(1-0.06);
            System.out.println("intervalo de "+nuevointervalo);
            MovimientoSerpiente.intervalo = (int)nuevointervalo ;
            itervalo();
        }
        if (dificultad == 2) {
            nuevointervalo =  (int)( MovimientoSerpiente.intervalo)*(1-0.09);
            System.out.println("intervalo de "+nuevointervalo);
            MovimientoSerpiente.intervalo = (int)nuevointervalo ;
            itervalo();
        }   
    }
    public void Reporte(){
        String cadenaHTML = "<!DOCTYPE html>\n"
                + "<html lang=\"en\"> \n"
                + "<head>\n"
                + "     <meta charset=\"utf-8\">\n"
                + "     <title>Reporte</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<table>\n"
                + "     <tr>\n"
                + "          <h1>Tamaño</h1>\n"
                + "          <h2>"+JuegoSerpiente.SerpienteTam.getText()+"</h1>\n"
                + "          <h1>Intervalo</h1>\n"
                + "          <h2>+ MovimientoSerpiente.intervalo+</h1>\n"
                + "          <h1>Timpo</h1>\n"
                 + "          <h2>"+JuegoSerpiente.tiempojugado.getText() +"</h1>\n"
                + "      </tr>\n"
                ;
        
         File archivo = new File("202100316.html");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(cadenaHTML);
            bw.close();
        } catch (Exception e) {
        }



    }
}
