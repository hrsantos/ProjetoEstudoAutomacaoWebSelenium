package br.ce.wcaquino.suites;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.test.DesafioPreencherFomulario;
import br.ce.wcaquino.test.DesafioTestarRegrasDeNegocio;
import br.ce.wcaquino.test.TesteCampoTreinamento;


// PARA A CRIA��O DE SUITES DE TESTES � NECESS�RIO CONFIGURAR A RUNNER COM O M�TODO SUITE DO JUNIT  
@RunWith(Suite.class)

// A ANOTA��O @SuiteClass  � NECESS�RIA PARA INFORMARMOS QUAIS CLASSES DE TESTES FAR�O PARTE DA SU�TE
@SuiteClasses({
	DesafioPreencherFomulario.class, 
	DesafioTestarRegrasDeNegocio.class, 
	//TesteCampoTreinamento.class
})
public class SuiteTeste {
	
	@AfterClass
	public static void finalizaTudo() {
		killDriver();
	}

}
