public abstract class Usuarios {
    private int codigo;
    private String nome;
    private String tipo;
    private int tempoDeEmprestimo;
    private State estado;

    private int numReservas;

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public void emprestimoBemSucedido(String tituloLivro, String dataEmprestimo, State status, String dataDevolucao) { //Seção 3.5.b parte 1
        //Precisa add um objeto no Array de Histórico de Empréstimos que contenha título, data de empréstimo, status(finalizado ou em curso),
        //data devolucao (realizada ou prevista)
    }

    public void reservaBemSucedida(String tituloLivro) { //Seção 3.5.b parte 2
        //Precisa add um objeto no Array de Reservas que contenha tanto o título que tá sendo passado quanto a data atual
        //a data atual deve vir de um método estático que provavelmente vem da Biblioteca
    }



    public String getNome() { return this.nome; }

    public int getNumReservas() {
        return numReservas;
    }
}
