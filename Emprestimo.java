import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Emprestimo {
    private String livro;
    private State estado;
    private String dataDeEmprestimo;

    private Date data;
    private String dataFormatada = DateFormat.getDateInstance().format(data);

    public Emprestimo (Livros livro) {
        this.livro = livro.getTitulo();
        this.dataDeEmprestimo = dataFormatada;
        this.estado = new StateEmCurso(this);
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public void changeState() {
        this.estado.changeState();
    }
}
