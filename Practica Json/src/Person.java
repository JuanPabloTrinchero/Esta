import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;            //SEARCH: google.code.gson
import com.google.gson.GsonBuilder;

public class Person /*implements Serializable*/ {
    private Boolean active;
    private int id;
    private int age;
    private String eyeColor;
    private String nombre;
    private String genero;
    private String registered;
    private double latitude;
    private double longitude;
    private List<String> tags;
    private List<Book> book;
    public Person(Boolean active, int id, int age, String eyeColor, String nombre, String genero, String registered,
                  double latitude, double longitude) {
        this.active = active;
        this.id = id;
        this.age = age;
        this.eyeColor = eyeColor;
        this.nombre = nombre;
        this.genero = genero;
        this.registered = registered;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tags = new ArrayList<>();
        this.book = new ArrayList<>();
    }

    public Person() {
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Book> getBooks() {
        return book;
    }

    public void setBooks(List<Book> books) {
        this.book = books;
    }

    @Override
    public String toString() {
        return "Person [active=" + active + ", id=" + id + ", age=" + age + ", eyeColor=" + eyeColor + ", nombre="
                + nombre + ", genero=" + genero + ", registered=" + registered + ", latitude=" + latitude
                + ", longitude=" + longitude + ", tags=" + tags + ", books=" + book + "]";
    }

    public List<Person> readPersonJsonFile(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new GsonBuilder().create();
            Person[] personsArray = gson.fromJson(reader, Person[].class);
            return Arrays.asList(personsArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void savePersonJasonFile(String fileName){
        try(FileWriter writer = new FileWriter(fileName)){
            Gson gson = new GsonBuilder().create();
            String jsonString = gson.toJson(this);
            writer.append(jsonString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}