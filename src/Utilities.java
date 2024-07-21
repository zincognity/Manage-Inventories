package src;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Utilities extends Colors{
    public ArrayList<String> username;
    public ArrayList<String> password;
    public ArrayList<String> type;
    public ArrayList<ArrayList<String>> userDatabase;
    private String auth;
    public ArrayList<String> idProduct;
    public ArrayList<String> nameProduct;
    public ArrayList<String> descriptionProduct;
    public ArrayList<Integer> priceProduct;
    public ArrayList<Integer> stockProduct;
    public ArrayList<ArrayList<String>> productDatabase;
    public ArrayList<String> idOrder;
    public ArrayList<String> titular;
    public ArrayList<String> table;
    public Map<String, ArrayList<String>> orders;
    public ArrayList<Integer> totalMount;

    public void Menu(String title, String[][] options, Scanner in){
        int option = 0;
        boolean isEnd = false;
        boolean isCorrect = true;
        do {
            isCorrect = true;
            if(auth != null) System.out.println(TEXT_BLUE +"\nBienvenid@ " + auth);
            System.out.printf(TEXT_BRIGHT_YELLOW + "%10s %s\n" , "Menú",title);
            for (int i = 0; i <= options.length; i++) { // Condicionamos con <= porque queremos que una función predeterminada (n. Salir) esté al final.
                if(i == options.length){
                    if(title.equals("Principal User") | title.equals("Principal Admin")){
                        System.out.printf(TEXT_BRIGHT_YELLOW + "%d. " + TEXT_BRIGHT_WHITE +"%s\n" ,i+1, "Cerrar sesión");
                    } else System.out.printf(TEXT_BRIGHT_YELLOW + "%d. " + TEXT_BRIGHT_WHITE +"%s\n" ,i+1, "Salir");
                }
                else System.out.printf(TEXT_BRIGHT_YELLOW +"%d. " + TEXT_BRIGHT_WHITE + "%s\n" ,i+1, options[i][1]);
            }
            try {
                System.out.println("Selecciona una opción: ");
                option = in.nextInt();
                in.nextLine();
                for (int i = 0; i <= options.length; i++) {
                    if(option == options.length + 1){
                        isEnd = true;
                        if(title.equals("Principal User") | title.equals("Principal Admin")){
                            auth = null;
                        };
                    }
                    if(option == i){
                        Clear();
                        System.out.println("Apartado de: " + TEXT_BRIGHT_YELLOW + options[i-1][1] + TEXT_BRIGHT_WHITE);
                        Option(options[i-1][1] ,options[i-1][0], in);
                    }
                    if (option > options.length + 1 || option < 0){
                        isCorrect = false;
                    }
                }
            } catch (java.util.InputMismatchException e) { // Identificamos el valor de la entrada del scanner, y definimos el error.
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                in.nextLine();
                continue;
            }
            if(isEnd){
                Clear();
                System.out.println("Saliendo...");
            };
            if(!isCorrect){
                Clear();
                System.out.println("Opción inválida, por favor, ingrese una opción del menú.");
            } 
        } while (!isEnd);
    }
    
    private void Option(String title, String option, Scanner in){
        switch (option) {
            case "Auth":
                AuthUser(in);
                break;
            case "Register":
                RegisterUser(in);
                break;
            case "ManageUser":
                Clear();
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
                Clear();
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
            case "EditPrice":
                EditParams(in, "Precio");
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
            case "EditOrders":
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
                break;
        }
    }

    private void AuthUser(Scanner in){
        System.out.println("Usuario:");
        String username = in.nextLine();
        System.out.println("Contraseña:");
        String password = in.nextLine();
        Clear();
        Auth(username, password, in);
    }

    public void Clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    private void Auth(String username, String password, Scanner in){
        boolean userFound = false;
        boolean correctPass = false;
        for (int i = 0; i < this.username.size(); i++) {
            if(this.username.get(i).equals(username)) userFound = true;
            if (this.username.get(i).equals(username) && this.password.get(i).equals(password)) {
                userFound = true;
                correctPass = true;
                this.auth = username;
                if (this.type.get(i).equals("User")) {
                    User(in);
                } else if (this.type.get(i).equals("Admin")) {
                    Admin(in);
                }
            }
        }
        if(!userFound){
            System.out.println("Usuario no encontrado.");
            return;
        }
        if(!correctPass){
            System.out.println("Contraseña incorrecta.");
            return;
        }
    }
    
    private void Admin(Scanner in){
        String[][] menu = {{"ManageUser", "Gestionar Usuarios"}, {"ManageProduct", "Gestionar Productos"}, {"ManageOrder", "Gestionar Pedidos"}, {"Report", "Generar Informe"}};
        Menu("Principal Admin", menu, in);
    }

    private void User(Scanner in){
        String[][] menu = {{"ManageProduct", "Gestionar Productos"}, {"ManageOrder", "Gestionar Órdenes"}, {"Report", "Generar Reporte"}};
        Menu("Principal User" ,menu, in);
    }

    private void RegisterUser(Scanner in){
        boolean isCorrect = false;
        boolean isExist;
        String newUsername;
        do {
            isExist = false;
            System.out.println("Usuario:");
            newUsername = in.nextLine();
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
        do {
            System.out.println("Repite la contraseña:");
            String newRepeatPassword = in.nextLine();
            if (newPassword.equals(newRepeatPassword)) {
                username.add(newUsername);
                password.add(newPassword);
                type.add("User");
                isCorrect = true;
                Clear();
                System.out.println("Te has registrado correctamente.");
            } else {
                System.out.println("Las contraseñas deben ser las mismas.");
            }
        } while (!isCorrect);
    }

    private void ChangeType(Scanner in){
        System.out.println("Ingrese el nombre del usuario a cambiar de tipo:");
        String searchUsername = in.nextLine();
        int res = getUserIdentity(searchUsername);
        System.out.println(res);
        if(res == -1) return;
        String getType = type.get(res);
        if(getType.equals("Admin")) type.set(res, "User");
        if(getType.equals("User")) type.set(res, "Admin");
        System.out.println("Se ha cambiado el tipo de usuario exitósamente.");
    }

    private void DeleteUser(Scanner in){
        System.out.println("Ingrese el nombre del usuario a eliminar:");
        String searchUsername = in.nextLine();
        int res = getUserIdentity(searchUsername);
        if(res == -1) return;
        username.remove(res);
        password.remove(res);
        type.remove(res);
        System.out.println("Se ha eliminado al usuario exitósamente.");
    }

    private int getUserIdentity(String searchUsername){
        int number = 0, identity = -1;
        boolean isFound = false;
        for (Object object : username) {
            if(object.equals(searchUsername)){
                identity = number;
                isFound = true;
            }
            number++;
        }
        if(!isFound){
            Clear();
            System.out.println("Usuario no encontrado.");
        }
        return identity;
    }

    private void CreateProduct(Scanner in){
        System.out.println("Ingresa el nombre del producto:");
        String name = in.nextLine();
        System.out.println("Ingresa una descripción: (opcional)");
        String description = in.nextLine();
        boolean isCorrect = false;
        int price = 0, stock = 0;
        do {
            try {
                System.out.println("Ingrese el precio:");
                price = in.nextInt();
                in.nextLine();
                System.out.println("Ingrese el stock disponible:");
                stock = in.nextInt();
                in.nextLine();
                if(price > 0 && stock > 0) isCorrect = true;
            } catch (java.util.InputMismatchException e) { // Identificamos el valor de la entrada del scanner, y definimos el error.
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                in.nextLine();
                continue;
            }
        } while (!isCorrect);
        System.out.println();
        int id = idProduct.size() + 1;
        idProduct.add(String.valueOf(id));
        nameProduct.add(name);
        descriptionProduct.add(description);
        priceProduct.add(price);
        stockProduct.add(stock);
        System.out.println("El producto se ha registrado correctamente.");
    }

    private int identity;
    private void EditProduct(Scanner in){
        this.identity = -1;
        for (String product : nameProduct) {
            System.out.println("- " + product);
        }
        System.out.println("Ingrese el nombre del producto a editar");
        String searchProduct = in.nextLine();
        int identity = GetProductIdentity(searchProduct);
        if(identity == -1) return;
        this.identity = identity;
        Clear();
        String[][] menu = {{"EditNameP","Editar Nombre"},{"EditDescription", "Editar Descripción"}, {"EditPrice", "Editar Precio"}, {"EditStock", "Editar Stock"}};
        Menu("Edición de producto", menu, in);
    }

    private int GetProductIdentity(String searchName){
        int number = 0, identity = -1;
        boolean isFound = false;
        for (Object object : nameProduct) {
            if(object.equals(searchName)){
                identity = number;
                isFound = true;
            }
            number++;
        }
        if(!isFound){
            Clear();
            System.out.println("Producto no encontrado.");
        }
        return identity;
    }

    private void EditParams(Scanner in, String param){
        switch (param) {
            case "Nombre":
                System.out.println("Ingrese el nuevo nombre:" + "(" + param + " actual: " + nameProduct.get(identity) + ")");
                String newNameP = in.nextLine();
                nameProduct.set(identity, newNameP);
                Clear();
                System.out.println("El nombre se ha actualizado correctamente.");
                break;
            case "Descripción":
                System.out.println("Ingrese la nueva descripción:" + "(" + param + " actual: " + descriptionProduct.get(identity) + ")");
                String newDescription = in.nextLine();
                descriptionProduct.set(identity, newDescription);
                Clear();
                System.out.println("La descripción se ha actualizado correctamente.");
                break;
            case "Precio":
                System.out.println("Ingrese el nuevo precio:" + "(" + param + " actual: " + priceProduct.get(identity) + ")");
                int newPrice = in.nextInt();
                in.nextLine();
                priceProduct.set(identity, newPrice);
                Clear();
                System.out.println("El precio se ha actualizado correctamente.");
                break;
            case "Stock":
                System.out.println("Ingrese el nuevo stock:" + "(" + param + " actual: " + stockProduct.get(identity) + ")");
                int newStock = in.nextInt();
                in.nextLine();
                stockProduct.set(identity, newStock);
                Clear();
                System.out.println("El stock se ha actualizado correctamente.");
                break;
            case "Titular":
                ArrayList<String> ordersList = orders.get(titular.get(identity)); // Se copian los valores originales.
                System.out.println("Ingrese el nuevo titular:" + "(" + param + " actual: " + titular.get(identity) + ")");
                String newTitular = in.nextLine();
                titular.set(identity, newTitular);
                orders.remove(titular.get(identity));
                orders.put(newTitular, ordersList);
                Clear();
                System.out.println("El titular se ha actualizado correctamente.");
                break;
            case "Mesa":
                System.out.println("Ingrese la nueva mesa:" + "(" + param + " actual: " + table.get(identity) + ")");
                String newTable = in.nextLine();
                in.nextLine();
                table.set(identity, newTable);
                Clear();
                System.out.println("La mesa se ha actualizado correctamente.");
                break;
            case "Orden":
                boolean hasMoreOrders = true;
                int total = 0, stock[] = new int[idProduct.size()], count[] = new int[idProduct.size()];
                String res;
                String foundTitular = titular.get(identity);
                ArrayList<String> newOrdersList = new ArrayList<>();
                System.out.println("Ingrese la nueva orden:" + "(" + param + " actual: " + orders.get(titular.get(identity)) + ")");
                do {
                    int positionProduct = 0;
                    for (String string : nameProduct) {
                        if(stockProduct.get(positionProduct) > 0) System.out.println("- " + string);
                        positionProduct++;
                    }
                    String orderDetail = in.nextLine();
                    int identity = GetProductIdentity(orderDetail);
                    if(identity == -1) return;
                    if(stockProduct.get(identity) > 0){
                        stock[identity] = stockProduct.get(identity);
                        if(stock[identity] <= count[identity]){
                            System.out.println("No hay stock disponible.");
                        } else{
                            count[identity] = count[identity] + 1;
                            newOrdersList.add(orderDetail);
                            total = total + priceProduct.get(identity);
                        }
                        System.out.println("¿Tiene otra orden?: (y/n)");
                        res = in.nextLine();
                        if(res.equals("n")) hasMoreOrders = false;
                    } else{
                        System.out.println("No hay stock disponible.");
                        continue;
                    }
                } while (hasMoreOrders);
                orders.replace(foundTitular, newOrdersList);
                totalMount.set(identity ,total);
                stockProduct.set(identity, stock[identity] - count[identity]);
                Clear();
                System.out.println("La orden se ha actualizado correctamente.");
                break;
            default:
                break;
        }
    }

    private void DeleteProduct(Scanner in){
        this.identity = -1;
        for (String product : nameProduct) {
            System.out.println("- " + product);
        }
        System.out.println("Ingrese el nombre del producto a eliminar.");
        String searchProduct = in.nextLine();
        int identity = GetProductIdentity(searchProduct);
        if(identity == -1) return;
        this.identity = identity;
        idProduct.remove(identity);
        nameProduct.remove(identity);
        descriptionProduct.remove(identity);
        priceProduct.remove(identity);
        stockProduct.remove(identity);
        Clear();
        System.out.println("El producto se ha eliminado correctamente.");
    }

    private void RegisterOrder(Scanner in){
        boolean hasMoreOrders = true;
        int total = 0, stock[] = new int[idProduct.size()], count[] = new int[idProduct.size()];
        String res;
        System.out.println("Ingrese el nombre del titular:");
        String titularOrder = in.nextLine();
        System.out.println("Ingrese el número de mesa:");
        String tableOrder = in.nextLine();
        System.out.println("Ingrese las órdenes:");
        ArrayList<String> ordersList = new ArrayList<>();
        do {
            int positionProduct = 0;
            for (String string : nameProduct) {
                if(stockProduct.get(positionProduct) > 0) System.out.println("- " + string);
                positionProduct++;
            }
            String orderDetail = in.nextLine();
            int identity = GetProductIdentity(orderDetail);
            if(identity == -1) return;
            if(stockProduct.get(identity) > 0){
                stock[identity] = stockProduct.get(identity);
                if(stock[identity] <= count[identity]){
                    System.out.println("No hay stock disponible.");
                } else{
                    count[identity] = count[identity] + 1;
                    ordersList.add(orderDetail);
                    total = total + priceProduct.get(identity);
                }
                System.out.println("¿Tiene otra orden?: (y/n)");
                res = in.nextLine();
                if(res.equals("n")) hasMoreOrders = false;
            } else{
                System.out.println("No hay stock disponible.");
                continue;
            }
            
        } while (hasMoreOrders);
        stockProduct.set(identity, stock[identity] - count[identity]);
        idOrder.add("ORDER-"+(idOrder.size()+1));
        titular.add(titularOrder);
        table.add(tableOrder);
        orders.put(titularOrder, ordersList);
        totalMount.add(total);
        Clear();
        System.out.println("El pedido se ha registrado correctamente.");
    }

    private void EditOrder(Scanner in){
        this.identity = -1;
        for (String order : idOrder) {
            System.out.println("- " + order);
        }
        System.out.println("Ingrese el ID de la orden a editar");
        String searchOrder = in.nextLine();
        int identity = GetOrderIdentity(searchOrder);
        if(identity == -1) return;
        this.identity = identity;
        Clear();
        String[][] menu = {{"EditTitular","Editar Titular"},{"EditTable", "Editar Mesa"}, {"EditOrders", "Editar Órdenes"}};
        Menu("Edición de orden", menu, in);
    }

    private int GetOrderIdentity(String searchID){
        int number = 0, identity = -1;
        boolean isFound = false;
        for (Object object : idOrder) {
            if(object.equals(searchID)){
                identity = number;
                isFound = true;
            }
            number++;
        }
        if(!isFound){
            Clear();
            System.out.println("Orden no encontrada.");
        }
        return identity;
    }

    private void DeleteOrder(Scanner in){
        this.identity = -1;
        for (String order : idOrder) {
            System.out.println("- " + order);
        }
        System.out.println("Ingrese el ID de la orden a eliminar.");
        String searchOrder = in.nextLine();
        int identity = GetOrderIdentity(searchOrder);
        if(identity == -1) return;
        this.identity = identity;
        orders.remove(titular.get(identity));
        idOrder.remove(identity);
        titular.remove(identity);
        table.remove(identity);
        totalMount.remove(identity);
        Clear();
        System.out.println("La orden se ha eliminado correctamente.");
    }

    private void AllOrders(){
        System.out.printf("%30s\n%8s %8s %5s %20s %20s\n", "ÓRDENES", "ID", "Titular", "Mesa", "Órdenes", "Total");
        for (int i = 0; i < idOrder.size(); i++) {
            System.out.printf("%8s %8s %5s %20s %8s\n", idOrder.get(i), titular.get(i), table.get(i), orders.get(titular.get(i)), totalMount.get(i));
        }
    }

    private void GenerateReport(){
        int total = 0;
        System.out.println("Número de Órdenes: " + orders.size());
        for (int string : totalMount) {
            total = total + string;
        }
        System.out.println("Ganancias totales: " + total);
        System.out.println("El reporte se ha generado exitósamente.");
    }
}
