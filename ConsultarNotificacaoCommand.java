public class ConsultarNotificacaoCommand implements Command{

    private Biblioteca biblioteca;
    private int codUsu;

    public ConsultarNotificacaoCommand(Biblioteca biblioteca, String codUsu) {
        this.biblioteca = biblioteca.obterBiblioteca();
        this.codUsu = Integer.parseInt(codUsu);
    }


    @Override
    public void execute() {
        biblioteca.consultarNotificacao(codUsu);
    }
}
