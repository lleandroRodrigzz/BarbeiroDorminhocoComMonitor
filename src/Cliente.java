public class Cliente extends Thread{
    private final int idCliente;
    private final Barbearia barbearia;

    public Cliente(int idCliente, Barbearia barbearia) {
        this.idCliente = idCliente;
        this.barbearia = barbearia;
    }

    @Override
    public void run() {
        barbearia.entrarBarbearia(idCliente);
    }
}
