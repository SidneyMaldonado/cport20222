import java.util.List;

public class AnalisadorSintatico {

	public AnalisadorSintatico() {
	
	}

	public Boolean validar( 
			List<String> exemplo, 
			List<String> gramatica) {
		Boolean valido = true;
		int numeroLinha = 0;
		for( String linha: exemplo) {
			//System.out.println("Analisando linha" + numeroLinha + ": "+linha);
			if (validarLinha( linha, gramatica)) {
				valido = true;
			} else {
			    valido = false;
			}
			if (!valido) {
				System.out.println("Erro de syntax na linha " + numeroLinha);
				return false;
			}
			numeroLinha++;
		}
		return true;
	}
	
	public Boolean validarLinha(String linha, List<String> gramatica) {
		for(String regra: gramatica) {
			if (linha.matches(regra)){
				return true;
			}
		}
		return false;
	}
}
