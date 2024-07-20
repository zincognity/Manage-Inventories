import java.util.ArrayList;

public class User extends Utilities{

    public void registerData(){
        // Se registran datos por predeterminado que se encuentran en la línea 9.
        for (ArrayList<String> in : database) {
            // Se definen las variables username, password y type de manera conjunta.
            username.add(in.get(0));
            password.add(in.get(1));
            type.add(in.get(2));
        }
    }

    // Definimos como primera instancia al llamar la función de la clase con User() y asignamos el tamaño de la lista de usuarios o el array de usuarios.
    public User(){
        username = new ArrayList<>();
        password = new ArrayList<>();
        type = new ArrayList<>();
        database = new ArrayList<>();
        database.add(new ArrayList<String>() {{ add("Incognity"); add("2005jesus"); add("User"); }});
        database.add(new ArrayList<String>() {{ add("Incognity1"); add("2005jesus1"); add("Admin"); }});
        registerData();
    }

    //public void menuUser(){
        
    //}

    //public void addUser (String username, String password, String type){

    //}

    //public void removeUser(String username){

    //}

    //public void updateType(String username, String type){

    //}

    //public void updatePass(String username, String password){

    //}
}
