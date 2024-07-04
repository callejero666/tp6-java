

public class Partidas implements Runnable {
    private Jugador jugador1;
    private Jugador jugador2;
    private SistemaJuego sistemaJuego;

    public Partidas(Jugador jugador1, Jugador jugador2, SistemaJuego sistemaJuego) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.sistemaJuego = sistemaJuego;
    }

    @Override
    public void run() {
        sistemaJuego.aumentarPartidas();
        System.out.println("Empezo una partida entre el Jugardor " + jugador1.getId() + " y el Jugador " + jugador2.getId());
    
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int puntajes1 = (int) (Math.random()*6);
        int puntajes2 = (int) (Math.random()*6);
        String resultado;

        if (puntajes1 > puntajes2){
            resultado = "Jugador " + jugador1.getId() + "gana la partida";
        } else if (puntajes1 < puntajes2) {
            resultado = "Jugador " + jugador2.getId() + "gana la partida";
        } else {
            resultado = "Empate";
        }
        
        System.out.println("El resultado de la partida: Jugador " + jugador1.getId() + " su puntaje es (" + puntajes1 + ") vs el Jugador " + jugador2.getId() + " su puntaje es (" + puntajes2 + ") - " + resultado);
        sistemaJuego.disminuirPartidasAct();
    }
}
