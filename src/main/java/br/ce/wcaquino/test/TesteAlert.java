package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteAlert {

	
	private DSL dsl;
	
	@Before
	public void Inicializa() {
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void fecha() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void TestarAlert() {
		
		
		getDriver().findElement(By.id("alert")).click();
		Alert alert = getDriver().switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	public void TestarAlertCancelorSend() {
		
		
		
		getDriver().findElement(By.id("confirm")).click();
		Alert alerta = getDriver().switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.accept();
		Assert.assertEquals("Confirmado", alerta.getText());
		alerta.accept();

		
		getDriver().findElement(By.id("confirm")).click();
		alerta = getDriver().switchTo().alert();
		Assert.assertEquals("Confirm Simples", alerta.getText());
		alerta.dismiss();
		Assert.assertEquals("Negado", alerta.getText());
		alerta.dismiss();
	}
	
	@Test
	public void InteragirPrompt() {
		
		
		getDriver().findElement(By.id("prompt")).click();
		Alert alerta = getDriver().switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("2");
		alerta.accept();
		Assert.assertEquals("Era 2?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
	}
	
	@Test
	public void verificarInput() {
		dsl.escreva("elementosForm:nome", "Janaina");
		Assert.assertEquals("Janaina", dsl.obetrValor("elementosForm:nome"));
		dsl.escreva("elementosForm:nome", "Vieira");
		Assert.assertEquals("Vieira", dsl.obetrValor("elementosForm:nome"));
	}
}
