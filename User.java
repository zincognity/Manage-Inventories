import java.util.ArrayList;

public class User extends Utilities{

    public void registerData(){
        // Se registran datos por predeterminado que se encuentran en la línea 9.
        for (ArrayList<String> user : userDatabase) {
            // Se definen las variables username, password y type de manera conjunta.
            username.add(user.get(0));
            password.add(user.get(1));
            type.add(user.get(2));
        }
    }

    // Definimos como primera instancia al llamar la función de la clase con User() y asignamos el tamaño de la lista de usuarios o el array de usuarios.
    public User(){
        username = new ArrayList<>();
        password = new ArrayList<>();
        type = new ArrayList<>();
        userDatabase = new ArrayList<>();
        userDatabase.add(new ArrayList<String>() {{ add("Incognity"); add("2005jesus"); add("User"); }});
        userDatabase.add(new ArrayList<String>() {{ add("Incognity1"); add("2005jesus1"); add("Admin"); }});
        registerData();
    }
}
