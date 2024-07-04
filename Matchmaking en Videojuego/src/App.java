import java.lang.Thread;

public class App {
    public static void main(String[] args) {
        SistemaJuego sistemaJuego = new SistemaJuego();
        Thread emparejamiento = new Thread(sistemaJuego);
        emparejamiento.start();

        int cantidadJugadores = 20;
        GeneradorJugador generador = new GeneradorJugador(sistemaJuego, cantidadJugadores);
        Thread generadorHilo = new Thread(generador);
        generadorHilo.start();

        try{
            emparejamiento.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final del programa");
    }
}
