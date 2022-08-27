package br.ce.wcaquino.core;

import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.*;



public class BaseTest {
	
	@Rule
	public TestName nomeDoTeste = new TestName();
	
	
	@After
	public void finaliza() throws IOException {
		
		//TakeSdreenshot � uma bilbioteca do selenium que permite tirar prints ao final da execu��o dos testes
		TakesScreenshot screenshot = (TakesScreenshot)DriverFactory.getDriver();	
		File arquivo = screenshot.getScreenshotAs(OutputType.FILE);
		//O m�todo getMethodName retorna o nome do m�todo que executa o teste
		// File.separator (java.io) insere a barra conforme nomenclatura utilizada pelo S.O. da m�quina
		FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot" + File.separator+ 
				nomeDoTeste.getMethodName() + ".jpg"));
		
		if(Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
}
