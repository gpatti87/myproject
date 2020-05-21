import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class RMIClient {
    public static void main(String[] args) {
        //posso mettere menu e altre cose

        String address = args[0];
        String rmi_name = args[1];

        try {
            RMIServices server = (RMIServices) Naming.lookup("rmi://" + address + "/" + rmi_name);

            System.out.println("L'ora attuale è: ");
            System.out.println(server.getDate());

            System.out.println("Converted to up: " + server.toUP("ciao!!"));

            boolean go = true;
            Scanner user_input = new Scanner(System.in);


            while (go) {
                System.out.println("---------------------------");
                System.out.println("0 - ADD Person");
                System.out.println("1 - REMOVE Person");
                System.out.println("2 - LIST ALL Person");
                System.out.println("3 - SAVE Person");
                System.out.println("4 - QUIT Person");
                System.out.println("5 - BIG complex computation");
                System.out.println("---------------------------");
                System.out.println("Enter your choice-->");
                int choice = user_input.nextInt();

                switch (choice) {
                    case 0:
                        System.out.println("Name: ");
                        String name = user_input.next();
                        System.out.println("Age: ");
                        int age = user_input.nextInt();
                        Person x = new Person(name, age);
                        server.addPerson(x);
                        break;
                    case 1:
                        break;
                    case 2:
                        ArrayList<Person> mylist = server.getList();
                        System.out.println("Received list->");
                        System.out.println(mylist);
                        break;
                    case 3:
                        break;
                    case 4:
                        go = false;
                        System.out.println("Quitting Client");
                        break;
                    case 5:
                        server.doIntensiveTask();
                        break;
                }

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
