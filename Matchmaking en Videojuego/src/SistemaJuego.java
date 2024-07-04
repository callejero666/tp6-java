import java.util.ArrayList;
import java.util.List;



public class SistemaJuego implements Runnable{
    private List<Jugador> jugadoresEspera = new ArrayList<>();
    private int partidasActivas = 0;
    private final int RANGO_INICIAL = 500;
    private final int AUMENTO_POR_SEGUNDO = 100;
    private final Object monitor = new Object();
    private boolean generandoJugadores = true;
    private boolean sistemaEnEjecucion = true;


    public void aumentarPartidas() {
       synchronized (monitor){
        partidasActivas++;
        System.out.println("Partidas activas: " + partidasActivas);
       }
    }


	public void disminuirPartidasAct() {
		synchronized (monitor) {
            partidasActivas--;
            System.out.println("Partidas activas: " + partidasActivas);
        }
	}


    public void agregarJugador(Jugador jugador) {
       synchronized (monitor){
        jugadoresEspera.add(jugador);
       }
    }

    public void setGenerandoJugadores(boolean generando) {
        this.generandoJugadores = generando;
    }

    private Jugador encontrarPartida(Jugador jugador1) {
        long tiempoEsperado = (System.currentTimeMillis() - jugador1.getTiempoEspera()) / 1000;
        int rangoPermitido = RANGO_INICIAL + (int) tiempoEsperado * AUMENTO_POR_SEGUNDO;

        synchronized (monitor) {
            for (Jugador jugador2 : jugadoresEspera) {
                if (jugador1 != jugador2 && Math.abs(jugador1.getRanking() - jugador2.getRanking()) <= rangoPermitido) {
                    return jugador2;
                }
            }
        }
        return null;
    }

    private void revisarEstadoSistema(){
        if(!generandoJugadores && jugadoresEspera.isEmpty() && partidasActivas ==0) {
            sistemaEnEjecucion = false;
        }
    }
    
    @Override
    public void run() {
        while (sistemaEnEjecucion) {
            Jugador jugador1 = null;
            Jugador jugador2 = null;

            synchronized (monitor) {
                if (jugadoresEspera.size() >= 2) {
                    jugador1 = jugadoresEspera.get(0);
                    jugador2 = encontrarPartida(jugador1);     

                    if (jugador2 != null) {
                        jugadoresEspera.remove(jugador1);
                        jugadoresEspera.remove(jugador2);
                    }
                }
            }
            
            if (jugador2 != null) {
                Partidas partidas = new Partidas(jugador1, jugador2, this);
                new Thread(partidas).start();            
            } else {
                try {
                    Thread.sleep(1000);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            revisarEstadoSistema();
        }
        System.out.println("El sistema se detuvo");
    }
}
