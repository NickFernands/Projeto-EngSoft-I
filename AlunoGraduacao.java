public class AlunoGraduacao extends Usuarios {

    private int codigo;
    private String nome;
    private String tipo = "Aluno Graduação";

    private int tempoDeEmprestimo = 3;

    private int limiteDeEmprestimo = 3;

    private State estado;

    public AlunoGraduacao(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = new StateNormal(this);
    }

    public int getTempoDeEmprestimo() {
        return tempoDeEmprestimo;
    }

    public int getLimiteDeEmprestimo() {
        return limiteDeEmprestimo;
    }

    public void setLimiteDeEmprestimo(int limiteDeEmprestimo) {
        this.limiteDeEmprestimo = limiteDeEmprestimo;
    }

    public State getEstado() {
        return estado;
    }

    @Override
    public void setEstado(State estado) {
        this.estado = estado;
    }

    public void changeStatus() {
        estado.changeState();
    }

    public String getStateName() {
       return this.estado.getNome();
    }
}
