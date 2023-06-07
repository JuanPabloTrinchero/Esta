import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.*;

public class MajorPersonGeneric<T extends Person> {
    private List<T> items;

    public MajorPersonGeneric() {
        this.items = new ArrayList<>();
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void add(T p){
        items.add(p);
    }

    public void delete(int index){
        items.remove(index);
    }

    @Override
    public String toString() {
        return "MajorPersonGeneric [items=" + items + "]";
    }

    public void filterFromList(List<T> parameterList, int menor) {
        parameterList.forEach(person -> {
            if (person.getAge() >= menor) {
                items.add(person);
            }
        });
    }

    public void filterFromList2(List<T> parameterList, int menor){
        for(Person p : parameterList){
            if(p.getAge() >= menor){
                items.add((T)p);
            }
        }
    }

    public HashSet<String> saveAuthors() {
        HashSet<String> hashSetAuthors = new HashSet<>();
        items.forEach(item -> {
            List<Book> bookList = item.getBooks();
            bookList.forEach(bookItem -> {
                hashSetAuthors.add(bookItem.getAuthor());
            });
        });
        return hashSetAuthors;
    }

    public HashSet<String> saveAuthors2(){      // Valores desordenados y no admiten duplicados
        HashSet<String> hashSetAuthors = new HashSet<>();
        for(Person p : items){
            List<Book> libros = p.getBooks();
            for(Book b : libros){
                hashSetAuthors.add(b.getAuthor());
            }
        }
        return hashSetAuthors;
    }

    public HashMap<Integer,ArrayList<String>> saveTags(){
        HashMap<Integer, ArrayList<String>> hm = new HashMap();
        for(Person p : items){
            int id = p.getId();
            List<String> tags = p.getTags();
            if(!hm.containsKey(id)){        // Si el ID no existe, agrega ID y contenido
                hm.put(id, (ArrayList<String>)tags);
            }else{                          // Si el ID existe, agrega el nuevo contenido al ya existente
                hm.get(id).addAll(tags);
            }
        }
        return hm;
    }

    public HashMap<Integer,ArrayList<String>> guardarEtiquetas(){
        HashMap<Integer,ArrayList<String>> mp = new HashMap();
        for (Person p : items){
            int id = p.getId();
            List<String> etiquetas = p.getTags();//le gurado las etiquetas
            if(mp.containsKey(id)){ //si contiene el id
                List<String> etiquetitas = mp.get(id);//listado de etiquetas que va a tener ese id que va a tener el HashMap
                etiquetitas.addAll(etiquetas);//se guarda en etiquetas
            }else {
                List<String> etiq = new ArrayList<>(etiquetas);
                mp.put(id, (ArrayList<String>) etiq);
            }
        }
        return mp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MajorPersonGeneric<?> other = (MajorPersonGeneric<?>) obj;
        return Objects.equals(items, other.items);
    }

    //6 - Hacer un función que devuelva la cantidad de etiquetas según un dato enviado por
    //parámetro. También se envía el id del usuario. Si la clave no existe, lanzar una
    //excepción propia.

    public int cantTags(int id) throws idNotFoundException {
        int contTags=0;
        boolean flag = false;
        for(Person p : items){
            if(p.getId()==id){
                flag=true;
                for(String tag : p.getTags()){
                    contTags++;
                }
            }
        }
        if(flag==false){
            throw new idNotFoundException();
        }
        return contTags;
    }

    /*
    public int cantTags2(int id){
        int cont = 0;
        for(Person p : items){
            if(p.getId() == id){
                for(String tag : p.getTags()){
                    cont++;
                }
                break;
            }
        }
        return cont;
    }
    */

    public void savePersonsJasonFile(String fileName){
        try(FileWriter writer = new FileWriter(fileName)){
            for(Person p : this.items){
                Gson gson = new GsonBuilder().create();
                String jsonString = gson.toJson(p);
                writer.append(jsonString);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}