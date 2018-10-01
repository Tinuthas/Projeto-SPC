package br.com.fiap.spc.beans;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// CLASSE DO ANDREY N�O � DO NOSSO PROJETO ESSENCIALMENTE
public class Chat {
	private static final List<String> userMessages = new ArrayList<>();
	private static final List<String> botMessages = new ArrayList<>();
	
	public void addUserMessage(String message) {
		userMessages.add(message);
	}
	
	public void addBotMessage(String message) {
		botMessages.add(message);
	}
	
	public Map<String, List<String>> getChat() {
		Map<String, List<String>> m = new LinkedHashMap<>();
		m.put("user", userMessages);
		m.put("bot", botMessages);
		return m;
	}
}
