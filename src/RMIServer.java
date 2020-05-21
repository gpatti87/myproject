import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class RMIServer extends UnicastRemoteObject implements RMIServices {
   PersonList person_list = new PersonList();

    protected RMIServer() throws RemoteException {
    }

    public void OtherStuff(){
        //fa altre cose non condivise in remoto
    }

    @Override
    public String getDate() throws RemoteException{
        System.out.println("SERVER LOG: invoked getDate()");
        return new Date().toString();
    }

    @Override
    public String toUP(String s) throws RemoteException {
        System.out.println("SERVER LOG: invoked toUP()");
        return s.toUpperCase();
    }

    @Override
    public ArrayList<Person> getList() throws RemoteException {
        System.out.println("LOG SERVER: invoking getList()");
        return person_list.getList();
    }

    @Override
    public synchronized void addPerson(Person p) throws RemoteException {
        System.out.println("LOG SERVER: invoking addPerson()");
        person_list.addPerson(p);

    }

    @Override
    public synchronized void doIntensiveTask() throws RemoteException {
        System.out.println("Thread that invoked doIntensiveTask: "+Thread.currentThread().getName());
        System.out.println("doing something...");
        int i = 0;
        while (i<100){
            System.out.println("completed "+i+"%");
            i=i+10;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("COMPLETED!");
    }

    public  static void main(String[] args) {
        try {
            RMIServices services = new RMIServer();
            Naming.rebind("listserver",services);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
