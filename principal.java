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
		Load user = new Load();
		String[][] menu = {{"Auth", "Iniciar Sesión"}, {"Register", "Registrarse"}};
        user.Menu("Principal", menu, in);
	}
}
