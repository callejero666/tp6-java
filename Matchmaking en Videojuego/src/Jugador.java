public class Jugador {
    private int id;
    private int ranking;
    private long tiempoEspera;

    public Jugador (int id, int ranking) {
        this.id = id;
        this.ranking = ranking;
        this.tiempoEspera = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public int getRanking() {
        return ranking;
    }

    public long getTiempoEspera() {
        return tiempoEspera;
    }

}
