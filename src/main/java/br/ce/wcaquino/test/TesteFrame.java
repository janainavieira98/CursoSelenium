package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.wcaquino.core.DSL;

public class TesteFrame {

	private DSL dsl;
	
	@Before
	public void Inicializa() {
	
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	

	@Test
	public void IntegrarComFrame() {
		
		
		getDriver().switchTo().frame("frame1");
		getDriver().findElement(By.id("frameButton")).click();
		Alert alerta = getDriver().switchTo().alert();
		String msg = alerta.getText();
		Assert.assertEquals("Frame OK!", msg);
		alerta.accept();
		getDriver().switchTo().defaultContent();
		getDriver().findElement(By.id("elementosForm:nome")).sendKeys(msg);
	}
	
	@Test
	public void InteragirComPopUp() {
		
		getDriver().findElement(By.id("buttonPopUpEasy")).click();
		getDriver().switchTo().window("Popup");
		getDriver().findElement(By.tagName("textarea")).sendKeys("teste");
		getDriver().close();//Fecha o PopUp
		getDriver().switchTo().window("");
		getDriver().findElement(By.tagName("textarea")).sendKeys("teste");
	}
	
	@Test
	public void InetragirPopUp() {
	
		getDriver().findElement(By.id("buttonPopUpHard")).click();
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);//No onclick do js não tem um titulo para o popup,
		//por isso foi utilizado esse getWindowHandles
		getDriver().findElement(By.tagName("textarea")).sendKeys("Foi");
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);
		getDriver().findElement(By.tagName("textarea")).sendKeys("te");
	}
	
	@Test
	public void InteragirComFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executorJS("windows.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoeAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
}
