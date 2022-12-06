public class DevolucaoCommand implements Command{
    private Biblioteca biblioteca;

    public DevolucaoCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    @Override
    public void execute() {
        biblioteca.devolucao();
    }
}
