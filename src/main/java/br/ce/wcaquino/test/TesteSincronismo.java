package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;


public class TesteSincronismo {


	private DSL dsl;
	
	@Before
	public void Inicializa() {
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@Test
	public void devUtilizarEsperaFixa() throws InterruptedException {//Considera o teste um erro e não uma falha, o junit faz o gerenciamento
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escreva("novoCampo", "Teste");
	}
	@Test
	public void devUtilizarEsperaImplicita() throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicarBotao("buttonDelay");
		dsl.escreva("novoCampo", "Teste");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);//Zera para não ser executado no restante do script
	}
	@Test
	public void devUtilizarEsperaExplicita() throws InterruptedException {
		//é recomendado usar essa, pois o teste fica mais rápido, tem-se o controle
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);//Dá controle
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreva("novoCampo", "Teste");
		
	}
}
