import java.util.ArrayList;

public class PersonList {
    private ArrayList<Person> list = new ArrayList<>();

    public void addPerson(Person p){
        list.add(p);
    }

    public ArrayList<Person> getList() {
        ArrayList<Person> a_list = new ArrayList<>();

        for(Person x:list){
            Person pc = new Person(x.getName(),x.getAge());
            a_list.add(pc);
        }
        return a_list;
    }
}
