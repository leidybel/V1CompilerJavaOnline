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
import comun.otros.Oferente;

public class mainServidor {
	
	//private JFrame frame;
	
	public static void main(String[] args)throws AccessException, RemoteException, UnknownHostException, JMSException {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		MyJMSProducer mp=ac.getBean(MyJMSProducer.class);
		EstacionTrabajo estacion = new EstacionTrabajo();
		ControlRemotoCompilador cr = new ControlRemotoCompilador();
		Oferente oRmi = new Oferente(estacion.getIp(), estacion.getPuerto());
		oRmi.OfrecerObjeto(estacion.nombreObjeto, cr);
		mp.sendMessages(estacion);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					mainServidor window = new mainServidor();
					//window.frame.setVisible(false);
			}
		});
	}

	public mainServidor() {
		initialize();
	}

	private void initialize() {
		/*frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		Ventana doc = new Ventana();
		doc.setVisible(true);
	}

}
