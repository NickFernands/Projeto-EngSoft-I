public class EmprestimoCommand implements Command{

    private Biblioteca biblioteca;

    public EmprestimoCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    @Override
    public void execute() {
        biblioteca.emprestimo();
    }
}
