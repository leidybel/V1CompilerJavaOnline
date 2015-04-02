import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.activemq.command.ActiveMQObjectMessage;
import comun.otros.EstacionTrabajo;

public class JMSMessageListener implements MessageListener{	
	Conectados conectados = Conectados.getInstance();
	
	public JMSMessageListener() {
		super();
		System.out.println("Esperando conexión de estudiantes...");
	}

	@Override
	public void onMessage(Message m) {
		ActiveMQObjectMessage om=(ActiveMQObjectMessage)m; 
		try {
			EstacionTrabajo receivedObject=(EstacionTrabajo)om.getObject();
			conectados.agregarConectado(receivedObject);
			System.out.println("la estación de trabajo : " );
			receivedObject.imprimirEstacion();
			System.out.println("Se encoló satisfactoriamente." );
			
			
		} catch (JMSException e) {
			throw new RuntimeException("An exception occured while trying to get a JMS object:"+e.getMessage(),e);
		}

	}

}
