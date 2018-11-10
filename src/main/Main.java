/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package main;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
*
* @author: Raúl Swanston, Belkis Rivera y Scotty
*/

public class Main extends JFrame{

private JButton botonComenzar, botonManual, botonSalir;



public Main()
{
super ("Estacionamiento");
Container contenedor = getContentPane();
contenedor.setLayout (new FlowLayout());

// Crea Boton Comenzar
Icon entrada1 = new ImageIcon ("");
Icon entrada2 = new ImageIcon ("");
botonComenzar = new JButton (" Comenzar", entrada1);
botonComenzar.setRolloverIcon(entrada2);
contenedor.add(botonComenzar);

// Crea Boton Manual
Icon manual1 = new ImageIcon ("");
Icon manual2 = new ImageIcon ("");
botonManual = new JButton (" ", manual1);
botonManual.setRolloverIcon(manual2);
contenedor.add(botonManual);

// Crea Boton Salir
Icon salir1 = new ImageIcon ("salir1.jpg");
Icon salir2 = new ImageIcon ("salir2.jpg");
botonSalir = new JButton (" Salir", salir1);
botonSalir.setRolloverIcon(salir2);
contenedor.add(botonSalir);

// registrar manejadores de eventos
ManejadorBoton manejador = new ManejadorBoton();
botonComenzar.addActionListener(manejador);
botonManual.addActionListener(manejador);
botonSalir.addActionListener(manejador);

setSize (600, 200);
setVisible (true);
}


public static void main(String args[])
{
// TODO code application logic here
Main aplicacion = new Main();
aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

// Subprograma que inicia la operacion de pilas y colas
public void pilaColaIntroducir()
{
int max=10;
int pila[] = new int[max]; // Arreglo para la pila del estacionamiento
int cola[] = new int[max]; // Arreglo para la cola de la cola de espera
int sacadoPila[] = new int[max]; // Arreglo que suma las veces que un auto se mueve en la pila para permitir la salida de otro
int sacadoCola[] = new int[max]; // Arreglo que suma las veces que un auto se mueve en la cola para permitir la salida de otro
int tope = 0, topeCola = 0; // Contadores
int b = 0, opc; // Contadores del for
int dato; // Variable de tipo entero
String salida, opcion, dat; // Variables de tipo caracter

do
{
opc=0; // Variable de tipo entero de seleccion

salida = "\n\t\tESTACIONAMIENTO\t\t\n\nHa entrado a estacionamiento. ¿Que desea hacer?:\n\n"
+"1. (E) Entrar\n2. (S) Sacar\n3. Mostrar\n0. salir";
JTextArea areaSalida = new JTextArea(); // LLama al cuadro de texto del programa pila cola
areaSalida.setText(salida);
JOptionPane.showMessageDialog(null, areaSalida, "Estacionamiento", JOptionPane.INFORMATION_MESSAGE);
opcion = JOptionPane.showInputDialog ("Diganos el número de la opción de su preferencia");
opc = Integer.parseInt (opcion);

if ( opc == 1) // accion de la seleccion 1
{
if (tope == max){
if (topeCola == max)
JOptionPane.showMessageDialog(null, "El "Estacionamiento" y la "Cola De Espera" estan al máximo ¡Disculpe los inconvenientes!", "Aviso", JOptionPane.INFORMATION_MESSAGE);

else
{
dato=0;
dat = JOptionPane.showInputDialog ("Diganos el número de placa");
dato = Integer.parseInt (dat);
cola[topeCola]=dato;
topeCola++;
JOptionPane.showMessageDialog(null, "¡Estacionamiento lleno! A sido puesto en "Cola De Espera"", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
}
else
{
dato=0;
dat = JOptionPane.showInputDialog ("Diganos el número de placa");
dato = Integer.parseInt (dat);
pila[tope]=dato;
tope++;
}
}

if ( opc == 2) // Accion de la seleccion 2
{
if (tope == 0)
{
JOptionPane.showMessageDialog(null, "Estacionamiento Vacio", "Aviso", JOptionPane.INFORMATION_MESSAGE);
}
else
{
dato=0;
dat = JOptionPane.showInputDialog ("Diganos el número de placa");
dato = Integer.parseInt (dat);
for (int cont = 0; cont < pila.length; cont++)
if (pila[cont]==dato)
{ 
pila[cont] = 0;
tope--;
for (b = cont; b <= 8; b++)
sacadoPila[b + 1] += 1;
JOptionPane.showMessageDialog(null, "El auto con placa Nº" + dato + " ha sido retirado."
+ "\nEl número de veces movido para permitir la salida de otros fue: "
+ sacadoPila[cont], "Aviso", JOptionPane.INFORMATION_MESSAGE);
}
for (int cont = 0; cont < cola.length; cont++)
if (cola[cont]==dato)
{ 
cola[cont] = 0;
topeCola--;
for (b = cont; b <= 8; b++)
sacadoCola[b + 1] += 1;
JOptionPane.showMessageDialog(null, "El auto con placa Nº" + dato + " ha sido retirado."
+ "\nEl número de veces movido para permitir la salida de otros fue: "
+ sacadoCola[cont], "Aviso", JOptionPane.INFORMATION_MESSAGE);
}
}
}

if ( opc == 3) // accion de la seleccion 3
{
if (tope == 0) 
{
JOptionPane.showMessageDialog(null, "Estacionamiento vacio", "Aviso", JOptionPane.INFORMATION_MESSAGE);
}
else if (topeCola!=0)
{
JOptionPane.showMessageDialog(null, "Mostrando el estacionamiento", "Aviso", JOptionPane.INFORMATION_MESSAGE);
salida = "\n\t\tMOSTRANDO ESTACIONAMIENTO\t\t\n\nEste es el estado del estacionamiento:\n\n"
+"1. El ultimo auto fue el: " + pila[tope - 1]
+"\n2. Ocupados: " + tope
+"\n3. Disponibles: " + (max - tope)
+"\n\nEste es el estado de la Cola de Espera:\n\n"
+"1. El ultimo auto fue el: " + cola[topeCola - 1]
+"\n2. Ocupados: " + topeCola
+"\n3. Disponibles: " + (max - topeCola);
JTextArea areaSalida2 = new JTextArea();
areaSalida2.setText(salida);
JOptionPane.showMessageDialog(null, areaSalida2, "Estacionamiento", JOptionPane.INFORMATION_MESSAGE);
}
else
{
JOptionPane.showMessageDialog(null, "Mostrando el estacionamiento", "Aviso", JOptionPane.INFORMATION_MESSAGE);
salida = "\n\t\tMOSTRANDO ESTACIONAMIENTO\t\t\n\nEste es el estado del estacionamiento:\n\n"
+"1. El ultimo auto fue el: " + pila[tope - 1]
+"\n2. Ocupados: " + tope
+"\n3. Disponibles: " + (max - tope)
+"\n\nEste es el estado de la Cola de Espera:\n\n"
+"1. El ultimo auto fue el: " + cola[topeCola]
+"\n2. Ocupados: " + topeCola
+"\n3. Disponibles: " + (max - topeCola);
JTextArea areaSalida2 = new JTextArea();
areaSalida2.setText(salida);
JOptionPane.showMessageDialog(null, areaSalida2, "Estacionamiento", JOptionPane.INFORMATION_MESSAGE);
}
}
} while(opc!=0); // accion de la seleccion 0

}

public void manual() // subprograma para el boton manual
{
String manual = null; // variable de tipo caracter para introducir el contenido de texto del manual

JTextArea areaManual = new JTextArea(); // llama a un cuadro de texto para presentar el contenido de de la variable manual
areaManual.setText(manual); // guarda en el cuadro de texto el contenido de la varible manual
JOptionPane.showMessageDialog(null, areaManual, "Estacionamiento", JOptionPane.INFORMATION_MESSAGE); // muestra el cuadro de dialogo junto con el cuadro de texto del manual
}

// Clase interna privada para el manejo de eventos
private class ManejadorBoton implements ActionListener
{
public void actionPerformed(ActionEvent evento)
{
if (evento.getSource() == botonComenzar)
{
pilaColaIntroducir();
}
else if (evento.getSource() == botonManual)
{
manual();
}
else if (evento.getSource() == botonSalir)
System.exit(0);
} // Fin del método ActionPerformed
} // Fin de la clase interna privada ManejadorBoton
} // Fin de la clase Main