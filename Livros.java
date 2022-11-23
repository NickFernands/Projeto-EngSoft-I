import java.util.List;

public class Livros implements Observable {

    private int codigo;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoPublicacao;
    private List<Exemplares> listaExemplares;
    private List<Observers> observadores;
    private List<Usuarios> reservantes;
    private int numReservas = 0;
    private int numEmprestados = 0;

    public VozBiblioteca voz = VozBiblioteca.obterVoz();


    // Todos os métodos aqui recebem o userID que foi dado na linha de comando
    // Deve ser evitado tratar esses dados utilizando "if" e "switch" - Usuário deve ter métodos que permitam acessar os valores chaves (ex: dias de empréstimo ou limite de empréstimos)
    // Note que o tratamento de valores limites e algumas regras em geral variam em aplicação dependendo do tipo de usuário (Checar seção 3)


    public Livros(int codigo, String titulo, String editora, String autores, String edicao, String anoPublicacao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
    }

    public void addExemplar(Exemplares exemplar) {
        listaExemplares.add(exemplar);
    }

    private void emprestimoDeuCerto(Exemplares livro, Usuarios usuario) { //Shortcut para evitar repetição de código no método emprestar()
        numReservas--;
        numEmprestados++;
        livro.getStatus().changeState();
        livro.setUsuarioEmprestado(usuario);
        livro.setDataEmprestimo(java.time.LocalDateTime.now());
        livro.setDataDevolucao(java.time.LocalDateTime.now().plusDays(usuario.getTempoDeEmprestimo()));
        usuario.emprestimoBemSucedido(titulo, livro.getDataEmprestimo() , livro.getDataDevolucao());
    }

    public int emprestar(Usuarios usuario) { //Seção 3.1 - Importante fazer a Biblioteca buscar o Usuário e enviar ele pra essa função
        //SEMPRE que alguém pegar o livro, cuidar de dar o assign para algum dos exemplares

        //Condição de falha de Devedor (ii)
        if (usuario.getEstado().getNome().equals("Devedor")) {
            voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] por usuário ter status de devedor.\n");
            return 0;
        }

