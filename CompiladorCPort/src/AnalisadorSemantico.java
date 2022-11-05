import java.util.ArrayList;
import java.util.List;

public class AnalisadorSemantico {

	public List<String> tabelaDeSimbolos;
	
	public AnalisadorSemantico() {
		tabelaDeSimbolos = new ArrayList();
	}
	
	public Boolean validar( List<String> exemplo, 
			List<String> dicionario) {
		for( String linha: exemplo) {
			if (!validarLinha(linha, dicionario)) {
				return false;
			}
		}
		return true;
	}
	public Boolean validarLinha(String linha,
			List<String> dicionario) {
		
		String[] tokens= linha.split(" ");
		for( String token: tokens) {
	if (token.equals("inteiro")) {
		adicionarTabelaSimbolos(tokens[1]);
	}
	else if (token.startsWith("\"")){return true;}
	else if (token.startsWith("'")){return true;}	
	else {
	if (!estaNoDicionario(token, dicionario)) {
	if (token.matches("^[a-z][0-9a-z]{0,30}$")) {
	if (!estaNaTabelaDeSimbolos(token)) {
	System.out.println("Variavel não declarada:" + token );
				}
			}
		}
	}
	
		}
		return true;
	}
	
	public Boolean estaNoDicionario(String token, 
			List<String> dicionario) {
		return dicionario.contains(token);
	}
	public void adicionarTabelaSimbolos(String token) {
		this.tabelaDeSimbolos.add(token);
	}
	public Boolean estaNaTabelaDeSimbolos(String token) {
		return tabelaDeSimbolos.contains(token);
	}

}
