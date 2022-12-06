public class ConsultarUsuarioCommand implements Command{

    private Biblioteca biblioteca;

    public ConsultarUsuarioCommand(Biblioteca biblioteca) {
        this.biblioteca = biblioteca.obterBiblioteca();
    }


    @Override
    public void execute() {
        biblioteca.consultarUsuario();
    }
}
