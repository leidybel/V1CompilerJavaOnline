import java.awt.EventQueue;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import javax.jms.JMSException;
import javax.swing.JFrame;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import comun.implementaciones.ControlRemotoCompilador;
import comun.otros.EstacionTrabajo;
import comun.otros.OferenteRmi;

public class InterfazServidor {
	
	private JFrame frame;
	
	public static void main(String[] args)throws AccessException, RemoteException, UnknownHostException, JMSException {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		MyJMSProducer mp=ac.getBean(MyJMSProducer.class);
		EstacionTrabajo estacion = new EstacionTrabajo();
		ControlRemotoCompilador cr = new ControlRemotoCompilador();
		OferenteRmi oRmi = new OferenteRmi(estacion.getIp(), estacion.getPuerto());
		oRmi.OfrecerObjeto(estacion.nombreObjeto, cr);
		mp.sendMessages(estacion);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					InterfazServidor window = new InterfazServidor();
					window.frame.setVisible(false);
			}
		});
	}

	public InterfazServidor() {
		initialize();
	}

	private void initialize() {
		Documento doc = new Documento();
		doc.inicioDoc();
		doc.iniciarMenu();
	}

}
