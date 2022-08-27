package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.page.CampoDeTreinamentoPage;
import br.ce.wcaquino.core.DSL;

public class TesteAjax {

	private DSL dsl; 
	private CampoDeTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().manage().window().maximize();
		//definindo um caminho para testes com diretório interno no projeto
		//possibilita a execução dos testes em qualquer máquina sem precisar alterar o caminho dos arquivos
		//que serão testados
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=74676");
		dsl = new DSL();
		page = new CampoDeTreinamentoPage();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void esperaExplicitaComAjax() throws InterruptedException {
		dsl.escreve("j_idt303:name", "Davi");
		dsl.clicarBotao("j_idt303:j_idt307");
		WebDriverWait wait = new WebDriverWait(getDriver(),10);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt303:display"), "Davi"));
		Assert.assertEquals("Davi", dsl.obterTexto("j_idt303:display"));
		Thread.sleep(5000);
	}
}
