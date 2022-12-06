import java.util.*;

public class Invoker {

    private Biblioteca biblioteca;

    {
        biblioteca = Biblioteca.obterBiblioteca();
    }

    private String firstArg; //Comando em si, ex: emp, dev, res...
    private String secondArg; // Primeiro argumento seguindo o comando
    private String thirdArg; // Segundo argumento seguindo o comando
    Scanner scanner = new Scanner(System.in);
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

    public void invoke() {
        comandos.get(firstArg).execute();
    }

    //demonstrar de alguma forma q o usuario pode dar input novamente
    //Usar essa função como a "Static" mencionada em outros métodos sempre que
    //ou argumentos falhem (aqui no Invoker) ou quando Usuário/Livro não são encontrados lá na Biblioteca
    public void userInput() {
        ArrayList<String> inputs = new ArrayList<String>();
        String msg = scanner.nextLine();
        String[] palavras = msg.split("\s");

        inputs.addAll(Arrays.asList(palavras).subList(0, 3));

        scanner.close();

        firstArg = inputs.get(0);
        secondArg = inputs.get(1);
        thirdArg = inputs.get(2);
    }
}


//todo Adicionar a funcionalidade de ter o Invoker "conversando" com o usuário, tipo myProgram> e tudo mais.
//Também ter um método para voltar ao myProgram> para os diferentes comandos chamarem caso o comando esteja digitado erroneamente, preferencialmente com uma mensagem
//Ex: Caso alguém faça emp 100, tá faltando um argumento, então não deve ser aceito e EmprestimoCommand deve ser capaz de retornar o erro
//Caso o Usuário ou Livro não exista lá na Biblioteca, então a Biblioteca também deve ser capaz de chamar esse método quando detectar essa falha
//Ou seja, criar um método Static em Invoker para voltar esse início