package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;


import br.ce.wcaquino.core.DSL;


public class ValidarRegrasdeNegocio {

	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void Inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void Finaliza() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void ValidarNome() {
		
		//driver.findElement(By.id("elementosForm:nome")).sendKeys(""); linguagem selenium
		//dsl.escreva("elementosForm:nome", ""); usando dsl
		page.setNome("");
		//driver.findElement(By.id("elementosForm:cadastrar")).click();
		page.setCadastrar("elementosForm:cadastrar");
		//Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoeAceita());
		//alerta.accept();
	}
	
	@Test
	public void ValidarSobrenome() {
		
		page.setNome("Teste");
		page.setSobrenome("");
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste");
		page.setCadastrar("elementosForm:cadastrar");
		//Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoeAceita());
		//alert.accept();
	}
	
	@Test
	public void ValidarRadio() {
		
		page.setNome("Teste");
		page.setSobrenome("hhw");
		getDriver().findElement(By.id("elementosForm:sexo:0"));
		page.setCadastrar("elementosForm:cadastrar");
		//Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoeAceita());
		//alert.accept();
	}
	
	@Test
	public void ValidarCheckbox() {
		
		
		page.setNome("Teste");
		page.setSobrenome("hhw");
		page.setMasculino();
		
		//driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		//dsl.clicarRadio("elementosForm:comidaFavorita:0");
		//dsl.clicarRadio("elementosForm:comidaFavorita:3");
		page.setCarne();
		page.setComidaVegetariano();
		page.setCadastrar("elementosForm:cadastrar");
	
		
		//Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoeAceita());
		//alert.accept();
	}
	
	@Test
	public void ValidarComboMultiplo() {
	
		
		page.setNome("Teste");
		page.setSobrenome("hhw");
		page.setMasculino();
		page.setCarne();
		
		//dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		page.setEsporte("Corrida", "O que eh esporte?");
		//dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		//driver.findElement(By.id("elementosForm:esportes"));
		//WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		//Select combo = new Select(element);
		//combo.selectByVisibleText("Corrida");
		//combo.selectByVisibleText("O que eh esporte?");
		page.setCadastrar("elementosForm:cadastrar");
		//Alert alert = driver.switchTo().alert();
		assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoeAceita());
		//alert.accept();
	}
}
