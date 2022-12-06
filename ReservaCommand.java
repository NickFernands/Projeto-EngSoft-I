public class ReservaCommand implements Command{

    private Biblioteca biblioteca;
    private int codUsu;
    private int codLiv;

    public ReservaCommand(Biblioteca biblioteca, String codUsu, String codLiv) {
        this.biblioteca = biblioteca;
        this.codUsu = Integer.parseInt(codUsu);
        this.codLiv = Integer.parseInt(codLiv);
    }


    @Override
    public void execute() {
        biblioteca.reserva(this.codUsu, this.codLiv);
    }
}