        //Checando disponibilidade de exemplar e se o usuário já tem ele em mãos (i)
        int jaEmprestado = 0;
        int disponibilidade = 0;
        Exemplares livro = null;
        for ( int i=0 ; i < listaExemplares.size() ; i++ ) {
            if (listaExemplares.get(i).getStatus().getNome().equals("Livre")) {
                disponibilidade++;
                if (disponibilidade==1) { //Atribuindo o Exemplar, que talvez será emprestado, a uma variável
                    livro = listaExemplares.get(i);
                }
                if (listaExemplares.get(i).getUsuarioEmprestado()==usuario) { //Definindo que esse usuário já tem um livro desse emprestado
                    jaEmprestado++;
                }
            }
        }
        //Condição de falha de Disponibilidade (i)
        if (disponibilidade==0) {
            voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] por não haver mais exemplares disponíveis no momento.\n");
            return 0;
        }


        //Checagem se o usuário fez a reserva do livro
        int reservado = 0;
        for ( int i=0 ; i < listaExemplares.size() ; i++ ) {
            if (reservantes.get(i)==usuario) {
                reservado = 1;
                break;
            }
        }

        //Se chegar até aqui, Usuários da classe privilegiada já podem pegar o livro independentemente de reserva ou não

        //Caso seja professor E tenha feito reserva
        if (usuario.getTipo().equals("Professor") && reservado==1) {
            emprestimoDeuCerto(livro, usuario);
            voz.falar("Livro " + titulo + "emprestado para [NomeDoUsuario] e reserva excluída do sistema.\n");
            return 1;
        }
        //Caso seja professor E não tenha feito reserva
        if (usuario.getTipo().equals("Professor")) {
            emprestimoDeuCerto(livro, usuario);
            voz.falar("Livro " + titulo + "emprestado para [NomeDoUsuario].\n");
            return 1;
        }

        //A partir daqui, estamos com Classe não privilegiada

        //Usuario atingiu limite de emprestimos (iii)
        if (usuario.getNumEmprestimos() >= usuario.getLimiteDeEmprestimo()) {
            voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] por usuário ter atingido o número limite de livros emprestados simultâneamente.\n");
            return 0;
        }

        //Usuario já tem um empréstimo daquele livro (vi)
        if (jaEmprestado>0) {
            voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] pelo por usuário já ter uma cópia desse livro em mãos.\n");
            return 0;
        }

        //Caso já tenha reservas demais pro livro e o usuário não tenha reserva (iv)
        if (numReservas>disponibilidade && reservado==0) {
            voz.falar("Livro " + titulo + "não pode ser emprestado para [NomeDoUsuario] pelo número de reservas existentes já ter atingido o valor do número de exemplares disponíveis do livro.\n");
            return 0;
        }

        //Caso já tenha reservado
        if (reservado==1) {
            emprestimoDeuCerto(livro, usuario);
            voz.falar("Livro " + titulo + "emprestado para [NomeDoUsuario] e reserva excluída do sistema.\n");
            return 1;
        }

        //Caso não tenha reservado mas ainda tem mais exemplares do que reservas
        emprestimoDeuCerto(livro, usuario);
        voz.falar("Livro " + titulo + "emprestado para [NomeDoUsuario].\n");
        return 1;

    }

    public int devolucao(Usuarios usuario) { //Seção 3.2 - Importante fazer a Biblioteca buscar o Usuário e enviar ele pra essa função

        for (int i=0 ; i < listaExemplares.size() ; i++) { //Busca nos exemplares o usuario em questão

            //Caso de Sucesso da Devolução
            if (listaExemplares.get(i).getUsuarioEmprestado() == usuario) {
                listaExemplares.get(i).getStatus().changeState();
                listaExemplares.get(i).setUsuarioEmprestado(null);
                listaExemplares.get(i).setDataEmprestimo(java.time.LocalDateTime.now());
                listaExemplares.get(i).setDataDevolucao(java.time.LocalDateTime.now().plusDays(usuario.getTempoDeEmprestimo()));
                numEmprestados--;

                usuario.livroDevolvido(listaExemplares.get(i)); //Passar pro usuario, por algum método dele, as informações que precisam para atualizar no array de histórico de empréstimos e tal

                voz.falar("Devolução do livro " + titulo + " por [NomeDoUsuario] realizada com sucesso.\n");
                return 1;

            }

        }

        //Caso de Falha da Devolução
        voz.falar("Devolução do livro " + titulo + " por [NomeDoUsuario] não pôde ser efetivada por usuário não possuir uma cópia do livro\n");
        return 0;

    }


    public int reserva(Usuarios usuario) { //Seção 3.3 - Importante fazer a Biblioteca buscar o Usuário e enviar ele pra essa função

        //Caso de Falha na Reserva
        if(usuario.getNumReservas()>=3) {
            voz.falar("Livro " + titulo + "não pôde ser reservado por [NomeDoUsuario] pois o número de reservas simultâneas do usuário já alcançou seu limite.");
            return 0;
        }
        //Caso de Sucesso na Reserva
        else {
            voz.falar("Livro " + titulo + "foi reservado com sucesso por [NomeDoUsuario]");
            numReservas++;
            if(numReservas==3) notifyObservers(); //Passou de 2 para 3 reservas
            usuario.reservaBemSucedida(getTitulo());
            return 1;
        }

    }

    public void checarLivro() { //Seção 3.5.a

        //Listar o título e o número de reservas
        voz.falar("Título: " + titulo + "\nNúmero de Reservas: " + numReservas + "\n");

        //Listar o nome de todos reservantes
        for (int i=0 ; i < reservantes.size() ; i++) {
            voz.falar("Nome reservante: " + reservantes.get(i).getNome() + "\n");
        }

        //Listando informações sobre cada exemplar e finalizando a computação do comando
        for (int i=0 ; i < listaExemplares.size() ; i++) {
            voz.falar("Informações de Exemplar: " + listaExemplares.get(i).getCodigo() + " - " + listaExemplares.get(i).getStatus());
            if (listaExemplares.get(i).getStatus().getNome().equals("Emprestado")) voz.falar(" - " + listaExemplares.get(i).getEmprestado() + listaExemplares.get(i).getDataEmprestimo() + listaExemplares.get(i).getDataDevolucao() +"\n");
            else voz.falar("\n");
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void registerObserver(Observers o) { //Seção 3.4 - Biblioteca deve buscar o Usuario e tratá-lo como Observers e passar para esse método do livro
        observadores.add(o);
    }

    public void removeObserver(Observers o) { //Seção 3.4 - Biblioteca deve buscar o Usuario e tratá-lo como Observers e passar para esse método do livro
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
