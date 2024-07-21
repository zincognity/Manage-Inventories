import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Utilities{
    /*
     * Definición de variables para la clase Usuario.
     * Init User Variables.
     */
    public ArrayList<String> username;
    public ArrayList<String> password;
    public ArrayList<String> type;
    public ArrayList<ArrayList<String>> userDatabase;
    public String auth;
    //End User Variables.

    /*
     * Definición de variables para la clase Product.
     * Init Product Variables.
     */
    public ArrayList<String> idProduct;
    public ArrayList<String> nameProduct;
    public ArrayList<String> descriptionProduct;
    public ArrayList<Integer> priceProduct;
    public ArrayList<Integer> stockProduct;
    public ArrayList<ArrayList<String>> productDatabase;
    //End Product Variables.

    /*
     * Definición de variables para la clase Order.
     * Init Order Variables.
     */
    public ArrayList<String> idOrder;
    public ArrayList<String> titular;
    public ArrayList<String> table;
    public Map<String, ArrayList<String>> orders;
    public ArrayList<Integer> totalMount;
    public ArrayList<ArrayList<String>> orderDatabase;
    //End Order Variables.

    /*
     * Se crea la función menú, que será el handler para todos los menú.
     * Init Menu Function.
     */
    public void Menu(String title, String[][] options, Scanner in){
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
                else System.out.println(i+1 + ". " + options[i][1]);
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
                        Clear();
                        System.out.println("Ha elegido: " + options[i-1][1]);
                        // Se llama a la función option, y se le da como atributo el String, y el Scanner.
                        Option(options[i-1][1] ,options[i-1][0], in);
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
    public void Option(String title, String option, Scanner in){
        // Se identifican las opciones dependiendo el String con la ayuda de un Switch.
        switch (option) {
            case "Auth":
                AuthUser(in); // Se llama a la función authUser.
                break;
            case "Register":
                RegisterUser(in); // Se llama a la función registerUser.
                break;
            case "ManageUser":
                String[][] menuManager = {{"CreateUser", "Crear Usuario"}, {"ToggleType", "Cambiar tipo de Usuario"}, {"DeleteUser", "Eliminar Usuario"}};
                Menu("Gestión de Usuarios", menuManager, in);
                break;
            case "CreateUser":
                RegisterUser(in);
                break;
            case "ToggleType":
                ChangeType(in);
                break;
            case "DeleteUser":
                DeleteUser(in);
                break;
            case "ManageProduct":
                if(type.get(identity).equals("User")){
                    String[][] menuProductUser = {{"EditProduct", "Editar Producto"}};
                    Menu(title, menuProductUser, in);
                    return;
                }
                String[][] menuProduct = {{"CreateProduct", "Crear Producto"}, {"EditProduct", "Editar Producto"}, {"DeleteProduct", "Eliminar Producto"}};
                Menu("Gestión de Productos", menuProduct, in);
                break;
            case "CreateProduct":
                CreateProduct(in);
                break;
            case "EditProduct":
                EditProduct(in);
                break;
            case "EditNameP":
                EditParams(in, "Nombre");
                break;  
            case "EditDescription":
                EditParams(in, "Descripción");
                break;  
            case "EditPrice":
                EditParams(in, "Price");
                break;  
            case "EditStock":
                EditParams(in, "Stock");
                break;    
            case "DeleteProduct":
                DeleteProduct(in);
                break;
            case "ManageOrder":
                String[][] menuOrder = {{"RegisterOrder", "Registrar Pedido"}, {"EditOrder", "Editar Pedido"}, {"DeleteOrder", "Eliminar Pedido"}, {"AllOrders", "Ver Pedidos"}};
                Menu("Gestión de Productos", menuOrder, in);
                break;
            case "RegisterOrder":
                RegisterOrder(in);
                break;
            case "EditOrder":
                EditOrder(in);
                break;
            case "EditTitular":
                EditParams(in, "Titular");
                break;
            case "EditTable":
                EditParams(in, "Mesa");
                break;
            case "EditOrder":
                EditParams(in, "Orden");
                break;
            case "DeleteOrder":
                DeleteOrder(in);
                break;
            case "AllOrders":
                AllOrders();
                break;
            case "Report":
                GenerateReport();
                break;
            
            case null:
                break;
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
        Clear();
        Auth(username, password, in);
    }
    // End authUser Function.

    /*
     * Se crea la función Clear para limpiar la pantalla.
     * Init Clear Function.
     */
    public void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    // End Clear Function.

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
        String[][] menu = {{"ManageUser", "Gestionar Usuarios"}, {"ManageProduct", "Gestionar Productos"}, {"ManageOrder", "Gestionar Pedidos"}, {"Report", "Generar Informe"}};
        // Se llama a la función menú pasándole los parámetros de menu de tipo Array y el in de tipo Scanner.
        Menu("Principal Admin", menu, in);
    }
    // End Admin Function.

    /*
     * Definimos la función que abrirá el menú de usuarios.
     * Init User Function.
     */
    public void User(Scanner in){
        String[][] menu = {{"ManageProduct", "Gestionar Productos"}, {"ManageOrder", "Gestionar Órdenes"}, {"Report", "Generar Reporte"}};
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

    /*
     * Creamos la función ChangeType que togglea el tipo de usuario.
     * Init ChangeType Function.
     */
    public void ChangeType(Scanner in){
        System.out.println("Ingrese el nombre del usuario a cambiar de tipo:");
        String searchUsername = in.nextLine();
        // Recolectamos la variable entera de la función getUserIdentity para identificar en qué posición se encuentra el usuario.
        int res = getUserIdentity(searchUsername);
        System.out.println(res);
        // Si la respuesta es -1 es porque no encontró al usuario.
        if(res == -1) return;
        // Obtenemos el tipo de usuario, ya sea User o Admin.
        String getType = type.get(res);
        // Si es Admin, lo cambia a User.
        if(getType.equals("Admin")) type.set(res, "User");
        // Si es User, lo cambia a Admin.
        if(getType.equals("User")) type.set(res, "Admin");
        System.out.println("Se ha cambiado el tipo de usuario exitósamente.");
    }
    // End ChangeType Function

    /*
     * Creamos la función DeleteUser que eliminará al usuario haciendo una búsqueda por su username.
     * Init DeleteUser Function.
     */
    public void DeleteUser(Scanner in){
        System.out.println("Ingrese el nombre del usuario a eliminar:");
        String searchUsername = in.nextLine();
        // Recolectamos la variable entera de la función getUserIdentity para identificar en qué posición se encuentra el usuario.
        int res = getUserIdentity(searchUsername);
        // Si la respuesta es -1 es porque no encontró al usuario.
        if(res == -1) return;
        // Se remueven los datos dependiento el orden del usuario.
        username.remove(res);
        password.remove(res);
        type.remove(res);
        System.out.println("Se ha eliminado al usuario exitósamente.");
    }
    // End DeleteUser Function.

    /*
     * Se crea la función getUserIdentity para buscar la posición del usuario.
     * Init getUserIdentity Function
     */
    public int getUserIdentity(String searchUsername){
        // Se declaran y definen las variables number e identity.
        int number = 0, identity = -1;
        boolean isFound = false;
        // Se hace una búsqueda dato por dato para retornar una coincidencia.
        for (Object object : username) {
            // Si uno de los datos coincide con el dato ingresado, la variable que iba sumando, se define en la variable identity.
            if(object.equals(searchUsername)){
                identity = number;
                isFound = true;
            }
            number++;
        }
        // Si no lo encuentra muestra un mensaje referente.
        if(!isFound){
            System.out.println("Usuario no encontrado.");
        }
        // Retorna el número.
        return identity;
    }

    /*
     * Creamos la función CreateProduct para abrir un formulario de crear un producto.
     * Init CreateProduct Function
     */
    public void CreateProduct(Scanner in){
        System.out.println("Ingresa el nombre del producto:");
        String name = in.nextLine();
        System.out.println("Ingresa una descripción: (opcional)");
        String description = in.nextLine();
        // Creamos las variables isCorrect, price y stock e inicializamos con el valor predeterminado.
        boolean isCorrect = false;
        int price = 0, stock = 0;
        do {
            // Asignamos un try para evitar errores de tipeo.
            try {
                System.out.println("Ingrese el precio:");
                price = in.nextInt();
                in.nextLine();
                System.out.println("Ingrese el stock disponible:");
                stock = in.nextInt();
                in.nextLine();
                // Si el precio y el stock se definen con valores mayores a 0, es porque son valores contables reales.
                if(price > 0 && stock > 0) isCorrect = true;
            } catch (java.util.InputMismatchException e) { // Identificamos el valor de la entrada del scanner, y definimos el error.
                // Muestra el mensaje de error.
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                in.nextLine();
                // Permite continuar y volver a mostrar el menú.
                continue;
            }
        } while (!isCorrect);
        System.out.println();
        int id = idProduct.size() + 1;
        // Se agregan los valores de forma equitativa.
        idProduct.add(String.valueOf(id)); // Convierte el id en String.
        nameProduct.add(name);
        descriptionProduct.add(description);
        priceProduct.add(price);
        stockProduct.add(stock);
        System.out.println("El producto se ha registrado correctamente.");
    }
    // End CreateProduct Function

    public int identity;
    /*
     * Creamos la función EditProduct que nos permitirá cambiar los parámetros de un producto.
     * Init EditProduct Function.
     */
    public void EditProduct(Scanner in){
        // Reseteamos el valor de identity.
        this.identity = -1;
        // Se muestra la lista de los nombres de los productos.
        for (String product : nameProduct) {
            System.out.println("- " + product);
        }
        System.out.println("Ingrese el nombre del producto a editar");
        String searchProduct = in.nextLine();
        // Se obtiene la posición del nombre del producto.
        int identity = GetProductIdentity(searchProduct);
        // Si el valor identity es -1 es porque no encontró el producto.
        if(identity == -1) return;
        // Se le asigna a la variable local el nuevo identity.
        this.identity = identity;
        // Se desplaza un menú de edición de producto.
        String[][] menu = {{"EditNameP","Editar Nombre"},{"EditDescription", "Editar Descripción"}, {"EditPrice", "Editar Precio"}, {"EditStock", "Editar Stock"}};
        Menu("Edición de producto", menu, in);
    }
    // End EditProduct Function.

    /*
     * Se crea la función getProductIdentity para identificar la posición de un producto por una búsqueda por nombre.
     * Init getProductIdentity
     */
    public int GetProductIdentity(String searchName){
        // Se declaran y definen las variables number e identity.
        int number = 0, identity = -1;
        boolean isFound = false;
        // Se hace una búsqueda dato por dato para retornar una coincidencia.
        for (Object object : nameProduct) {
            // Si uno de los datos coincide con el dato ingresado, la variable que iba sumando, se define en la variable identity.
            if(object.equals(searchName)){
                identity = number;
                isFound = true;
            }
            number++;
        }
        // Si no lo encuentra muestra un mensaje referente.
        if(!isFound){
            System.out.println("Producto no encontrado.");
        }
        // Retorna el número.
        return identity;
    }
    // End getProductIdentity Function.

    /*
     * Se crea la función EditParams para identificar el parámetro a editar del producto.
     * Init EditParams Function.
     */
    public void EditParams(Scanner in, String param){
        // Se usa un switch para identificar los tipos de parámetros a editar.
        switch (param) {
            case "Nombre":
                System.out.println("Ingrese el nuevo nombre:" + "(" + param + " actual: " + nameProduct.get(identity) + ")");
                String newNameP = in.nextLine();
                nameProduct.set(identity, newNameP);
                System.out.println("El nombre se ha actualizado correctamente.");
                break;
            case "Descripción":
                System.out.println("Ingrese la nueva descripción:" + "(" + param + " actual: " + descriptionProduct.get(identity) + ")");
                String newDescription = in.nextLine();
                descriptionProduct.set(identity, newDescription);
                System.out.println("La descripción se ha actualizado correctamente.");
                break;
            case "Precio":
                System.out.println("Ingrese el nuevo precio:" + "(" + param + " actual: " + priceProduct.get(identity) + ")");
                int newPrice = in.nextInt();
                in.nextLine();
                priceProduct.set(identity, newPrice);
                System.out.println("El precio se ha actualizado correctamente.");
                break;
            case "Stock":
                System.out.println("Ingrese el nuevo stock:" + "(" + param + " actual: " + stockProduct.get(identity) + ")");
                int newStock = in.nextInt();
                in.nextLine();
                stockProduct.set(identity, newStock);
                System.out.println("El stock se ha actualizado correctamente.");
                break;
            case "Titular":
                ArrayList<String> ordersList = orders.get(titular.get(identity));
                System.out.println("Ingrese el nuevo titular:" + "(" + param + " actual: " + titular.get(identity) + ")");
                String newTitular = in.nextLine();
                titular.set(identity, newTitular);
                orders.remove(titular.get(identity));
                orders.put(newTitular, ordersList);
                System.out.println("El titular se ha actualizado correctamente.");
                break;
            case "Mesa":
                System.out.println("Ingrese la nueva mesa:" + "(" + param + " actual: " + stockProduct.get(identity) + ")");
                String newTable = in.nextLine();
                in.nextLine();
                table.set(identity, newTable);
                System.out.println("La mesa se ha actualizado correctamente.");
                break;
            case "Orden":
                String foundTitular = titular.get(identity);
                ArrayList<String> newOrdersList = new ArrayList<>();
                String res;
                boolean hasMoreOrders = true;
                int total = 0;
                System.out.println("Ingrese la nueva orden:" + "(" + param + " actual: " + orders.get(titular.get(identity)) + ")");
                do {
                    for (String string : nameProduct) {
                        System.out.println("- " + string);
                    }
                    String orderDetail = in.nextLine();
                    identity = GetProductIdentity(orderDetail);
                    if(identity == -1) return;
                    newOrdersList.add(orderDetail);
                    total = total + priceProduct.get(identity);
                    System.out.println("¿Tiene otra orden?: (y/n)");
                    res = in.nextLine();
                    if(res.equals("n")) hasMoreOrders = false;
                } while (hasMoreOrders);
                orders.replace(foundTitular, newOrdersList);
                totalMount.set(identity ,total);
                System.out.println("La orden se ha actualizado correctamente.");
                break;
            default:
                break;
        }
    }
    // End EditParams Function.

    /*
     * Se crea la función DeleteProduct para eliminar un producto en específico.
     * Init DeleteProduct Function.
     */
    public void DeleteProduct(Scanner in){
        // Se resetea el valor de identity.
        this.identity = -1;
        // Muestra la lista de los nombres de los productos.
        for (String product : nameProduct) {
            System.out.println("- " + product);
        }
        System.out.println("Ingrese el nombre del producto a eliminar.");
        String searchProduct = in.nextLine();
        // Se descubre la posición del producto.
        int identity = GetProductIdentity(searchProduct);
        // Si identity es -1 es porque no se ha encontrado el producto.
        if(identity == -1) return;
        // Se setea el identity local con el nuevo identity.
        this.identity = identity;
        // Se remueven los datos de manera equitativa.
        idProduct.remove(identity);
        nameProduct.remove(identity);
        descriptionProduct.remove(identity);
        priceProduct.remove(identity);
        stockProduct.remove(identity);
        System.out.println("El producto se ha eliminado correctamente.");
    }

    public void RegisterOrder(Scanner in){
        boolean hasMoreOrders = true;
        int total = 0;
        String res;
        System.out.println("Ingrese el nombre del titular:");
        String titularOrder = in.nextLine();
        System.out.println("Ingrese el número de mesa:");
        String tableOrder = in.nextLine();
        System.out.println("Ingrese las órdenes:");
        ArrayList<String> ordersList = new ArrayList<>();
        do {
            for (String string : nameProduct) {
                System.out.println("- " + string);
            }
            String orderDetail = in.nextLine();
            int identity = GetProductIdentity(orderDetail);
            if(identity == -1) return;
            ordersList.add(orderDetail);
            total = total + priceProduct.get(identity);
            System.out.println("¿Tiene otra orden?: (y/n)");
            res = in.nextLine();
            if(res.equals("n")) hasMoreOrders = false;
        } while (hasMoreOrders);
        idOrder.add("ORDER-"+(idOrder.size()+1));
        titular.add(titularOrder);
        table.add(tableOrder);
        orders.put(titularOrder, ordersList);
        totalMount.add(total);
    }
    /*
     * Creamos la función EditProduct que nos permitirá cambiar los parámetros de un producto.
     * Init EditProduct Function.
     */

    public void EditOrder(Scanner in){
        // Reseteamos el valor de identity.
        this.identity = -1;
        // Se muestra la lista de los nombres de los productos.
        for (String order : idOrder) {
            System.out.println("- " + order);
        }
        System.out.println("Ingrese el ID de la orden a editar");
        String searchOrder = in.nextLine();
        // Se obtiene la posición del nombre del producto.
        int identity = GetOrderIdentity(searchOrder);
        // Si el valor identity es -1 es porque no encontró el producto.
        if(identity == -1) return;
        // Se le asigna a la variable local el nuevo identity.
        this.identity = identity;
        // Se desplaza un menú de edición de producto.
        String[][] menu = {{"EditTitular","Editar Titular"},{"EditTable", "Editar Mesa"}, {"EditOrder", "Editar Órdenes"}};
        Menu("Edición de orden", menu, in);
    }

    /*
     * Se crea la función getProductIdentity para identificar la posición de un producto por una búsqueda por nombre.
     * Init getProductIdentity
     */
    public int GetOrderIdentity(String searchID){
        // Se declaran y definen las variables number e identity.
        int number = 0, identity = -1;
        boolean isFound = false;
        // Se hace una búsqueda dato por dato para retornar una coincidencia.
        for (Object object : idOrder) {
            // Si uno de los datos coincide con el dato ingresado, la variable que iba sumando, se define en la variable identity.
            if(object.equals(searchID)){
                identity = number;
                isFound = true;
            }
            number++;
        }
        // Si no lo encuentra muestra un mensaje referente.
        if(!isFound){
            System.out.println("Orden no encontrada.");
        }
        // Retorna el número.
        return identity;
    }
    // End getProductIdentity Function.

    public void DeleteOrder(Scanner in){
        // Se resetea el valor de identity.
        this.identity = -1;
        // Muestra la lista de los nombres de los productos.
        for (String order : idOrder) {
            System.out.println("- " + order);
        }
        System.out.println("Ingrese el ID de la orden a eliminar.");
        String searchOrder = in.nextLine();
        // Se descubre la posición del producto.
        int identity = GetOrderIdentity(searchOrder);
        // Si identity es -1 es porque no se ha encontrado el producto.
        if(identity == -1) return;
        // Se setea el identity local con el nuevo identity.
        this.identity = identity;
        // Se remueven los datos de manera equitativa.
        orders.remove(titular.get(identity));
        idOrder.remove(identity);
        titular.remove(identity);
        table.remove(identity);
        totalMount.remove(identity);
        System.out.println("La orden se ha eliminado correctamente.");
    }

    // End EditProduct Function.
    public void AllOrders(){
        for (int i = 0; i < idOrder.size(); i++) {
            System.out.println(idOrder.get(i));
            System.out.println(titular.get(i));
            System.out.println(table.get(i));
            System.out.println(orders.get(titular.get(i)));
            System.out.println(totalMount.get(i));
        }
    }

    public void GenerateReport(){
        int total = 0;
        System.out.println("Número de Órdenes: " + orders.size());
        for (int string : totalMount) {
            total = total + string;
        }
        System.out.println("Ganancias totales: " + total);
        System.out.println("El reporte se ha generado exitósamente.");
    }
}
