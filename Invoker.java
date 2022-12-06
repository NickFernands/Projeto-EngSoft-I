import java.util.HashMap;
import java.util.Map;

public class Invoker {

    private Biblioteca biblioteca;

    {
        biblioteca = Biblioteca.obterBiblioteca();
    }

    private String firstArg; //Comando em si, ex: emp, dev, res...
    private String secondArg; // Primeiro argumento seguindo o comando
    private String thirdArg; // Segundo argumento seguindo o comando

    public Map<String, Command> comandos = new HashMap<String, Command>();

    public Invoker () {
        comandos.put("emp", new EmprestimoCommand(biblioteca, secondArg, thirdArg)); //emprestimo
        comandos.put("dev", new DevolucaoCommand(biblioteca, secondArg, thirdArg) ); //devolução
        comandos.put("res", new ReservaCommand(biblioteca, secondArg, thirdArg) ); //reserva
        comandos.put("obs", new ObservarCommand(biblioteca, secondArg, thirdArg) ); //obersvar
        comandos.put("liv", new ConsultarLivroCommand(biblioteca, thirdArg) ); //listar infos do livro
        comandos.put("usu", new ConsultarUsuarioCommand(biblioteca, secondArg)); //lista de emprestimos/reservas de usuarios
        comandos.put("ntf", new ConsultarNotificacaoCommand(biblioteca, secondArg)); //notificar observadorres
        comandos.put("sai", new SairCommand(biblioteca)); //sair do sistema
    }

    public void invoke(String command) {
        comandos.get(command).execute();
    }
}


//todo Adicionar a funcionalidade de ter o Invoker "conversando" com o usuário, tipo myProgram> e tudo mais.
//Também ter um método para voltar ao myProgram> para os diferentes comandos chamarem caso o comando esteja digitado erroneamente, preferencialmente com uma mensagem
//Ex: Caso alguém faça emp 100, tá faltando um argumento, então não deve ser aceito e EmprestimoCommand deve ser capaz de retornar o erro
//Caso o Usuário ou Livro não exista lá na Biblioteca, então a Biblioteca também deve ser capaz de chamar esse método quando detectar essa falha
//Ou seja, criar um método Static em Invoker para voltar esse início