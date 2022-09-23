/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegosnake;

/**
 *
 * @author Hinestroza
 */
public class TiempoJugado extends Thread{
    int segundo = 0;
    int minuto = 0;
    int hora = 0;
    Serpiente SerpientePanel;

    public void run() {
        
        try {
            for (; ;){         
               if(segundo!=59) {
                   segundo++;                                  
                }else{
                    if(minuto!=59){
                        segundo=0; 
                        minuto++;
                    }else{
                            hora++;
                            minuto=0;
                            segundo=0;       
                    }
                }               
            JuegoSerpiente.tiempojugado.setText(hora + ":" + minuto + ":" + segundo);
            Thread.sleep(1000);          
            }           
        } catch (Exception ex) {
             System.out.println(ex.getMessage());
        }   
    
    }
}
