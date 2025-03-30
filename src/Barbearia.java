public class Barbearia {
    private final int cadeiras;
    private int clientesEsperando = 0; // Número de clientes na sala de espera
    private boolean barbeiroDormindo = true;

    public Barbearia(int cadeiras) {
        this.cadeiras = cadeiras;
    }

    // Cliente entra na barbearia
    public synchronized void entrarBarbearia(int idCliente) {
        System.out.println("Cliente " + idCliente + " entrou na barbearia.");

        if (clientesEsperando == cadeiras) { //Barbearia Lotada
            System.out.println("Sala de espera cheia! Cliente " + idCliente + " foi embora.");
            return;
        }

        clientesEsperando++; // Cliente senta em uma cadeira
        System.out.println("Cliente " + idCliente + " está esperando. Clientes esperando: " + clientesEsperando);

        if (barbeiroDormindo) {
            System.out.println("Cliente " + idCliente + " acordou o barbeiro.");
            barbeiroDormindo = false;
            notify(); // Acorda o barbeiro
        }

        try {
            wait(); // Cliente espera até ser atendido
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Erro: " + e.getMessage());
        }
    }

    // Barbeiro atende um cliente
    public synchronized void atenderCliente() {
        while (clientesEsperando == 0) {
            System.out.println("Barbeiro está dormindo...");
            barbeiroDormindo = true;

            try {
                wait(); // Barbeiro dorme até que um cliente chegue
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Erro: " + e.getMessage());
            }
        }

        clientesEsperando--; //Barbeiro atende um cliente

        System.out.println("Barbeiro está cortando cabelo. Clientes restantes: " + clientesEsperando);

        notifyAll(); //Notifica um cliente para ser atendido
        try {
            Thread.sleep(4000); //Simula o tempo do corte
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
