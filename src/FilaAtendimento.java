import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

class FilaAtendimento {
    private static final int NUMERO_ATENDENTES = 3;
    private static int quantidadeEstudantes;
    private static final Semaphore semaforo = new Semaphore(NUMERO_ATENDENTES);
    private static final String[] atendentes = {"Luciana", "Adalgiza", "Dora"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a quantidade de estudantes na fila: ");
        quantidadeEstudantes = scanner.nextInt();

        for (int i = 1; i <= quantidadeEstudantes; i++) {
            System.out.print("Digite o nome do Estudante " + i + ": ");
            String nomeEstudante = scanner.next();
            int tempoAtendimento = new Random().nextInt(19) + 2;
            new Estudante(nomeEstudante, tempoAtendimento).start();
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new VerificarFilaTask(), 0, 10000);
    }

    static void decrementarFila() {
        semaforo.release();
    }

    private static class VerificarFilaTask extends TimerTask {
        @Override
        public void run() {
            int estudantesNaFila = quantidadeEstudantes - semaforo.availablePermits();
            System.out.println("Existem " + estudantesNaFila + " estudante(s) na fila");

            if (estudantesNaFila == 0) {
                System.out.println("Todos os estudantes foram atendidos");
                cancel();
            }
        }
    }
}
