import java.util.HashMap;
import java.util.Map;

public class Invoker {

    public Map<String, Command> comandos = new HashMap<String, Command>();

    public Invoker () {
        comandos.put("emp", new ConsultarLivroCommand(Biblioteca biblioteca)); //emprestimo
        comandos.put("dev", ); //devolução
        comandos.put("res", ); //reserva
        comandos.put("obs", ); //obersvar
        comandos.put("liv", ); //listar infos do livro
        comandos.put("usu", ); //lista de emprestimos/reservas de usuarios
        comandos.put("ntf", ); //notificar observadorres
        comandos.put("sai", new SairCommand()); //sair dos sistema
    }

    public void invoke(String command) {
        comandos.get(command).execute();
    }
}
