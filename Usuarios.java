public abstract class Usuarios {
    private int codigo;
    private String nome;
    private String tipo;
    private int tempoDeEmprestimo;
    private State estado;

    public void setEstado(State estado) {
        this.estado = estado;
    }
}
