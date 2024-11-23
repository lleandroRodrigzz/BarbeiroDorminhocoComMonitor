public class BarbeiroDorminhocoMain {
    public static void main(String[] args) {
        int numCadeiras = 3; // Número de cadeiras da barbearia
        Barbearia barbearia = new Barbearia(numCadeiras);

        Barbeiro barbeiro = new Barbeiro(barbearia);
        barbeiro.start();

        // Cria clientes chegando em momentos diferentes
        for (int i = 1; i <= 10; i++) {
            Cliente cliente = new Cliente(i, barbearia);
            cliente.start();

            try {
                Thread.sleep((int) (Math.random() * 5000)); // Máximo de 5 segundos
                                                            // Clientes chegam aleatoriamente
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
