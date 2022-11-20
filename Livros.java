import java.util.List;

public abstract class Livros implements Observable {

    public int id;
    public String titulo;
    public String editora;
    public String autores;
    public String edicao;
    public String anoPublicacao;
    private List<Exemplares> listaExemplares;

    private List<Observers> observadores;
    public int numReservas = 0;

    public VozBiblioteca voz = VozBiblioteca.obterVoz();


    // Todos os métodos aqui recebem o userID que foi dado na linha de comando
    // Deve ser evitado tratar esses dados utilizando "if" e "switch" - Usuário deve ter métodos que permitam acessar os valores chaves (ex: dias de empréstimo ou limite de empréstimos)
    // Note que o tratamento de valores limites e algumas regras em geral variam em aplicação dependendo do tipo de usuário (Checar seção 3)

    public void emprestar() { //Seção 3.1

        //Casos de Sucesso de Empréstimo
        voz.falar("Livro " + titulo + "emprestado para [NomeDoUsuario].\n");
        voz.falar("Livro " + titulo + "emprestado para [NomeDoUsuario] e reserva excluída do sistema.\n");

        //Casos de Falha de Empréstimo
        voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] por não haver mais exemplares disponíveis no momento.\n");
        voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] por usuário ter status de devedor.\n");
        voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] por usuário ter atingido o número limite de livros emprestados simultâneamente.\n");
        voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] pelo número de reservas existentes já ter atingido o valor do número de exemplares do livro.\n");
        voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] pelo por usuário já ter uma cópia desse livro em mãos.\n");

    }

    public void devolucao() { //Seção 3.2

        //Caso de Sucesso da Devolução
        voz.falar("Devolução do livro " + titulo + " por [NomeDoUsuario] realizada com sucesso.\n");

        //Caso de Falha da Devolução
        voz.falar("Devolução do livro " + titulo + " por [NomeDoUsuario] não pôde ser efetivada por usuário não possuir uma cópia do livro\n");

    }

    public void reserva() { //Seção 3.3

        //Caso de Sucesso na Reserva
        voz.falar("Livro " + titulo + "foi reservado com sucesso por [NomeDoUsuario]");
        // Se passar de 2 reservas, então, numReservas for de 2 para 3, ativa o notifyObservers();


        //Caso de Falha na Reserva
        voz.falar("Livro " + titulo + "não pôde ser reservado por [NomeDoUsuario] pois o número de reservas simultâneas do usuário já alcançou seu limite.");

    }

    public void checarLivro() { //Seção 3.5.a

        //voz.falar(titulo + numReserva)

        //if (numReservas>0) voz.falar e colocar os nomes dos usuarios que reservaram

        //Fazer um loop e uma String pra pegar todos os valores dos códigos dos exemplares
        //voz.falar() item iii em diante

    }

    public void registerExemplar(Exemplares e) {
        listaExemplares.add(e);
    }

/*    public String getUserEmprestados() { //Ainda precisa do nome do usuário, que depende da classe do usuário
        String todos="";
        for (int i=0 ; i < listaExemplares.size()-1 ; i++) {
            todos.concat(listaExemplares.get(i).)
        }
    }
*/

    public void registerObserver(Observers o) {
        observadores.add(o);
    }

    public void removeObserver(Observers o) {
        int i = observadores.indexOf(o);
        if (i>=0) observadores.remove(o);
    }

    public void notifyObservers() {
        for (int i=0 ; i < observadores.size() ; i++) {
            Observers observer = observadores.get(i);
            observer.update(this);
        }
    }

}
