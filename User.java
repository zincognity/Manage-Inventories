import java.util.Scanner;

public class User {
    // Definición de variables para la clase Usuario.
    public String[] username;
    public String[] password;
    public String[] type;
    
    public String[][] database = {
        {"Incognity", "2005jesus", "User"},
        {"Incognity1", "2005jesus1", "Admin"}
    };
    // Se define la variable auth para identificar al usuario que ha iniciado sesión.
    public String auth;
    // Como serán varios usuarios registrados, las variables serán una lista o un array de usuarios.

    public void registerData(){
        // Se registran datos por predeterminado que se encuentran en la línea 9.
        for (int i = 0; i < database.length; i++) {
            // Se definen las variables username, password y type de manera conjunta.
            this.username[i] = database[i][0];
            this.password[i] = database[i][1];
            this.type[i] = database[i][2];
        }
    }

    // Definimos como primera instancia al llamar la función de la clase con User() y asignamos el tamaño de la lista de usuarios o el array de usuarios.
    public User(){
        username = new String[database.length];
        password = new String[database.length];
        type = new String[database.length];
    }

    // Definimos la función que permite la autenticación de usuarios, ya sea tipo "Usuario" o "Administrador".
    public void auth(String username, String password, Scanner in){
        // Registramos a los usuarios de manera local (se pueden crear más usuarios).
        registerData();
        // Obtenemos los datos de los usuarios y los guardamos en una variable de tipo array para evaluar el contenido.
        String[] thisUser = this.username;
        String[] thisPass = this.password;
        String[] thisType = this.type;

        // Definimos las variables para reconocer si el usuario existe o si la contraseña obtenida es incorrecta, éstas inicializan en falso.
        boolean userFound = false;
        boolean correctPass = false;

        // Hacemos la búsqueda con un for, esto nos permite buscar dato por dato hasta encontrar uno similar al dato ingresado (el ciclo se repite dependiendo el tamaño de la lista o array de usuarios).
        for (int i = 0; i < database.length; i++) {
            // Verifica si el usuario ingresado existe.
            if(thisUser[i].equals(username)) userFound = true;
            // Verifica si el usuario ingresado y la contraseña existen en el mismo orden, de no ser así, las variables userFound y correctPass siguen en false.
            if (thisUser[i].equals(username) && thisPass[i].equals(password)) {
                // Define las variables userFound y correctPass como true, ya que en esta condicional ya se ha verificado si el usuario y contraseña son correctas en el mismo orden.
                userFound = true;
                correctPass = true;
                this.auth = username;
                // Verifica el tipo de usuario ("Usuario" o "Administrador");
                if (thisType[i].equals("User")) {
                    // Envía al usuario al apartado de usuarios.
                    isUser(in);
                } else if (thisType[i].equals("Admin")) {
                    // Envía al usuario al apartado de administradores.
                    isAdmin(in);
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

    // Definimos la función que abrirá el menú de administradores.
    public void isAdmin(Scanner in){
        // Se definen los apartados que le aparecerán al administrador en el menú.
        String[] menu = {"Gestionar Usuarios", "Gestionar Productos", "Gestionar Informe"};
        // Se llama a la función menú pasándole los parámetros de menu de tipo Array y el in de tipo Scanner.
        Menu(menu, in);
    }

    public void isUser(Scanner in){
        String[] menu = {"Gestionar Productos", "Gestionar Informe"};
        Menu(menu, in);
    }

    // Se define la función menú.
    public void Menu(String[] options, Scanner in){
        // Definición e inicialización de variables: opcion que permite identificar la opción elegida, e isEnd que permite identificar la opción salir. 
        int opcion = 0;
        boolean isEnd = false;
        // Se hace un bucle infinito para mostrar el menú con la condición de que la opción escogida, no sea la de salir.
        do {
            // Colocamos la variable isCorrect dentro del bucle para inicializarlo con true constantemente.
            boolean isCorrect = true;
            // Mensaje de bienvenida al usuario identificado.
            System.out.println("Bienvenid@ " + auth);
            // Desplazamiento del menú.
            System.out.println("Menú Principal:");
            // Realizamos un for para imprimir en la pantalla todas las opciones dependiendo el menu[], que es la lista de opciones.
            for (int i = 0; i <= options.length; i++) { // Condicionamos con <= porque queremos que una función predeterminada (n. Salir) esté al final.
                // Si la variable i es igual al tamaño de las opciones, es porque la opción no se ha definido, por lo tanto la toma como la opción de Salir del programa.
                if(i == options.length) System.out.println(i+1 + ". Salir del programa");
                // De lo contrario es porque las opciones ya se han definido, y las muestra en pantalla.
                else System.out.println(i+1 + ". " + options[i]);
            }
            // Se hace un try para evitar errores de tipeo por parte del usuario.
            try {
                // Se ingresa la opción a escoger.
                System.out.println("Selecciona una opción: ");
                opcion = in.nextInt();
                in.nextLine();
                // Buscamos en todas las opciones la opción escogida con la ayuda de un for.
                for (int i = 0; i <= options.length; i++) {
                    // Si la opción es la última de todas, o el tamaño del options[] es porque ha escogido Salir.
                    if(opcion == options.length + 1) isEnd = true;
                    // Si no, la opción escogida se puede mostrar en pantalla.
                    else if(opcion == i){
                        System.out.println("Ha elegido: " + options[i-1]);
                    } else if (opcion > options.length + 1){ // De lo contrario es porque ha escogido una opción que no está dentro de las opciones.
                        isCorrect = false;
                    }
                }
                // Si isEnd es true, muestra en pantalla (Saliendo...).
                if(isEnd) System.out.println("Saliendo...");
                // Si isCorrect es false, muestra que tiene que ingresar una opción válida.
                if(!isCorrect) System.out.println("Opción inválida, por favor, ingrese una opción del menú.");
            } catch (java.util.InputMismatchException e) { // Identificamos el valor de la entrada del scanner, y definimos el error.
                // Muestra el mensaje de error.
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                in.nextLine();
                // Permite continuar y volver a mostrar el menú.
                continue;
            }
        } while (!isEnd);
    }
}
