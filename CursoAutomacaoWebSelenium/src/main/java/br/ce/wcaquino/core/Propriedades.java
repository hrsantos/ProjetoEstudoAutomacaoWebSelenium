package br.ce.wcaquino.core;

public class Propriedades {
	
	//Quando essa propriedade estiver como false, ela roda todos os testes em sequência no mesmo browser.
	public static boolean FECHAR_BROWSER = false;
	
	public static Browsers browser = Browsers.FIREFOX;
			
	public enum Browsers{
		CHROME,
		FIREFOX
	}

}
