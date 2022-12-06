public class ConsultarUsuarioCommand implements Command{

    private Biblioteca biblioteca;
    private int codUsu;

    public ConsultarUsuarioCommand(Biblioteca biblioteca, String codUsu) {
        this.biblioteca = biblioteca.obterBiblioteca();
        this.codUsu = Integer.parseInt(codUsu);
    }


    @Override
    public void execute() {
        biblioteca.consultarUsuario(codUsu);
    }
}
