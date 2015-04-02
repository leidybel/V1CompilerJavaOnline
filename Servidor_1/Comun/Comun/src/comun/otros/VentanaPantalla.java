<<<<<<< HEAD
package comun.otros;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import comun.control.Control;

public class VentanaPantalla extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	


	private Control cr;
	private JLabel lblImg;
	private boolean estado;
	private Dimension estaPantalla;
	private Dimension laOtraPantalla;
	private int alto;
	private int ancho;
	
	public void terminaDocumento(){
		this.estado = false;
		this.dispose();
	}

	public VentanaPantalla(Control cr) throws RemoteException{
		this.cr = cr;
		lblImg = new JLabel();

		estaPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		laOtraPantalla = cr.getResolusion();
		this.ancho = (int) estaPantalla.getWidth();
		this.alto = (int)(this.ancho /(laOtraPantalla.getWidth()/laOtraPantalla.getHeight()));
		System.out.println("Nueva resolusion: "+ this.ancho + this.alto);
		
        this.getContentPane().add(lblImg, BorderLayout.CENTER);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setSize(this.ancho, this.alto);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int getAncho(){
		return this.getWidth();
	}
	
	public int getAlto(){
		return this.getHeight();
	}
	
	public void MostrarVentanaPantalla() throws IOException, InterruptedException {
		while(estado){
			Thread.sleep(100);
			byte[] screenshotBytes = this.cr.getCapturaPantalla();
			InputStream entrada = new ByteArrayInputStream(screenshotBytes);
			BufferedImage imagenCapturaPantalla = ImageIO.read(entrada);
			imagenCapturaPantalla = this.resize(imagenCapturaPantalla, this.ancho, this.alto);
	        ImageIcon img = new ImageIcon(imagenCapturaPantalla);
	        lblImg.setIcon(img);
		}
	}
	
	public BufferedImage resize(BufferedImage img, int newW, int newH) {  
	    int w = img.getWidth();  
	    int h = img.getHeight();  
	    BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
	    Graphics2D g = dimg.createGraphics();  
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
	    g.dispose();  
	    return dimg;  
	} 
	
	public Dimension getEstaPantalla() {
		return estaPantalla;
	}
	
    public void run() {
        try {
        	
        	System.out.println("inicio hilo ventana pantalla");
        	this.estado = true;
        	repaint();
			this.MostrarVentanaPantalla();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


}
=======
package comun.otros;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import comun.control.Control;

public class VentanaPantalla extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	


	private Control cr;
	private JLabel lblImg;
	private boolean estado;
	private Dimension estaPantalla;
	private Dimension laOtraPantalla;
	private int alto;
	private int ancho;
	
	public void terminaDocumento(){
		this.estado = false;
		this.dispose();
	}

	public VentanaPantalla(Control cr) throws RemoteException{
		this.cr = cr;
		lblImg = new JLabel();

		estaPantalla = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		laOtraPantalla = cr.getResolusion();
		this.ancho = (int) estaPantalla.getWidth();
		this.alto = (int)(this.ancho /(laOtraPantalla.getWidth()/laOtraPantalla.getHeight()));
		System.out.println("Nueva resolusion: "+ this.ancho + this.alto);
		
        this.getContentPane().add(lblImg, BorderLayout.CENTER);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setSize(this.ancho, this.alto);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int getAncho(){
		return this.getWidth();
	}
	
	public int getAlto(){
		return this.getHeight();
	}
	
	public void MostrarVentanaPantalla() throws IOException, InterruptedException {
		while(estado){
			Thread.sleep(100);
			byte[] screenshotBytes = this.cr.getCapturaPantalla();
			InputStream entrada = new ByteArrayInputStream(screenshotBytes);
			BufferedImage imagenCapturaPantalla = ImageIO.read(entrada);
			imagenCapturaPantalla = this.resize(imagenCapturaPantalla, this.ancho, this.alto);
	        ImageIcon img = new ImageIcon(imagenCapturaPantalla);
	        lblImg.setIcon(img);
		}
	}
	
	public BufferedImage resize(BufferedImage img, int newW, int newH) {  
	    int w = img.getWidth();  
	    int h = img.getHeight();  
	    BufferedImage dimg = new BufferedImage(newW, newH, img.getType());  
	    Graphics2D g = dimg.createGraphics();  
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
	    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);  
	    g.dispose();  
	    return dimg;  
	} 
	
	public Dimension getEstaPantalla() {
		return estaPantalla;
	}
	
    public void run() {
        try {
        	
        	System.out.println("inicio hilo ventana pantalla remota");
        	this.estado = true;
        	repaint();
			this.MostrarVentanaPantalla();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


}
>>>>>>> 34a942198644b1af9b979d40b6d2c4ffdff3973a
