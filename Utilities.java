import java.util.ArrayList;
import java.util.Scanner;

public class Utilities{
    /*
     * Definición de variables para la clase Usuario.
     * Init User Variables.
     */
    ArrayList<String> username;
    ArrayList<String> password;
    ArrayList<String> type;
    ArrayList<ArrayList<String>> database;
    public String auth;
    //End User Variables.

    /*
     * Se crea la función menú, que será el handler para todos los menú.
     * Init Menu Function.
     */
    public void Menu(String title, String[] options, Scanner in){
        // Definición e inicialización de variables: opcion que permite identificar la opción elegida, e isEnd que permite identificar la opción salir. 
        int option = 0;
        boolean isEnd = false;
        boolean isCorrect = true;
        // Se hace un bucle infinito para mostrar el menú con la condición de que la opción escogida, no sea la de salir.
        do {
            // Colocamos la variable isCorrect dentro del bucle para inicializarlo con true constantemente.
            isCorrect = true;
            // Mensaje de bienvenida al usuario identificado.
            if(auth != null) System.out.println("Bienvenid@ " + auth);
            // Desplazamiento del menú.
            System.out.println(title);
            // Realizamos un for para imprimir en la pantalla todas las opciones dependiendo el menu[], que es la lista de opciones.
            for (int i = 0; i <= options.length; i++) { // Condicionamos con <= porque queremos que una función predeterminada (n. Salir) esté al final.
                // Si la variable i es igual al tamaño de las opciones, es porque la opción no se ha definido, por lo tanto la toma como la opción de Salir del programa.
                if(i == options.length) System.out.println(i+1 + ". Salir");
                else System.out.println(i+1 + ". " + options[i]);
                // De lo contrario es porque las opciones ya se han definido, y las muestra en pantalla.
            }
            // Se hace un try para evitar errores de tipeo por parte del usuario.
            try {
                // Se ingresa la opción a escoger.
                System.out.println("Selecciona una opción: ");
                option = in.nextInt();
                in.nextLine();
                // Buscamos en todas las opciones la opción escogida con la ayuda de un for.
                for (int i = 0; i <= options.length; i++) {
                    // Si la opción es la última de todas, o el tamaño del options[] es porque ha escogido Salir.
                    if(option == options.length + 1){
                        isEnd = true;
                        // Si es el menú principal, es porque ha dejado la autenticación, por lo tanto, el auth se vuelve null.
                        if(title.equals("Principal User") | title.equals("Principal Admin")){
                            auth = null;
                        };
                    }
                    // Si no, la opción escogida se puede mostrar en pantalla.
                    if(option == i){
                        System.out.println("Ha elegido: " + options[i-1]);
                        // Se llama a la función option, y se le da como atributo el String, y el Scanner.
                        Option(options[i-1], in);
                    }
                    // De lo contrario es porque ha escogido una opción que no está dentro de las opciones.
                    if (option > options.length + 1 || option < 0){
                        isCorrect = false;
                    }
                }
            } catch (java.util.InputMismatchException e) { // Identificamos el valor de la entrada del scanner, y definimos el error.
                // Muestra el mensaje de error.
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                in.nextLine();
                // Permite continuar y volver a mostrar el menú.
                continue;
            }
            // Si isEnd es true, muestra en pantalla (Saliendo...).
            if(isEnd) System.out.println("Saliendo...");
            // Si isCorrect es false, muestra que tiene que ingresar una opción válida.
            if(!isCorrect) System.out.println("Opción inválida, por favor, ingrese una opción del menú.");
        } while (!isEnd);
    }
    // End Menu Function.

    /*
     * Se crea la función option, que permite identificar las opciones escogidas en el menú.
     * Init Option Function.
     */
    public void Option(String option, Scanner in){
        // Se identifican las opciones dependiendo el String con la ayuda de un Switch.
        switch (option) {
            case "Iniciar Sesión":
                AuthUser(in); // Se llama a la función authUser.
                break;
            case "Registrarse":
                RegisterUser(in); // Se llama a la función registerUser.
                break;
            case "":
                break;
            case "Gestionar Usuarios":
                // Se abre otro menú para gestionar a los Usuarios.
                String[] menu = {"Agregar Usuario", "Cambiar tipo de Usuario", "Cambiar Contraseña", "Eliminar Usuario"};
                Menu("Gestión de Usuarios", menu, in);
                break;
            case "Agregar Usuario":
                System.out.println("Usuario agregao");
            case null:
            default:
                System.out.println("asd");
                break;
        }
    }
    // End Option Function.

