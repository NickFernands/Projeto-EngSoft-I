public class ConsultarLivroCommand implements Command{

    private Biblioteca biblioteca;
    private int codLiv;

    public ConsultarLivroCommand(Biblioteca biblioteca, String codLiv) {
        this.biblioteca = biblioteca.obterBiblioteca();
        this.codLiv = Integer.parseInt(codLiv);
    }


    @Override
    public void execute() {
        biblioteca.consultarLivro(codLiv);
    }
}
