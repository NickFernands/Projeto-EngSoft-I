public class ConsultarNotificacaoCommand implements Command{

    private Biblioteca biblioteca;

    public ConsultarNotificacaoCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca.obterBiblioteca();
    }


    @Override
    public void execute() {
        biblioteca.consultarNotificacao();
    }
}
