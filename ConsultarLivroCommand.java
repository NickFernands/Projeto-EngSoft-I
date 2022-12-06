public class ConsultarLivroCommand implements Command{

    private Biblioteca biblioteca;

    public ConsultarLivroCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca.obterBiblioteca();
    }


    @Override
    public void execute() {
        biblioteca.consultarLivro();
    }
}
