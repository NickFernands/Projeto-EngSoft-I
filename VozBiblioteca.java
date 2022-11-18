public class VozBiblioteca {

    private VozBiblioteca () {}

    private static VozBiblioteca instancia;
    public static VozBiblioteca obterVoz() {
        if (instancia==null) instancia = new VozBiblioteca();
        return instancia;
    }

    public void falar (String conteudo) {
        System.out.println(conteudo);
    }
}
