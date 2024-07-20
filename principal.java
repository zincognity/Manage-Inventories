import java.util.Scanner;
/*
 * 
 * @author Jesús Reluz | Incognity
 * @version 1.0
 * @see console
 * 
 * Principal file to run the code.
 * 
 */

public class principal{
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		User user = new User();
		String[] menu = {"Iniciar Sesión", "Registrarse"};
        user.Menu("Principal", menu, in);
	}
}
