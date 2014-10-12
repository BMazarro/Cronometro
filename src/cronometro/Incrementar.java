/*
 * CREADO POR BEATRIZ MAZARRO
 * 11/10/2014
 * 
 */
package cronometro;

public class Incrementar extends Thread{
    private Ventana v;
    private int seg;
    private int min;
    private int hora;
  
    public Incrementar(Ventana v){
        this.v=v;
        seg = v.getSeg();
        min = v.getMin();
        hora = v.getHora();       
    }
    @Override
    public void run() {
        for (; ;){
            if (seg!=59){
                seg=v.aumentSeg();
            }else{
                 seg=0;
                 v.resetSeg();
                if(min!=59){            
                    min=v.aumentMin();
                }else{
                    hora=v.aumentHora();
                    min=0;
                    v.resetMin();
                }           
            }
             try {
                sleep(999);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
            if(!v.isContinuar()){
                try {
                    synchronized(this){
                       this.wait(); 
                    } 
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }    
        }       
    }    
    public void seguir(){
        synchronized(this){
            notifyAll();
        }
    }   
}
