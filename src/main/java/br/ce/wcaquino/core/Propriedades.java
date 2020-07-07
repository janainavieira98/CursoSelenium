package br.ce.wcaquino.core;

public class Propriedades {

	public static boolean FECHAR_BROWSER = false;//não fazer, pois não é seguro(utilizar somente na hora de desenvolvimento)
		//manter o outro jeito, na execução dos testes
	public static Browsers browser = Browsers.CHROME;
	
	public enum Browsers{
		CHROME,
		FIREFOX
	}

}
