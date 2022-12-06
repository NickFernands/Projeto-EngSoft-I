public class ReservaCommand implements Command{

    private Biblioteca biblioteca;

    public ReservaCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca.obterBiblioteca();
    }


    @Override
    public void execute() {
        biblioteca.reserva();
    }
}
