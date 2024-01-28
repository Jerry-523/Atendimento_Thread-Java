# Atendimento_Thread-Java
Sistema de fila de atendimento ao estudante. Os estudantes são atendidos por um número limitado de atendentes


## Requisitos

### 1 - Classe Estudante: 
  Cada estudante é uma thread. O tempo de atendimento do estudante é definido 
quando o estudante é criado e varia entre 2 e 20 minutos.

### 2 - Semáforos: 
  Use semáforos para garantir que não mais do que um número específico de estudantes 
estejam sendo atendidos ao mesmo tempo.

### 3 - ThreadLocal: 
  Use ThreadLocal para armazenar informações específicas da thread,
como o tempo em que o estudante começou a ser atendido.

### 4 - Timer e TimerTask: 
  Use Timer e TimerTask para agendar uma tarefa que verifica a
fila a cada 10 segundos e imprime uma mensagem com o número de estudantes na fila.

### Exemplo de outputs:
#### Output:
`
Estudante Maria está sendo atendido pelo atendente Luciana
Estudante Alex está sendo atendido pelo atendente Adalgiza
Estudante Marco está sendo atendido pelo atendente Dora
Estudante Maria terminou o atendimento. Tempo de atendimento: 5 segundos
Estudante Ana está sendo atendido pelo atendente Luciana
...
//A cada 10 segundos, o TimerTask verifica a fila e imprime uma mensagem
...
Existem 7 estudante na fila
Existem 5 estudante na fila
Existem 2 estudante na fila
Todos os estudante foram atendidos
`
