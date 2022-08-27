package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.page.*;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteSincronismo {

	private DSL dsl;
	private CampoDeTreinamentoPage page;

	@Before
	public void inicializa() {
		getDriver().manage().window().maximize();
		// definindo um caminho para testes com diretório interno no projeto
		// possibilita a execução dos testes em qualquer máquina sem precisar alterar o
		// caminho dos arquivos
		// que serão testados
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoDeTreinamentoPage();
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void deveUtilizarEsperaFixa() throws Exception {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "Bazinga!!!");
	}

	@Test
	public void deveUtilizarEsperaImplicita() throws Exception {
		dsl.clicarBotao("buttonDelay");
		// Definição de espera implícita via driver
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.escreve("novoCampo", "Bazinga!!!");
		// Configuração na espera implícita para desativar a espera
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Test
	public void deveUtilizarEsperaExplicita() throws Exception {
		dsl.clicarBotao("buttonDelay");
		// Utiliza-se Webdriver para criá-la
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Bazinga!!!");
	}

}
