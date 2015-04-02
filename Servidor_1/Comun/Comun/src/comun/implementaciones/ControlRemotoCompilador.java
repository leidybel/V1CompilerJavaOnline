package comun.implementaciones;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.imageio.ImageIO;
import comun.control.Control;

public class ControlRemotoCompilador  extends UnicastRemoteObject implements Control {

	private static final long serialVersionUID = 6295288270761256991L;
	private Robot robot;
	int contadorCuadros = 0;
	
	@Override
	public Robot getRobot() throws RemoteException {
		return robot;
	}
	
	@Override
	public void setRobot(Robot robot) throws RemoteException {
		this.robot = robot;
	}
	
	public ControlRemotoCompilador() throws RemoteException {
		super();
		try {
			System.out.println("Resolusión actual del equipo remoto:"+Toolkit.getDefaultToolkit().getScreenSize());
			this.robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public Dimension getResolusion() throws RemoteException{
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	}

	@Override
	public byte[] getCapturaPantalla() throws RemoteException {
		try {
			System.out.println("Cuadros enviados actualmente:" + this.contadorCuadros);
			this.contadorCuadros++;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( pantallazo(), "jpg", baos );
	        baos.flush();
	        byte[] imageInByte = baos.toByteArray();
	        baos.close();
	        return imageInByte;
		} catch (IOException e) {
			throw new RemoteException("",e);
		}
	}

	@Override
	public void moverMouse(int x, int y) throws RemoteException {
		this.robot.mouseMove(x, y);
		this.robot.delay(1000);
	}


	@Override
	public void arrastrarSoltar(int fx, int fy) throws RemoteException {
		this.robot.mousePress(InputEvent.BUTTON1_MASK);
		this.robot.mouseMove(fx, fy);
		this.robot.delay(1000);
		this.robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@Override
	public void digitar(int keyCode) throws RemoteException {
		this.robot.keyPress(keyCode);
		this.robot.delay(1000);
		this.robot.keyRelease(keyCode);
		this.robot.delay(1000);
	}

	@Override
	public void clickDerecho() throws RemoteException {	
        this.robot.mousePress(InputEvent.BUTTON3_MASK);
        this.robot.mouseRelease(InputEvent.BUTTON3_MASK);
	}

	@Override
	public void clickIzquierdo() throws RemoteException {
		this.robot.mousePress(InputEvent.BUTTON1_MASK);
		this.robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
    public BufferedImage pantallazo() throws RemoteException{
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        BufferedImage image = this.robot.createScreenCapture(screenRectangle);
        return image;
    }

}
