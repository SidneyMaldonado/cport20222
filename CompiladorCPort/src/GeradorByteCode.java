import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class GeradorByteCode {
	
	public List<String> byteCode;
	public List<String> fonte;
	public Boolean jaExisteScanner;
	public GeradorByteCode(List<String> fonte) {
		this.fonte = fonte;
	}
	public Boolean gerar(String nomeArquivo) {
		String nomeClasse;
		System.out.println(nomeArquivo);
		nomeClasse = limparNome(nomeArquivo);
		jaExisteScanner = false;
		byteCode = new ArrayList<>();
		for(String linha: fonte ) {
			//System.out.println(linha);
			gerarInicio(linha, nomeClasse);
			gerarFim(linha);
			gerarInteiro(linha);
			gerarLeia(linha);
			gerarOperacaoAritmetica(linha);
			gerarEscreva(linha);
			gerarSe(linha);
			gerarSenao(linha);
			gerarFimSe(linha);
			gerarConte(linha);
			gerarFimConte(linha);
			gerarPulaLinha(linha);

		}
		for(String linha: byteCode) {
			System.out.println(linha);
		}
		gravar(nomeArquivo, byteCode);
		return true;
	}
	public void gerarInicio(String linha,
			String nome) {
		if (!linha.equals("inicio"))
			return;
byteCode.add("import java.util.Scanner;");
byteCode.add("public class "+nome+" {");
byteCode.add("public static void main(String[] args){");

	}
	public void gerarFim(String linha) {
		if (!linha.equals("fim"))
			return;
		byteCode.add("}");
		byteCode.add("}");
	}
	public void gerarInteiro(String linha) {
		if (!linha.startsWith("inteiro"))
			return;
	byteCode.add(linha.replace("inteiro","int")+";");
	}
	public void gerarLeia(String linha) {
		if(!linha.startsWith("leia"))
			return;
	if(!jaExisteScanner) {
	byteCode.add("Scanner teclado = new Scanner(System.in);");
	jaExisteScanner=true;
	}
	byteCode.add(linha.replace("leia","")+
			"= teclado.nextInt();");	
	}
	public void gerarOperacaoAritmetica(String linha) {
		if (linha.contains("+") ||
			linha.contains("-") ||
			linha.contains("*") ||
			linha.contains("/")) {
			byteCode.add(linha+";");
		}
	}
	public void gerarEscreva(String linha) {
		if (!linha.startsWith("escreva")) 
		   return;
		linha = linha.replace("'","\"");
		byteCode.add(
				linha.replace("escreva",
		"System.out.print(")+");"
				);
	}
	public void gerarSe(String linha) {
		if (linha.startsWith("senao"))
			return;
		if (!linha.startsWith("se"))
			return;
		linha = linha.replace("se","if (");
		linha = linha.replace("entao",") {");
		byteCode.add(linha);
	}
	public void gerarSenao(String linha) {
		if (!linha.startsWith("senao"))
			return;
		linha = linha.replace("senao","} else {");
		byteCode.add(linha);
	}
	public void gerarFimSe(String linha) {
		if (!linha.startsWith("fimse"))
			return;
		
		linha = linha.replace("fimse", "}");
		byteCode.add(linha);
	}
	public void gerarConte(String linha) {
		if (!linha.startsWith("conte"))
			return;
		String[] token = linha.split(" ");
		
		String novaLinha = "for(" + token[5] +
				"="+token[1]+";"+token[5] + "<="+
				token[3] + ";" + token[5] + "++" +
				"){";
		
		byteCode.add(novaLinha);
		
	}
	public void gerarFimConte(String linha) {
		if (!linha.startsWith("fimconte"))
			return;
		linha = linha.replace("fimconte","}");
		byteCode.add(linha);
	}
    public void gerarPulaLinha(String linha) {
    	if (!linha.startsWith("pulalinha"))
    		return;
    	linha = linha.replace("pulalinha",
    			"System.out.println();");
    	byteCode.add(linha);
    }
    public String limparNome(String nome) {
    	nome = nome.replace("\\"," ");
    	nome = nome.replace(".txt","");
    	String[] token = nome.split(" ");
    	String novoNome = token[token.length-1];
    	novoNome = novoNome.substring(0,1).toUpperCase()
    			+novoNome.substring(1).toLowerCase();
      //  novoNome.substring(1, novoNome.length()-1);
    	System.out.println(novoNome);
    	return novoNome;
    }
    
    public void gravar(String nome, 
    		List<String> byteCode) {
    	LeitorArquivo gravador= 
    			new LeitorArquivo();
    	gravador.gravarArquivo(nome, byteCode);
    }
    
}
