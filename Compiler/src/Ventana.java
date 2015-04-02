import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Ventana extends JFrame implements ActionListener {
	
	 
  JLabel labelAreaEntrada;
  JLabel labelAreaSalida;
  JButton boton1;
  JLabel labelContadorVocales;
  JTextArea areaEntradaDeTexto;
  JScrollPane scrollPaneAreaEntrada;
  JTextArea areaSalidaDeTexto;
  JScrollPane scrollPaneAreaSalida;
 
  int cantidadVocales=0;
  String vocales="";
   
  
public Ventana() {
     setLayout(null);
     iniciaComponentes();
     iniciarMenu();
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

public static void main(String args[]) {
    Ventana ventana;
    ventana = new Ventana();
    
    ventana.setVisible(true);
 }
 

private void iniciaComponentes() {
//  labelTitulo= new JLabel();
//  labelTitulo.setFont(new java.awt.Font("Comic Sans MS", 0, 28));
//  labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//  labelTitulo.setText("Eventos del Teclado");
//  labelTitulo.setBorder(javax.swing.BorderFactory.
// createBevelBorder(javax.swing.border.BevelBorder.RAISED));
//  labelTitulo.setBounds(110, 20, 300, 40);
// 
	  
    Dimension dime = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
   int al=(int)dime.getWidth();
   int an=(int)dime.getHeight();
   System.out.print("alto"+ al);
   System.out.print("ancho"+ an);
   
    

   setTitle("JavaCompilerOnline");
   setSize(1050,1000);
  labelAreaEntrada= new JLabel();
  labelAreaEntrada.setBounds(20, 70, 180, 40);
  labelAreaEntrada.setText("Codigo");
   
  areaEntradaDeTexto = new JTextArea();
  areaEntradaDeTexto.setLineWrap(true);
   
  scrollPaneAreaEntrada = new JScrollPane();
  scrollPaneAreaEntrada.setBounds(10, 100, 1000,350 );
  scrollPaneAreaEntrada.setViewportView(areaEntradaDeTexto);
         
  labelAreaSalida= new JLabel();
  labelAreaSalida.setBounds(20, 170, 180, 600);
  labelAreaSalida.setText("Consola");
   
  areaSalidaDeTexto = new JTextArea();
  areaSalidaDeTexto.setLineWrap(true);
  
   
  scrollPaneAreaSalida = new JScrollPane();
  scrollPaneAreaSalida.setBounds(10, 500, 1000, 350);
  scrollPaneAreaSalida.setViewportView(areaSalidaDeTexto);
  
  labelContadorVocales = new JLabel();
  labelContadorVocales.setBounds(380, 280, 190, 20);
   
  boton1=new JButton("Compilar");
  boton1.setBounds(890,860,100,25);
 
 
 
  add(labelContadorVocales);
  add(labelAreaEntrada);
  add(labelAreaSalida);
  add(scrollPaneAreaEntrada);
  add(scrollPaneAreaSalida);
  add(boton1);
   
}


	public void iniciarMenu() {
		System.out.println("ENRO MENU");
		JMenuBar menu = new JMenuBar();

		JMenu archivo = new JMenu("Archivo");
		

		JMenuItem nuevo = new JMenuItem("Nuevo");
		JMenuItem abrir = new JMenuItem("Abrir...");
		JMenuItem guardar = new JMenuItem("Guardar");
		JMenuItem salir = new JMenuItem("Salir");
		JMenuItem acercaDe = new JMenuItem("Acerca de...");

		// Añade los elementos al menu
		archivo.add(nuevo);
		archivo.add(abrir);
		archivo.add(guardar);
		archivo.add(salir);
		//ayuda.add(acercaDe);

		menu.add(archivo);
		
		// Añade la barra de menu a la ventana
			setJMenuBar(menu);

		// Asigna a cada menuItem su listener
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//areaEntradaDeTexto.setText("");
				String file= "C:\\Users\\ASUS\\Documents\\compilador.java";
				//String file2= "C:\\Users\\ASUS\\Documents\\compilador";
				try {
					Process compilacion = Runtime.getRuntime().exec("javac "+ file);
				    try {
						compilacion.waitFor();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BufferedReader stdInput = new BufferedReader(new InputStreamReader(compilacion.getInputStream()));
		            BufferedReader stdError = new BufferedReader(new InputStreamReader(compilacion.getErrorStream()));

		            
		            // Read command standard output
		            String s= null;
		            System.out.println("Standard output: ");
		            s = stdInput.readLine();
		            		if (s == null){
		            		areaSalidaDeTexto.append("Exitoso ");
		            }
		            while ((s = stdInput.readLine()) == null) {
		               
		                if (s==null)
		                	areaSalidaDeTexto.append(s.toString());
		            }

		            // Read command errors
		            s= null;
		            System.out.println("Standard error: ");
		            if((s = stdError.readLine()) != null){
		            	areaSalidaDeTexto.append("Errores ");
			            	
		            }
		            while ((s = stdError.readLine()) != null) {
		            	areaSalidaDeTexto.append(s.toString());
	                	System.out.println(s);
		             }
		            	System.out.println("termino compilacion.....");
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		abrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					abrirArchivo();
				} catch (IOException ex) {
					Logger.getLogger(Interface.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});
		guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarArchivo();
			}
		});
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		

	}
	

	public void abrirArchivo() throws IOException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(this)) {
			File archivo = fileChooser.getSelectedFile();
			FileReader lector = null;
			try {
				lector = new FileReader(archivo);
				BufferedReader bfReader = new BufferedReader(lector);

				String lineaFichero;
				StringBuilder contenidoFichero = new StringBuilder();

				// Recupera el contenido del fichero
				while ((lineaFichero = bfReader.readLine()) != null) {
					contenidoFichero.append(lineaFichero);
					contenidoFichero.append("\n");
				}

				// Pone el contenido del fichero en el area de texto
				areaEntradaDeTexto.setText(contenidoFichero.toString());

			} catch (FileNotFoundException ex) {
				Logger.getLogger(Interface.class.getName()).log(Level.SEVERE,
						null, ex);
			} catch (IOException ex) {
				Logger.getLogger(Interface.class.getName()).log(Level.SEVERE,
						null, ex);
			} finally {
				try {
					lector.close();
				} catch (IOException ex) {
					Logger.getLogger(Interface.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}
	}

	public void guardarArchivo() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(this)) {
			File archivo = fileChooser.getSelectedFile();
			FileWriter escritor = null;
			try {
				escritor = new FileWriter(archivo);
				escritor.write(areaEntradaDeTexto.getText());
			} catch (FileNotFoundException ex) {
				Logger.getLogger(Interface.class.getName()).log(Level.SEVERE,
						null, ex);
			} catch (IOException ex) {
				Logger.getLogger(Interface.class.getName()).log(Level.SEVERE,
						null, ex);
			} finally {
				try {
					escritor.close();
				} catch (IOException ex) {
					Logger.getLogger(Interface.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
 

}