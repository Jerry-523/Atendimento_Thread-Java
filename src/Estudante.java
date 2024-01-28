import java.util.Random;

class Estudante extends Thread {
    private static final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private String nome;
    private int tempoAtendimento;

    public Estudante(String nome, int tempoAtendimento) {
        this.nome = nome;
        this.tempoAtendimento = tempoAtendimento;
    }

    @Override
    public void run() {
        System.out.println("Estudante " + nome + " está aguardando atendimento.");
        startTime.set(System.currentTimeMillis());

        try {
            Thread.sleep(tempoAtendimento * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String atendente = Thread.currentThread().getName();
        System.out.println("Estudante " + nome + " está sendo atendido pelo atendente " + atendente);
        System.out.println("Estudante " + nome + " terminou o atendimento. Tempo de atendimento: " +
                (System.currentTimeMillis() - startTime.get()) / 1000 + " segundos");

        FilaAtendimento.decrementarFila();
    }
}