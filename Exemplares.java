import java.time.LocalDateTime;

public class Exemplares {
    private Livros dono;
    private int codigo;
    private State status = new StateLivre(this);
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private Usuarios usuarioEmprestado;

    public Exemplares(Livros dono, int codigo) {
        this.dono = dono;
        this.codigo = codigo;
        dono.addExemplar(this);
    }

    public String getEmprestado() {
        return usuarioEmprestado.getNome();
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public State getStatus() {
        return status;
    }

    public Usuarios getUsuarioEmprestado() {
        return usuarioEmprestado;
    }

    public void setUsuarioEmprestado(Usuarios usuarioEmprestado) {
        this.usuarioEmprestado = usuarioEmprestado;
    }

    public void setStatus(State status) {
        this.status = status;
    }
}
