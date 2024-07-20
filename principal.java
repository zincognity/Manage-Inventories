import java.util.Scanner;

public class principal{
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		User user = new User();
		String[] menu = {"Iniciar Sesión", "Registrarse"};
        user.Menu("Principal", menu, in);
	}
}
