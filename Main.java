
public class Main {
    public static void main(String[] args) {

        Professor usuario = new Professor(100, "El Hombre");
        System.out.println(usuario.getStateName());
        usuario.changeStatus();
        System.out.println(usuario.getStateName());
    }
}
