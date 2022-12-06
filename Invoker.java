import java.util.HashMap;
import java.util.Map;

public class Invoker {

    private Biblioteca biblioteca;

    {
        biblioteca = Biblioteca.obterBiblioteca();
    }

    public Map<String, Command> comandos = new HashMap<String, Command>();

    public Invoker () {
        comandos.put("emp", new ConsultarLivroCommand(biblioteca)); //emprestimo
        comandos.put("dev", new DevolucaoCommand(biblioteca) ); //devolução
        comandos.put("res", new ReservaCommand(biblioteca) ); //reserva
        comandos.put("obs", new ObservarCommand(biblioteca) ); //obersvar
        comandos.put("liv", new ConsultarLivroCommand(biblioteca) ); //listar infos do livro
        comandos.put("usu", new ConsultarUsuarioCommand(biblioteca)); //lista de emprestimos/reservas de usuarios
        comandos.put("ntf", new ConsultarNotificacaoCommand(biblioteca)); //notificar observadorres
        comandos.put("sai", new SairCommand(biblioteca)); //sair dos sistema
    }

    public void invoke(String command) {
        comandos.get(command).execute();
    }
}
