import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.util.*;
public class Main {
    //region Metodos Estaticos
    public static List<Person> readPersonJsonFileMAIN(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new GsonBuilder().create();
            Person[] personsArray = gson.fromJson(reader, Person[].class);
            return Arrays.asList(personsArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Person> readPersonJsonFileMAINPath(String path) {    //"E:\\Users\\Usuario\\Desktop\\UTN\\2023 - 3er Cuattimestre\\Laboratorio de Programacion\\Practica\\Practica Json\\generated.json"
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new GsonBuilder().create();
            Person[] personsArray = gson.fromJson(reader, Person[].class);
            return Arrays.asList(personsArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //endregion
    public static void main(String[] args) {
        //Person person = new Person();
        List<Person> persons = /*person.*/readPersonJsonFileMAIN("generated.json");

            for(Person p : persons){
                System.out.println(p.toString());
            }
            Person p = persons.get(2);
            System.out.println("Persona Sola");
            System.out.println(p.toString());

        MajorPersonGeneric<Person> majorPersons = new MajorPersonGeneric<>();

        majorPersons.filterFromList2(persons, 18);

        System.out.println("Mayores Edad");
        System.out.println(majorPersons.getItems());

        Person ppp = new Person(true, 2, 32, "Brown", "Juan", "M", "register", 13.5, 14.6);

        ppp.getTags().add("HolaPuto");

        Book book0 = new Book("15", "HPL");

        ppp.getBooks().add(book0);

        majorPersons.add(ppp);

        for(Person person : majorPersons.getItems()){
            System.out.println(person.toString());
        }

        System.out.println("Guardar Etiquetas");
        HashMap<Integer, ArrayList<String>> mapaEtiquetas = new HashMap<>();
        mapaEtiquetas = majorPersons.saveTags();

        System.out.println(mapaEtiquetas);
        for(Integer index : mapaEtiquetas.keySet()){        //Muestra un HashMap
            System.out.println(index+" - "+mapaEtiquetas.get(index));
        }

        HashSet<String> authors = majorPersons.saveAuthors();

        System.out.println(authors);

        //System.out.println("Punto 6 MAL HECHO");
        //System.out.println("Cant TAGS id 2: "+majorPersons.cantTags(4));

        System.out.println("Punto 6");
        int searchId = 1;

        try{
            int cantTagsP = majorPersons.cantTags(searchId);
            System.out.println("Cant tags id "+searchId+": "+cantTagsP);
        }catch(Exception e){
            System.out.println("Id NOT Found - Person Not Found");
        }

        System.out.println("Guardar persona en archivo Json");
        Person pExtra = new Person(true, 10, 32, "Brown", "Juan Pablo", "M", "123456", 15.2, 15.8);

        pExtra.savePersonJasonFile("PersonaExtra.json");

        //majorPersons.add(pExtra);
        //majorPersons.delete(0);
        majorPersons.savePersonsJasonFile("ListaPersonasExtras.json");
    }
}