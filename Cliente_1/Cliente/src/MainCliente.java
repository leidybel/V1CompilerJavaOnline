
import java.awt.Color;
import java.awt.EventQueue;



import comun.otros.EstacionTrabajo;
import comun.otros.VentanaPantalla;

import javax.swing.JFrame;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import comun.control.Control;
import comun.otros.Demandante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class MainCliente {
	
	private JFrame frame;
	private JMenu menuConectados;
	private LinkedList<JButton> botonesConectados = new LinkedList<JButton>();
	private JButton botonTemporal;
	private Conectados conectados = Conectados.getInstance();
	private EstacionTrabajo sesion = new EstacionTrabajo();
	private String nombreEstacionTrabajo = null;
	private Demandante dRmi ;
	private Control cSesion;
	private Thread hiloCapturaDocumento;
	private VentanaPantalla vpr = null;
	Color a =null;
	
	

	public static void main(String[] args){	
		
		@SuppressWarnings("unused")
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");	

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainCliente window = new MainCliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainCliente() throws NotBoundException, IOException {
		initialize();
	}

	private void initialize() throws NotBoundException, IOException {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 430, 90);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar;
		
		JButton btnVerEscritorio;
		JButton btnTomarSesionDocumento;
		JButton btnFinalizar;
		
		menuBar= new JMenuBar();
		
		menuConectados = new JMenu("Conectados");
		
		
		menuConectados.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					MainCliente.this.actualizarConectadosMenu();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	
		menuBar.add(menuConectados);		
		
		btnVerEscritorio = new JButton("Ver Documento");
		btnVerEscritorio.setBackground(a.CYAN);
		btnVerEscritorio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sesion = conectados.buscarEstacionTrabajo(nombreEstacionTrabajo);
				if (sesion == null) {
					JOptionPane.showMessageDialog(null, "No ha escogido ninguna maquina.", null, 0);
					System.out.println("No ha escogido ninguna maquina.");
				}else {
					System.out.println(sesion.getIp());
					
					try {
						
						dRmi = new Demandante(sesion.getIp(), sesion.getPuerto());
						dRmi.DemandarObjeto(sesion.getNombreObjeto());
						cSesion = (Control) dRmi.getObjeto();
						
						vpr = new VentanaPantalla(cSesion);
						
						vpr.addMouseListener(new MouseListener(){
							
							@Override
							public void mouseClicked(MouseEvent e) {
								System.out.println("Click en " + e.getX() + ", " + e.getY());
								if((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK){
									try {
										cSesion.clickIzquierdo();
									} catch (RemoteException e1) {
										e1.printStackTrace();
									}
									
								}else if((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){
									try {
										cSesion.clickDerecho();
									} catch (RemoteException e1) {
										e1.printStackTrace();
									}
								}
							}

							@Override
							public void mouseEntered(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseExited(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mousePressed(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseReleased(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}
							
						});
						
						vpr.addKeyListener(new KeyListener(){

							@Override
							public void keyPressed(KeyEvent arg0) {
								System.out.println("Recibido estimulo de teclado...");
								System.out.println("Key Character: " + arg0.getKeyChar() + "; Key Code: " + KeyEvent.getKeyText(arg0.getKeyCode()));
								try {
									cSesion.digitar(arg0.getKeyCode());
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}

							@Override
							public void keyReleased(KeyEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void keyTyped(KeyEvent arg0) {
								// TODO Auto-generated method stub
								
							}
							
						});
						
						vpr.addMouseMotionListener(new MouseMotionListener() {
							
							@Override
							public void mouseMoved(MouseEvent e) {
								System.out.println("el mouse se movio a la posicion: " + e.getPoint());
								try {
									cSesion.moverMouse(e.getX(), e.getY());
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							
							@Override
							public void mouseDragged(MouseEvent e) {
								System.out.println("Hace drag");					
								
							}
						});
						hiloCapturaDocumento = new Thread(vpr, "hilo1");
						hiloCapturaDocumento.start();

					} catch (IOException | NotBoundException e) {
						e.printStackTrace();
					}

				}
			}
		});
		
		menuBar.add(btnVerEscritorio);
		
		btnTomarSesionDocumento = new JButton("Tomar Documento");
		btnTomarSesionDocumento.setBackground(a.CYAN);
		
		btnTomarSesionDocumento.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Se supone que tomo Documento");
				
			}
			
		});
		
		menuBar.add(btnTomarSesionDocumento);
		
		btnFinalizar = new JButton("Finalizar");
		
		btnFinalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(vpr == null){
					JOptionPane.showMessageDialog(null,"No hay sesion la cual finalizar.", null, 0);
					System.out.println("No hay sesion la cual finalizar.");	
				}
				else {
					nombreEstacionTrabajo = null;
					vpr.terminaDocumento();
				}
				
			}
		});
		
		menuBar.add(btnFinalizar);
		
		frame.setJMenuBar(menuBar);

	}
	
	private void actualizarConectadosMenu() throws IOException{
				
		conectados.VerificarConectados();
		
		menuConectados.removeAll();

		if(conectados.getListaConectados().size() > 0){
			for (int i = 0; i < conectados.getListaConectados().size(); i++){
				
				botonTemporal = new JButton(conectados.getListaConectados().get(i).getNombre());
				botonTemporal.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						nombreEstacionTrabajo = botonTemporal.getText();
						System.out.println("Usted ha escogido el computador: " + nombreEstacionTrabajo);
					}
					
				});
				
				botonesConectados.add(botonTemporal);
				
				for (int j = 0; j < botonesConectados.size(); j++) {
					menuConectados.add(botonesConectados.get(i));
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Ningun inicia a�n sesi�n", null, 0);
		}		
	}

	
}



