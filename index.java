import java.util.Scanner;
/*
 * 
 * @author Jesús Reluz | Incognity
 * @version 1.0
 * @see README
 * 
 * Principal file to run the code.
 * 
 */

import src.Load;

public class index{
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		Load load = new Load();
		String[][] menu = {{"Auth", "Iniciar Sesión"}, {"Register", "Registrarse"}};
        load.Menu("Principal", menu, in);
	}
}
