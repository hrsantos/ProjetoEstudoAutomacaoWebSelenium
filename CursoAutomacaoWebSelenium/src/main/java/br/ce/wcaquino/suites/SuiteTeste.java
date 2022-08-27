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


// PARA A CRIAÇÃO DE SUITES DE TESTES É NECESSÁRIO CONFIGURAR A RUNNER COM O MÉTODO SUITE DO JUNIT  
@RunWith(Suite.class)

// A ANOTAÇÃO @SuiteClass  É NECESSÁRIA PARA INFORMARMOS QUAIS CLASSES DE TESTES FARÃO PARTE DA SUÍTE
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
