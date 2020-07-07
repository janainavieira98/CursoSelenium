package br.ce.wcaquino.suite;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.test.TesteRagrasCadastro;
import br.ce.wcaquino.test.ValidarCadastro;


@RunWith(Suite.class)
@SuiteClasses({
	TesteRagrasCadastro.class,
	ValidarCadastro.class,
	//ValidarRegraDeNegocio.class
})
public class SuiteTeste {

	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
}
