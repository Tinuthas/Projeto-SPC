package br.com.fiap.spc.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import br.com.fiap.spc.arduino.ArduinoSerial;
import br.com.fiap.spc.beans.Acesso;
import br.com.fiap.spc.beans.Chat;
import br.com.fiap.spc.beans.Comunicado;
import br.com.fiap.spc.beans.Funcionario;
import br.com.fiap.spc.beans.Morador;
import br.com.fiap.spc.beans.Servico;
import br.com.fiap.spc.bo.AcessoBO;
import br.com.fiap.spc.bo.ComunicadoBO;
import br.com.fiap.spc.bo.ServicoBO;
import br.com.fiap.spc.excecoes.Excecoes;

@WebServlet(urlPatterns = "/chat")
public class MessageServlet extends HttpServlet{
	
	private static final long serialVersionUID = 6093948662123015033L;
	private Context context;
	private String entidade;
	private String valor;
	DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
	DateFormat formatBR = new SimpleDateFormat("dd-mm-yyyy");
	private Date data;
	private String dataNova;
	private String assunto;
	private String local;
	private String hora = null;
	
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String message = req.getParameter("question");
		if (message.isEmpty())  this.context = null;
		MessageResponse response = this.conversationAPI(message, context);
		Chat chat = new Chat();
		chat.addUserMessage(message);
		for(String text : response.getOutput().getText()) {
			chat.addBotMessage(text);
		}
				
//		System.out.println(response.getInput());
//		System.out.println(response.getInput().getText());
//		System.out.println(response.getInput().toString());
		
		
		
		context = response.getContext();
		resp.setContentType("application/json");
		
