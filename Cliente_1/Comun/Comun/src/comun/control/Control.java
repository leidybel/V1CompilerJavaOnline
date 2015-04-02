package comun.control;


import java.awt.Dimension;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Control extends Remote{
	
	public byte[]  getCapturaPantalla() throws RemoteException;
	public void moverMouse(int x, int y) throws RemoteException;
	public void clickDerecho()throws RemoteException;
	public void clickIzquierdo()throws RemoteException;
	public void arrastrarSoltar(int fx, int fy) throws RemoteException;
	public void digitar(int keyCode) throws RemoteException;
	public Robot getRobot() throws RemoteException;
	void setRobot(Robot robot) throws RemoteException;
	public BufferedImage pantallazo() throws RemoteException;
	public Dimension getResolusion() throws RemoteException;
}
