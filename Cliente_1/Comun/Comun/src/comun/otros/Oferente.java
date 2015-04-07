package comun.otros;
import java.rmi.AccessException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Oferente {
	private Registry reg;
	private String ip;
	private int puerto;
	
	public Oferente(String ip, int puerto) throws RemoteException{
		this.ip = ip;
		this.puerto = puerto;
		this.reg = LocateRegistry.getRegistry(this.ip, this.puerto);
		LocateRegistry.createRegistry(this.puerto);
	}
	
	public void OfrecerObjeto(String nombre, Object o) throws AccessException, RemoteException{
		this.reg.rebind(nombre, (Remote) o);
		System.out.println("Oferente confirma : Ofreciendo el objeto " + nombre +" en la dirección: " + this.ip + ", puerto: " + this.puerto);
	}
	

}
