public class EmprestimoCommand implements Command{

    private Biblioteca biblioteca;
    private int codUsu;
    private int codLiv;

    public EmprestimoCommand(Biblioteca biblioteca, String codUsu, String codLiv) {
        this.biblioteca = biblioteca;
        this.codUsu = Integer.parseInt(codUsu);
        this.codLiv = Integer.parseInt(codLiv);
    }


    @Override
    public void execute() {
        biblioteca.emprestimo(this.codUsu, this.codLiv);
    }
}
