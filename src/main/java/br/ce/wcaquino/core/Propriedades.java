package br.ce.wcaquino.core;

public class Propriedades {

	public static boolean FECHAR_BROWSER = false;//n�o fazer, pois n�o � seguro(utilizar somente na hora de desenvolvimento)
		//manter o outro jeito, na execu��o dos testes
	public static Browsers browser = Browsers.CHROME;
	
	public enum Browsers{
		CHROME,
		FIREFOX
	}

}
