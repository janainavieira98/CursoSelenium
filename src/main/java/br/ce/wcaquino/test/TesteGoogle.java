package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TesteGoogle {

	@Test
	public void Teste() {
		
		//System.setProperty("webdriver.IE.driver", "C:\\IEdriverserver.exe")
		//System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new ChromeDriver();
		//WebDriver driver = new InternetExplorerDriver();
		//driver.manage().window().setSize(new Dimension(1200, 765));Nesta linha é definida o tamanho da tela 
		//driver.manage().window().maximize(); maximizar tela
		driver.get("https://www.google.com/");
		Assert.assertEquals("Google", driver.getTitle());
		//driver.close();
		driver.quit();
	}
}
