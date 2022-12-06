public class ObservarCommand implements Command {

    private Biblioteca biblioteca;

    public ObservarCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    @Override
    public void execute() {
        biblioteca.observar();
    }
}
