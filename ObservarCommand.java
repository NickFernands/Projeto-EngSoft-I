public class ObservarCommand implements Command {

    private Biblioteca biblioteca;
    private int codUsu;
    private int codLiv;

    public ObservarCommand(Biblioteca biblioteca, String codUsu, String codLiv) {
        this.biblioteca = biblioteca;
        this.codUsu = Integer.parseInt(codUsu);
        this.codLiv = Integer.parseInt(codLiv);
    }


    @Override
    public void execute() {
        biblioteca.observar(this.codUsu, this.codLiv);
    }
}
