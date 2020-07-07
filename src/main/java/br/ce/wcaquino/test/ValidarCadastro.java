package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;

public class ValidarCadastro extends BaseTest{//herdando a propriedade de finalizar o driver

	private CampoTreinamentoPage page;
	
	@Before
	public void Inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		//dsl = new DSL(driver);
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void Fecha() {
		DriverFactory.killDriver();
	}
	
	@Test
	public void Cadastro() {
		
		page.setNome("Janaina");
		//Input Nome
		//dsl.escreva("elementosForm:nome", "teste");
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("teste");
		
		//Input Sobrenome
		//driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("teste Sobrenome");
		//dsl.escreva("elementosForm:sobrenome", "teste Sobrenome");
		page.setSobrenome("Vieira");
		
		//Preencher Radio
		//driver.findElement(By.id("elementosForm:sexo:0")).click();
		//dsl.clicarRadio("elementosForm:sexo");
		page.setMasculino();
		
		//dsl.isRadioMarcado("elementosForm:sexo:0");
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		//Preencher Checkbox
		//driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//dsl.clicarRadio("elementosForm:comidaFavorita:0");
		page.setCarne();
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		//dsl.isRadioMarcado("elementosForm:comidaFavorita:0");
		
		//Preencher combo
		//driver.findElement(By.id("elementosForm:escolaridade"));
		//WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		//Select combo = new Select(element);
		//combo.selectByVisibleText("Mestrado");
		page.setEscolaridade("Mestrado");
		//dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		
		//Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());
		//Assert.assertEquals("Mestrado", dsl.obterValorCombo("elementosForm:escolaridade"));
		
		//Preencher Combo Multiplo
		//WebElement element2 = driver.findElement(By.id("elementosForm:esportes"));
		//dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		//dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		page.setEsporte("Natacao", "Corrida");
		
		//Select combo2 = new Select(element2);
		//combo2.selectByVisibleText("Natacao");
		//combo2.selectByVisibleText("Corrida");
		
		//List<WebElement> allSelectedOptions = combo2.getAllSelectedOptions();
		//dsl.obterValoresCombo("elementosForm:esportes");
		
		//Assert.assertEquals(2, dsl.obterQtdeOpcoesCombo("elementosForm:esportes"));
		
		//Clicar no botão cadastrar
		//driver.findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.setCadastrar("elementosForm:cadastrar");
		
		//Validar dados enviados
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Janaina", page.obterNomeCadastro());
		Assert.assertEquals("Vieira", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Carne", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao Corrida", page.obterEsportesCadastro());
		
	}
	
	
	
}
