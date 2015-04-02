package comun.otros;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class ImagenCompilador extends JComponent{
	private static final long serialVersionUID = 1L;
	
	Container panel;
	JFrame lienzo = new JFrame();
	BufferedImage imagenCapturaPantalla;
	InputStream entrada;
	int Width = 0;
	int Height = 0;
	byte[] screenshotBytes;
	
	public void cerrarImagenRemota(){
		this.lienzo.dispose();
	}
	
	public void setCapturaPantalla(byte[] screenshotBytes) throws IOException {
			this.setScreenshotBytes(screenshotBytes);
			this.lienzo.setLayout(new BorderLayout());
			this.lienzo.setSize(this.Width,this.Height);
			this.lienzo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.lienzo.setTitle("Imagen Estación de Trabajo ");
			this.panel = lienzo.getContentPane();			
			this.entrada = new ByteArrayInputStream(this.screenshotBytes);
			this.imagenCapturaPantalla = ImageIO.read(entrada);
			this.panel.add(this, BorderLayout.CENTER);
			
			this.lienzo.setVisible(true);
			//this.lienzo.setBounds(0, 0, (int)this.WIDTH, (int)this.HEIGHT);

	}
	
	public JFrame getLienzo() {
		return lienzo;
	}

	public void setLienzo(JFrame lienzo) {
		this.lienzo = lienzo;
	}

	public Container getPanel() {
		return panel;
	}

	public void setPanel(Container panel) {
		this.panel = panel;
	}

	public void setScreenshotBytes(byte[] screenshotBytes) {
		this.screenshotBytes = screenshotBytes;
	}

	public ImagenCompilador(){
		Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		System.out.println("Resolusión de este equipo:"+Toolkit.getDefaultToolkit().getScreenSize());
		this.Height = (int)captureSize.getHeight();//alto
		this.Width = (int)captureSize.getWidth();//ancho
	}
 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.Src);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(this.imagenCapturaPantalla, 0, 0, this.getWidth(), this.getHeight(), null);
	}
}
