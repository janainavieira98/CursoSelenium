package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;


public class TesteAjax {

	private DSL dsl;
	
	@Before
	public void Inicializa() {
		
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
		
	}
	@Test
	public void testAjax() {
		dsl.escreva("j_idt725:name", "Testando"); 
		dsl.clicarBotao("j_idt725:j_idt728");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		//wait.until(ExpectedConditions.textToBe(By.id("j_idt725:display"), "Testando"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt799_start")));//Interrompero fluxo e esperar até que o evento ocorra
		Assert.assertEquals("Testando", dsl.obterTexto("j_idt725:display"));
	}
}
