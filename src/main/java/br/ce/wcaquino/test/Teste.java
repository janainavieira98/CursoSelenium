package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class Teste {

	private WebDriver driver;
	
	private DSL dsl;
	
	@Before
	public void Inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void Fecha() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void testeTextField() {
		dsl.escreva("elementosForm:nome", "Teste de input");
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de input");
		//Assert.assertEquals("Teste de input", driver.findElement(By.id("elementosForm:nome")).getAttribute("value")); usei a dsl
		Assert.assertEquals("Teste de input", dsl.obetrValor("elementosForm:nome"));
		
	}
	
	@Test
	public void testeTextArea() {
		
		getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
		Assert.assertEquals("teste", getDriver().findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		//driver.quit();
		
	}
	
	@Test
	public void deveInteragirComRadio() {
		
		dsl.clicarRadio("elementosForm:sexo:0");
		//driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
	}
	
	@Test
	public void deveInteragirComCheckbox() {
		
		getDriver().findElement(By.id("elementosForm:comidaFavorita:1")).click();
		Assert.assertTrue(getDriver().findElement(By.id("elementosForm:comidaFavorita:1")).isSelected());
	}
	
	@Test
	public void deveInteragirComCombo() {
		
		
		//driver.findElement(By.id("elementosForm:escolaridade"));
		//WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		//Select combo = new Select(element);
		//combo.selectByIndex(1);
		//combo.selectByValue("superior");
		//combo.selectByVisibleText("2o grau incompleto");
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau incompleto");
		Assert.assertEquals("2o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void verificarValoresCombo() {
		
		getDriver().findElement(By.id("elementosForm:escolaridade"));
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option:options) {
			if (option.getText().equals("Superior")) { //Para verificar se a lógica está realmente correta, teste um valor falso
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "Karate");
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		//combo.selectByVisibleText("Natacao");
		//combo.selectByVisibleText("Corrida");
		//combo.selectByVisibleText("Karate");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
		
	}
	
	@Test
	public void cliqueBotao() {

		//driver.findElement(By.id("buttonSimple")).click();
		WebElement botao = getDriver().findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	
	@Test
	public void verificarLink() {
		
		dsl.clicarLink("Voltar");
		//driver.findElement(By.linkText("Voltar")).click();
		//Assert.fail(); quando o teste estiver incompleto
		Assert.assertEquals("Voltou!", dsl.obterTexto("Resultado"));
		//Assert.assertEquals("Voltou!", driver.findElement(By.id("Resultado")).getText());
	}
	
	@Test
	public void verificarTexto() {

		//System.out.println(driver.findElement(By.tagName("body")).getText()); Mostra todos os textos no console do eclipse presente na tela
		//Assert.assertTrue(driver.findElement(By.tagName("Body"))
		//		.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento",dsl.obterTexto(By.tagName("h3")));
		//Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText()); fiz usando dsl
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
		//Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText()); fiz usando dsl
	}
	
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('Testando Js via Selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via JS' ");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio' ");
		
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = 'arguments[1]'", element, "solid 4 px red");
	}

	@Test
	public void deveClicarBotaoTabela() {
		dsl.ClicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
}
