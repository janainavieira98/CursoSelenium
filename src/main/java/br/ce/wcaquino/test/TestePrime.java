package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.DSL;

public class TestePrime {

	
	private DSL dsl;

	@Before
	public void Inicializa() {
		dsl = new DSL();
		
	}
	
	@After
	public void Fecha() {
		//getDriver().quit();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarRadio(By.xpath("//input[@id='j_idt726:console:0']//../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt726:console:0"));
		dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt726:console:1"));
	}
	
	@Test
	public void deveInteragirComComboPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.selecionarComboPrime("j_idt726:console", "Xbox One");
		Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt726:console_label"));
		//dsl.clicarRadio(By.xpath("//*[@id='j_idt726:console_input']/../..//span"));
		//dsl.clicarRadio(By.xpath("//*[@id='j_idt726:console_items']//li[.='PS4']"));
		//Assert.assertEquals("PS4", dsl.obterTexto("j_idt726:console_input"));
		
	}
}
