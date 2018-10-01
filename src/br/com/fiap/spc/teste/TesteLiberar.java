package br.com.fiap.spc.teste;

import javax.swing.JOptionPane;

import br.com.fiap.spc.arduino.ArduinoSerial;

public class TesteLiberar {

	public static void main(String[] args) {
		
		
		//String evento = "l";
		
		String evento = JOptionPane.showInputDialog("Digite \"l\"");
		
		
		ArduinoSerial arduino = new ArduinoSerial("COM5");
		
		
		arduino.initialize();
							
		arduino.send(evento);
				
		
		
		
//		arduino.initialize();
//		
//		evento.toLowerCase();
//		evento.charAt(0);
//		
//		arduino.send(evento);	

	}

}
