public class SairCommand implements Command{

    private Biblioteca biblioteca;

    public SairCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void execute() {
        biblioteca.sairDoSistema();
    }
}
