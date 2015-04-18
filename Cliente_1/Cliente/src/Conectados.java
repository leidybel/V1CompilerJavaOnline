import java.io.IOException;
import java.util.LinkedList;
import comun.otros.EstacionTrabajo;

public class Conectados {
	private static Conectados instance =  new Conectados();
	private LinkedList<EstacionTrabajo> listaConectados = new LinkedList<EstacionTrabajo>();
	
	private Conectados(){ 
		
	}
	
	public EstacionTrabajo buscarEstacionTrabajo(String nombre){
		EstacionTrabajo temporal = null;
		for (int i = 0; i < listaConectados.size(); i++) {
			if(listaConectados.get(i).getNombre().equals(nombre)){
				temporal = listaConectados.get(i);
			}
		}
		return temporal;
	}
	
	public LinkedList<EstacionTrabajo> getListaConectados() {
		return listaConectados;
	}
	
	public void agregarConectado(EstacionTrabajo nuevo){
		this.listaConectados.add(nuevo);
	}

	public static Conectados getInstance(){
		return instance;
	}
	
	public void VerificarConectados() throws IOException{
		for (int i = 0; i < this.listaConectados.size(); i++) {
			if(this.listaConectados.get(i).isConectado()==false){
				System.out.println("eliminando: " +this.listaConectados.get(i).getNombre());
				this.listaConectados.remove(i);
				
			}
		}
	}

}
