public class ConsultarLivroCommand implements Command{

    private Biblioteca biblioteca;

    public ConsultarLivroCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    @Override
    public void execute() {
        biblioteca.consultarLivro();
    }
}
