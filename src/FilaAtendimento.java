import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

class FilaAtendimento {
    private static final int NUMERO_ATENDENTES = 3;
    private static int quantidadeEstudantes;
    private static final Semaphore semaforo = new Semaphore(NUMERO_ATENDENTES);
    private static final String[] nomesAtendentes = {"Luciana", "Adalgiza", "Dora"};
    private static final Atendente[] atendentes = new Atendente[NUMERO_ATENDENTES];
    private static String[] alunos;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a quantidade de estudantes na fila: ");
        quantidadeEstudantes = scanner.nextInt();

        alunos = new String[quantidadeEstudantes];

        for (int i = 0; i < NUMERO_ATENDENTES; i++) {
            atendentes[i] = new Atendente(nomesAtendentes[i]);
        }

        for (int i = 0; i < quantidadeEstudantes; i++) {
            System.out.print("Digite o nome do Estudante " + (i + 1) + ": ");
            alunos[i] = scanner.next();
        }

        for (int i = 0; i < quantidadeEstudantes; i++) {
            String nomeEstudante = alunos[i];
            int tempoAtendimento = new Random().nextInt(19) + 2;
            new Estudante(nomeEstudante, tempoAtendimento).start();
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new VerificarFilaTask(), 0, 10000);
    }

    public static class Atendente {
        private String nome;

        public Atendente(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }

    static void decrementarFila() {
        semaforo.release();
    }

    private static class VerificarFilaTask extends TimerTask {
        @Override
        public void run() {
            int estudantesNaFila = quantidadeEstudantes - semaforo.availablePermits();
            System.out.println("Existem " + estudantesNaFila + " estudante(s) na fila");

            if (estudantesNaFila < 1) {
                System.out.println("Todos os estudantes foram atendidos");
                cancel();
            }
        }
    }
}
