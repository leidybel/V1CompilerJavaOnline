import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

class Ventana extends JFrame {
	
	 
  private static final int DEFAULT_BUFFER_SIZE = 0;
JLabel labelAreaEntrada;
  JLabel labelAreaSalida;
  JButton compilar;
  JButton ejecutar;
  JButton salir;
  
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
   
   System.out.print("alto "+ al);
   System.out.print("ancho "+ an);
   
    
  setTitle("JavaCompilerOnline");
  setSize(al,an);
  labelAreaEntrada= new JLabel();
  labelAreaEntrada.setBounds(20, 70, 180, 40);
  labelAreaEntrada.setText("Codigo");
  labelAreaEntrada.setFont(new java.awt.Font("Comic Sans MS", 0, 15));
   
  areaEntradaDeTexto = new JTextArea();
  areaEntradaDeTexto.setLineWrap(true);
   
  scrollPaneAreaEntrada = new JScrollPane();
  scrollPaneAreaEntrada.setBounds(10, 100, 1560,230 );
  scrollPaneAreaEntrada.setViewportView(areaEntradaDeTexto);
         
  labelAreaSalida= new JLabel();
  labelAreaSalida.setBounds(20, 70, 180, 600);
  labelAreaSalida.setText("Consola");
  labelAreaSalida.setFont(new java.awt.Font("Comic Sans MS", 0, 15));
  
   
  areaSalidaDeTexto = new JTextArea();
  areaSalidaDeTexto.setLineWrap(true);
  
   
  scrollPaneAreaSalida = new JScrollPane();
  scrollPaneAreaSalida.setBounds(10, 400, 1560, 230);
  scrollPaneAreaSalida.setViewportView(areaSalidaDeTexto);
  
  labelContadorVocales = new JLabel();
  labelContadorVocales.setBounds(380, 280, 190, 20);
   
  compilar = new JButton("Compilar");
  compilar.setBounds(680,660,100,25);
  
  ejecutar = new JButton("Salir");
  ejecutar.setBounds(890,660,100,25);
  
  add(labelContadorVocales);
  add(labelAreaEntrada);
  add(labelAreaSalida);
  add(scrollPaneAreaEntrada);
  add(scrollPaneAreaSalida);
  add(compilar);
  add(ejecutar);
  
   
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

		// AÃ±ade los elementos al menu
		archivo.add(nuevo);
		archivo.add(abrir);
		archivo.add(guardar);
		archivo.add(salir);
		
		menu.add(archivo);
		
		// AÃ±ade la barra de menu a la ventana
			setJMenuBar(menu);

		// Asigna a cada menuItem su listener
		nuevo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				areaEntradaDeTexto.setText("");

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
		
		compilar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(areaEntradaDeTexto.getText().length()>0){
					areaSalidaDeTexto.setText("");
					String ruta = null,nombar=null, cad1 = null;
					String [] cad;
					cad1 = areaEntradaDeTexto.getText();
					cad = cad1.split(" ");
					nombar = cad[2].trim();
					if(nombar.contains("{")){
						nombar = nombar.replace("{", "");
					}
					String file= nombar;
					ruta = guardar(file);
					try {
						Process compilacion = Runtime.getRuntime().exec("javac "+ ruta);
				   
						BufferedReader stdInput = new BufferedReader(new InputStreamReader(compilacion.getInputStream()));
						BufferedReader stdError = new BufferedReader(new InputStreamReader(compilacion.getErrorStream()));
		            
						String s= null;
						//System.out.println("Standard output: ");
						s = stdInput.readLine();
						if (s == null && stdError.readLine() == null){
							areaSalidaDeTexto.append("Compilación Exitosa");
						}else{
							areaSalidaDeTexto.append("\n"+"Errores en Compilación: " + "\n");
		            	
							while ((s = stdError.readLine()) != null) {
								areaSalidaDeTexto.append("\n"+s + "\n");
								//System.out.println(s);
							}
						}
		         
						areaSalidaDeTexto.append("termino compilacion.....");
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
				}else{
						JOptionPane.showMessageDialog(null, "Debe ingresar codigo para compilar",
								  "Error", JOptionPane.WARNING_MESSAGE);
				}
			}	
		});
		
		ejecutar.addActionListener(new ActionListener() {
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

	public String guardar(String nombre){
		String ruta = "D:\\"+nombre.concat(".java");
		File archivo = new File(ruta);
		System.out.println("asi se llamara " +archivo);
		try{
			archivo.createNewFile();
		}catch(IOException e){
			e.printStackTrace();
		}
		
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
		return ruta;
	}
 
}