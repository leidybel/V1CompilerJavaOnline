/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Documento extends JFrame  {
    JFrame ventana;
   
    JTextArea notas1;
    JTextArea notas2;
    JScrollPane scrollNotas1;
    JScrollPane scrollNotas2;
    Dimension dime;
   
    public void inicioDoc () {
    ventana = new JFrame("JavaCompilerOnline");
    
   dime=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
   int an=(int)dime.getWidth();
   int al=(int)dime.getHeight();
      
        // Cra un area de texto con scroll y lo añade a la ventana 
        notas1 = new JTextArea();
        notas1.setLineWrap(true);
       
       
        scrollNotas1 = new JScrollPane();
        scrollNotas1.setBounds(10, 200, 200, 40);
        scrollNotas1.setViewportView(notas1);

        ventana.add(scrollNotas1);
        ventana.setSize(al, an);
        ventana.setLocation(0, 0);
       
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        notas2 = new JTextArea();
        notas2.setLineWrap(true);
       
        scrollNotas2 = new JScrollPane();
        scrollNotas2.setViewportView(notas2);
       
        ventana.setVisible(true);   
   }
     
   
      
    public void iniciarMenu(){
        System.out.println("ENRO MENU");
    JMenuBar menu = new JMenuBar();
 
    JMenu archivo = new JMenu("Archivo");
    JMenu ayuda = new JMenu("Ayuda");
 
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
    ayuda.add(acercaDe);
 
    menu.add(archivo);
    menu.add(ayuda);
 
    // Añade la barra de menu a la ventana
    ventana.setJMenuBar(menu);
    
    // Asigna a cada menuItem su listener
    nuevo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
           notas1.setText("");
        }
    });
    
    abrir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                abrirArchivo();
            } catch (IOException ex) {
                Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
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
        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(ventana)) {
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
                notas1.setText(contenidoFichero.toString());

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    lector.close();
                } catch (IOException ex) {
                    Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void guardarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(ventana)) {
            File archivo = fileChooser.getSelectedFile();
            FileWriter escritor = null;
            try {
                escritor = new FileWriter(archivo);
                escritor.write(notas1.getText());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    escritor.close();
                } catch (IOException ex) {
                    Logger.getLogger(Documento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }  
 }
