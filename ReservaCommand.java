public class ReservaCommand implements Command{

    private Biblioteca biblioteca;

    public ReservaCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }


    @Override
    public void execute() {
        biblioteca.reserva();
    }
}
