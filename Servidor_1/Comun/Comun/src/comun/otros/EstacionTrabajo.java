package comun.otros;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EstacionTrabajo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public InetAddress estaEstacion;
	public String nombre ;
	public int puerto = 2800;
	public String nombreObjeto = "ControlAplicacion";
	public String ip ;

	
	public EstacionTrabajo(){
		try {
			this.estaEstacion = InetAddress.getLocalHost();
			this.nombre = this.estaEstacion.getHostName();
			this.ip = this.estaEstacion.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public void imprimirEstacion(){
		System.out.println("Datos: " +this.nombre +", "+ this.ip + ", " + this.puerto);
	}

	public InetAddress getEstaEstacion() {
		return estaEstacion;
	}

	public void setEstaEstacion(InetAddress estaEstacion) {
		this.estaEstacion = estaEstacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public boolean isConectado(){
		boolean conectado = false;

		try {
			Registry reg = LocateRegistry.getRegistry(this.ip, this.puerto);
			reg.lookup(this.nombreObjeto);
		} catch (RemoteException | NotBoundException e) {
			System.out.println("No se esta ofreciendo el objeto " + this.nombreObjeto + " en la dirección " + this.ip + ", y el puerto " + this.puerto + ". ");
			return conectado;
		}

		conectado = true;
		System.out.println("Se esta ofreciendo el objeto " + this.nombreObjeto + " en la dirección " + this.ip + ", y el puerto " + this.puerto + ". ");

		return conectado;
	}

	public String getNombreObjeto() {
		return nombreObjeto;
	}

	public void setNombreObjeto(String nombreObjeto) {
		this.nombreObjeto = nombreObjeto;
	}

}
