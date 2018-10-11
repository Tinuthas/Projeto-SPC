package br.com.fiap.spc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.spc.arduino.ArduinoSerial;
import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns = "/login")
public class ServletLiberar extends HttpServlet {

	private static final long serialVersionUID = -917021736380285904L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String evento = "l";
			ArduinoSerial arduino = new ArduinoSerial("COM1");

			arduino.initialize();

			evento.toLowerCase();
			evento.charAt(0);

			arduino.send(evento);

		} catch (Exception e) {
			System.out.println(Excecoes.tratarExcecao(e));
		}

	}

}
