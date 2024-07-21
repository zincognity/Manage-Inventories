import java.util.ArrayList;
import java.util.HashMap;

public class Load extends Utilities{

    private void registerData(){
        // Se registran datos por predeterminado que se encuentran en la línea 9.
        for (ArrayList<String> user : userDatabase) {
            // Se definen las variables username, password y type de manera conjunta.
            username.add(user.get(0));
            password.add(user.get(1));
            type.add(user.get(2));
        }

        for (ArrayList<String> product : productDatabase) {
            // Se definen las variables username, password y type de manera conjunta.
            idProduct.add(product.get(0));
            nameProduct.add(product.get(1));
            descriptionProduct.add(product.get(2));
			priceProduct.add(Integer.parseInt(product.get(3)));
			stockProduct.add(Integer.parseInt(product.get(4)));
        }
    }

    // Definimos como primera instancia al llamar la función de la clase con User() y asignamos el tamaño de la lista de usuarios o el array de usuarios.
    public Load(){
        username = new ArrayList<>();
        password = new ArrayList<>();
        type = new ArrayList<>();
        userDatabase = new ArrayList<>();
        userDatabase.add(new ArrayList<String>() {{ add("Incognity"); add("2005jesus"); add("User"); }});
        userDatabase.add(new ArrayList<String>() {{ add("Incognity1"); add("2005jesus1"); add("Admin"); }});
        
        idProduct = new ArrayList<>();
        nameProduct = new ArrayList<>();
        descriptionProduct = new ArrayList<>();
        priceProduct = new ArrayList<>();
		stockProduct = new ArrayList<>();
        productDatabase = new ArrayList<>();
        productDatabase.add(new ArrayList<String>() {{ add("0"); add("Fideos verdes"); add("Plato de arroz con fideos verdes."); add("25"); add("10"); }});
        productDatabase.add(new ArrayList<String>() {{ add("1"); add("Milanesa"); add("Plato de arroz con pollo empanizado."); add("30"); add("2");}});
        
        idOrder = new ArrayList<>();
        titular = new ArrayList<>();
        table = new ArrayList<>();
        orders = new HashMap<>();
        totalMount = new ArrayList<>();
        
        ArrayList<String> orderDetails1 = new ArrayList<>();
        orderDetails1.add("Plato a la cubana");
        orderDetails1.add("Otro plato");

        idOrder.add("ORDER-1");
        titular.add("Juan");
        table.add("3");
        orders.put("Juan", orderDetails1);
        totalMount.add(100);

        ArrayList<String> orderDetails2 = new ArrayList<>();
        orderDetails2.add("Plato a la cuba");
        orderDetails2.add("Otro pato");

        idOrder.add("ORDER-2");
        titular.add("Juan2");
        table.add("2");
        orders.put("Juan2", orderDetails2);
        totalMount.add(100);

        
        registerData();
    }
}
