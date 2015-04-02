<<<<<<< HEAD
package comun.otros;
import java.awt.Color;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class DemandanteRmi {
	private Registry reg;
	private Object objeto;
	private String ip;
	private int puerto;
	
	
	public DemandanteRmi(String ip, int puerto) throws RemoteException{
		this.ip = ip;
		this.puerto = puerto;
		this.reg = LocateRegistry.getRegistry(this.ip, this.puerto);
	}
	
	public void DemandarObjeto(String nombre) throws AccessException, RemoteException, NotBoundException{
		
		this.objeto = this.reg.lookup(nombre);
		System.out.println("Demandante confirma: Se demando objeto" + nombre + " en la dirección: " + this.ip + ", puerto: " + this.puerto);
	}

	public Registry getReg() {
		return reg;
	}

	public void setReg(Registry reg) {
		this.reg = reg;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

}
=======
package comun.otros;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class DemandanteRmi {
	private Registry reg;
	private Object objeto;
	private String ip;
	private int puerto;
	
	public DemandanteRmi(String ip, int puerto) throws RemoteException{
		this.ip = ip;
		this.puerto = puerto;
		this.reg = LocateRegistry.getRegistry(this.ip, this.puerto);
	}
	
	public void DemandarObjeto(String nombre) throws AccessException, RemoteException, NotBoundException{
		
		this.objeto = this.reg.lookup(nombre);
		System.out.println("Demandante confirma: Se demando objeto" + nombre + " en la dirección: " + this.ip + ", puerto: " + this.puerto);
	}

	public Registry getReg() {
		return reg;
	}

	public void setReg(Registry reg) {
		this.reg = reg;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

}
>>>>>>> 34a942198644b1af9b979d40b6d2c4ffdff3973a
