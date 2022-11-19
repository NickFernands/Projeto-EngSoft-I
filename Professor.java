public class Professor extends Usuarios implements Observers{
    private int codigo;
    private String nome;
    private String tipo = "Professor";

    private int tempoDeEmprestimo = 4;

    private State estado;

    public Professor(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = new StateNormal(this);
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTempoDeEmprestimo() {
        return tempoDeEmprestimo;
    }

    public State getEstado() {
        return estado;
    }

    @Override
    public void setEstado(State estado) {
        this.estado = estado;
    }

    public String getStateName() {
        return this.estado.getNome();
    }

    public void changeStatus() {
        estado.changeState();
    }

    @Override
    public void update(Livros livros) {

    }
}