		try {
			JSONArray jsonArray = new JSONArray(response.getEntities());
			
			//Encomenda enco = new Encomenda();
			Servico serv = new Servico();
			Comunicado comu = new Comunicado();
			Morador m = new Morador();
			Funcionario f = new Funcionario();
			Acesso a = new Acesso();
			
			HttpSession session = req.getSession();	
			if(!(session.getAttribute("MORADOR") == null)) {
				m = (Morador) session.getAttribute("MORADOR");
				
			}else if(!(session.getAttribute("FUNCIONARIO") == null)) {
				f = (Funcionario) session.getAttribute("FUNCIONARIO");
			}
			
			
			
			if(!response.getEntities().isEmpty()) {
				entidade = jsonArray.getJSONObject(0).getString("entity");
				
				// LIBERAR ACESSO
				
				if(entidade.equals("confirmacao")) {
					valor = jsonArray.getJSONObject(0).getString("value");
					if((valor.equals("sim"))) {
						String evento = "l";
						ArduinoSerial arduino = new ArduinoSerial("COM3");
						
						arduino.initialize();
						
						System.out.println(evento);
						arduino.send(evento);	
					}
					if(!(session.getAttribute("MORADOR") == null)) {
						a.setUsuario(m);
					}else if(!(session.getAttribute("FUNCIONARIO") == null)) {
						a.setUsuario(f);
					}
					
					AcessoBO.acessoNovo(a);
					String mandar = "[\"Acessado!\"]";
					resp.getWriter().write(mandar);

					return;
					
				}
				
				// REGISTRAR ENCOMENDA	
					
					/**
					if(entidade.equals("tipoEncomenda")) {
						valor = jsonArray.getJSONObject(0).getString("value");
						System.out.println(valor);
						if(valor.equals("caixa")) {
							enco.setTipoEncomenda(1);
						} else if (valor.equals("carta")) {
							enco.setTipoEncomenda(2);
						} else if (valor.equals("sedex")){
							enco.setTipoEncomenda(3);
						}
						
					}
					
					if(entidade.equals("tamanho")) {
						valor = jsonArray.getJSONObject(0).getString("value");
						System.out.println(valor);
						enco.setTamanho(valor);
					}
					
					
					
					if(entidade.equals("morador")) {
						valor = jsonArray.getJSONObject(0).getString("value");
						System.out.println(valor);
						enco.setMorador(MoradorBO.buscarMorador(Integer.parseInt(valor)));
					}
					
					if(enco.getTipoEncomenda() != 0 && enco.getTamanho() != null && enco.getMorador().getCodigoUsuario() != 0) {
						enco.setFuncionario(FuncionarioBO.buscarFuncionario(f.getCodigoUsuario()));
						if(enco.getFuncionario().getCodigoUsuario() == 0) {
							String mandar = "[\"Você não tem permissão de funcionario!\"]";
							resp.getWriter().write(mandar);

							return;
						}
						
						String resposta = EncomendaBO.novoEncomenda(enco);
						String mandar = "["+resposta+"]";
						resp.getWriter().write(mandar);

						return;
					}	
				
					 **/
				// AGENDAR SERVIÇO
				if(entidade.equals("tipoServico")) {
					valor = jsonArray.getJSONObject(0).getString("value");
					if(valor.equals("churrasqueira")) {
						serv.setTipoServico(1);
					} else if (valor.equals("festa")) {
						serv.setTipoServico(2);
					} else if (valor.equals("jogos")){
						serv.setTipoServico(3);
					}else if (valor.equals("academia")){
						serv.setTipoServico(4);
					}else if (valor.equals("brinquedoteca")){
						serv.setTipoServico(5);
					}else if (valor.equals("sauna")){
						serv.setTipoServico(6);
					}
					
				}	
				

				
				if(entidade.equals("sys-date")) {
					data = formatUS.parse(jsonArray.getJSONObject(0).getString("value"));
					dataNova = formatBR.format(data);
					
				}
				
				if(entidade.equals("sys-time")) {
					hora = jsonArray.getJSONObject(0).getString("value");
					System.out.println(hora);
				}
				
				if(dataNova != null && hora != null) {
					serv.setInicio(dataNova +" "+ hora.substring(0, 5));
					serv.setMorador(m);
					if(m == null) {
						String mandar = "[\"Você não tem permissão de morador!\"]";
						resp.getWriter().write(mandar);

						return;
					}else {
						
						ServicoBO.novoServico(serv);
						String mandar = "[\"Serviço agendado!\"]";
						resp.getWriter().write(mandar);
						return ;
						
					}
				}
				

				
				
				// ESCREVER COMUNICADO
				if(entidade.equals("assunto")) {
					valor = jsonArray.getJSONObject(0).getString("value");

					assunto = valor;
					
				}	
				if(entidade.equals("local")) {
					valor = jsonArray.getJSONObject(0).getString("value");
					comu.setLocal(valor);
					local = valor;
					
					if(local != null && assunto != null)	{
						comu.setAssunto(assunto);
						comu.setLocal(local);
						comu.setDescricao(assunto + " no "+ local);
						local = null;
						assunto = null;
						if(session.getAttribute("MORADOR") != null) {
							comu.setPessoa(m);
						}else if(!(session.getAttribute("FUNCIONARIO") == null)) {
							comu.setPessoa(f);
						}
						
						if(comu.getPessoa() == null){
							String mandar = "[\"Você não tem permissão de morador ou funcionario!\"]";
							resp.getWriter().write(mandar);

							return;
						}else {

							ComunicadoBO.comunicadoNovo(comu);
							String mandar = "[\"Comunicado Enviado!\"]";
							resp.getWriter().write(mandar);
							return ;
						
						}
					}
						
				}
			}

				
						
				

				
			
		}catch (Exception e) {
			System.out.println(Excecoes.tratarExcecao(e));
		}
		
		
//		if(!response.getEntities().isEmpty()) {
//			
//			System.out.println(response.getEntities().size());
//			
//			System.out.println(response.getEntities().toString());
//			
//			System.out.println(response.getEntities().toString().length());
//			
//			System.out.println((response.getEntities()));
//
//			System.out.println(response.getEntities().iterator());
//
//			System.out.println(response.getEntities().get(0));
//			
//
//
//			
//			
////			HashMap<?, ?> entidade = (HashMap<?, ?>) response.getEntities();
//			
//			
//			
//		}
		
		
		
		
		resp.getWriter().write(new Gson().toJson(response.getOutput().getText()));
	}
	
	private MessageResponse conversationAPI(String input, Context context){
		Conversation service = new Conversation("2018-09-09"); // Data que o workspace foi criado
		service.setUsernameAndPassword("5d64f19d-19ce-4554-8cd8-c1a61dbaa215", "HztKV4cC88YX");
		InputData inputData = new InputData.Builder().text(input).build();
		MessageRequest newMessage = new MessageRequest();
		newMessage.setInput(inputData);
		String workspaceId = "59d23150-1116-4e75-8879-4c00d99e808b";
		MessageOptions options = new MessageOptions.Builder(workspaceId).input(inputData).context(context).build();
		MessageResponse response = service.message(options).execute();
		return response;
	} 

}








