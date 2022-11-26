import java.util.ArrayList;

public class AlunoPos extends Usuarios {

    private int codigo;
    private String nome;
    private String tipo = "Aluno Pós-Graduação";
    private int tempoDeEmprestimo = 4;
    private int limiteDeEmprestimo = 4;
    private State estado;
    private final ArrayList<Emprestimo> emprestimosCorrentes = new ArrayList<Emprestimo>();
    private final ArrayList<Emprestimo> emprestimosPassados = new ArrayList<Emprestimo>();
    private final ArrayList<Emprestimo> reservas = new ArrayList<Emprestimo>();

    public AlunoPos(int codigo, String nome) {
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
