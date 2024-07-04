import java.lang.Runnable;

public class GeneradorJugador implements Runnable {
    private SistemaJuego sistemaJuego;
    private int jugadoresTotales;

    public GeneradorJugador(SistemaJuego sistemaJuego, int jugadoresTotales) {
        this.sistemaJuego = sistemaJuego;
        this.jugadoresTotales = jugadoresTotales;
    }

    @Override
    public void run() {
        for (int i = 0; i < jugadoresTotales; i++) {
            try {
                Thread.sleep((int) (Math.random()*5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }     

            int ranking = 1000 + (int) (Math.random()*24000);
            Jugador jugador = new Jugador(i, ranking);
            sistemaJuego.agregarJugador(jugador);
            System.out.println("El jugador " + jugador.getId() + " (Ranking: " + jugador.getRanking() + ") se unio a la espera de un emparejamiento.");
        }

        sistemaJuego.setGenerandoJugadores(false);
        System.out.println("Se generaron todos los jugadores.");
    }
}