    /*
     * Se crea la función authUser que nos abre un formulario de ingreso de datos: username, password.
     * Init authUser Function.
     */
    public void AuthUser(Scanner in){
        System.out.println("Usuario:");
        String username = in.nextLine();
        System.out.println("Contraseña:");
        String password = in.nextLine();
        Auth(username, password, in);
    }
    // End authUser Function.

    /*
     * Definimos la función que permite la autenticación de usuarios, ya sea tipo "Usuario" o "Administrador".
     * Init auth Function.
     */
    public void Auth(String username, String password, Scanner in){
        // Definimos las variables para reconocer si el usuario existe o si la contraseña obtenida es incorrecta, éstas inicializan en falso.
        boolean userFound = false;
        boolean correctPass = false;

        // Hacemos la búsqueda con un for, esto nos permite buscar dato por dato hasta encontrar uno similar al dato ingresado (el ciclo se repite dependiendo el tamaño de la lista o array de usuarios).
        for (int i = 0; i < this.username.size(); i++) {
            // Verifica si el usuario ingresado existe.
            if(this.username.get(i).equals(username)) userFound = true;
            // Verifica si el usuario ingresado y la contraseña existen en el mismo orden, de no ser así, las variables userFound y correctPass siguen en false.
            if (this.username.get(i).equals(username) && this.password.get(i).equals(password)) {
                // Define las variables userFound y correctPass como true, ya que en esta condicional ya se ha verificado si el usuario y contraseña son correctas en el mismo orden.
                userFound = true;
                correctPass = true;
                this.auth = username;
                // Verifica el tipo de usuario ("Usuario" o "Administrador");
                if (this.type.get(i).equals("User")) {
                    // Envía al usuario al apartado de usuarios.
                    User(in);
                } else if (this.type.get(i).equals("Admin")) {
                    // Envía al usuario al apartado de administradores.
                    Admin(in);
                }
            }
        }
        // Se identifica de que el usuario no ha sido encontrado gracias a que la variable userFound no cambió.
        if(!userFound){
            System.out.println("Usuario no encontrado.");
            return;
        }
        // Se identifica de que la contraseña ingresada es incorrecta gracias a que la variable userFound cambió pero la variable correctPass no.
        if(!correctPass){
            System.out.println("Contraseña incorrecta.");
            return;
        }
    }
    // End auth Function

    /*
     * Definimos la función que abrirá el menú de administradores.
     * Init Admin Function.
     */
    public void Admin(Scanner in){
        // Se definen los apartados que le aparecerán al administrador en el menú.
        String[] menu = {"Gestionar Usuarios", "Gestionar Productos", "Gestionar Informe"};
        // Se llama a la función menú pasándole los parámetros de menu de tipo Array y el in de tipo Scanner.
        Menu("Principal Admin", menu, in);
    }
    // End Admin Function.

    /*
     * Definimos la función que abrirá el menú de usuarios.
     * Init User Function.
     */
    public void User(Scanner in){
        String[] menu = {"Gestionar Pedidos", "Gestionar Informe"};
        Menu("Principal User" ,menu, in);
    }
    // End User Function.

    /*
     * Se crea la función RegisterUser que nos abre un formulario de ingreso de datos: username, password.
     * Init RegisterUser Function
     */
    public void RegisterUser(Scanner in){
        // Se declaran y se definen las variables que permiten saber si el usuario existe o si la contraseña es la misma a la anterior.
        boolean isCorrect = false;
        boolean isExist;
        String newUsername;
        // Bucle infinito para determinar si el usuario ingresado ya existe en la base de datos local.
        do {
            isExist = false;
            System.out.println("Usuario:");
            newUsername = in.nextLine();
            // Revisa si el usuario existe.
            for (Object object : username) {
                if (object.equals(newUsername)) {
                    isExist = true;
                    System.out.println("El nombre de usuario ya existe. Intenta con otro.");
                    break;
                }
            }
        } while (isExist);
        System.out.println("Contraseña:");
        String newPassword = in.nextLine();
        // Bucle infinito para saber si las contraseñas son iguales.
        do {
            System.out.println("Repite la contraseña:");
            String newRepeatPassword = in.nextLine();
            // Si las contraseñas son iguales
            if (newPassword.equals(newRepeatPassword)) {
                // Se agregan los datos de manera equitativa.
                username.add(newUsername);
                password.add(newPassword);
                type.add("User");
                isCorrect = true;
                System.out.println("Te has registrado correctamente.");
            } else { // De lo contrario, le muestra el mensaje y el bucle continúa.
                System.out.println("Las contraseñas deben ser las mismas.");
            }
        } while (!isCorrect);
    }
    // End RegisterUser Function.
}
