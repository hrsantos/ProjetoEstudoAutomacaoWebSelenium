package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;


public class TesteAlert {
	
	
	DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().manage().window().maximize();
		//definindo um caminho para testes com diretório interno no projeto
		//possibilita a execução dos testes em qualquer máquina sem precisar alterar o caminho dos arquivos
		//que serão testados
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test  // TESTE MAPEAMENTO E INTERAÇÃO COM ALERTS
	public void deveInteragirComAlertSimples() throws InterruptedException {
		inicializa();
		
		//getDriver().findElement(By.id("alert")).click();
		dsl.clicarBotao("alert");
		
		//ALTERA O FOCO DO SELENIU PARA O ALERT
		//Alert alert = getDriver().switchTo().alert();
		//dsl.alterarFocoAlert();
		
		//VALITANDO A MENSAGEM APRESENTADA NO ALERT
		//String texto = alert.getText();
		//Assert.assertEquals("Alert Simples", texto);
		String texto = dsl.obterTextoAlert();
		Assert.assertEquals("Alert Simples", texto);
		//alert.accept();
		dsl.botaoAceptAlert();
		
		//INSERIR O TEXTO DO ALERT NO CAMPO NOME DO FORMULÁRIO
		//getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto);
		dsl.escreve("elementosForm:nome", texto);
		
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test  // TESTE MAPEAMENTO E INTERAÇÃO COM ALERTS
	public void deveInteragirComAlertConfirmAcept() throws InterruptedException {
		inicializa();
		//getDriver().findElement(By.id("confirm")).click();
		dsl.clicarBotao("confirm");
		
		//ALTERA O FOCO DO SELENIU PARA O ALERT
		//Alert alert = getDriver().switchTo().alert();
		
		//VALIDANDO A MENSAGEM APRESENTADA NO ALERT
		//Assert.assertEquals("Confirm Simples", alert.getText());
		Assert.assertEquals("Confirm Simples", dsl.obterTextoAlert());
		
		//alert.accept();
		dsl.botaoAceptAlert();
		
		//Assert.assertEquals("Confirmado", alert.getText());
		Assert.assertEquals("Confirmado", dsl.obterTextoAlert());
		
		//FECHA O ALERT
		//alert.accept();
		dsl.botaoAceptAlert();
		
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test  // TESTE MAPEAMENTO E INTERAÇÃO COM ALERTS
	public void deveInteragirComAlertConfirmDimissed() throws InterruptedException {
		inicializa();
		
		//getDriver().findElement(By.id("confirm")).click();
		dsl.clicarBotao("confirm");
		
		//ALTERA O FOCO DO SELENIU PARA O ALERT
		//Alert alert = getDriver().switchTo().alert();
		
		//VALIDANDO A MENSAGEM APRESENTADA NO ALERT
		//Assert.assertEquals("Confirm Simples", alert.getText());
		Assert.assertEquals("Confirm Simples", dsl.obterTextoAlert());
		
		//alert.dismiss();
		dsl.botaoDismissAlert();
		
		//Assert.assertEquals("Negado", alert.getText());
		Assert.assertEquals("Negado", dsl.obterTextoAlert());
		
		//FECHA O ALERT
		//alert.accept();
		dsl.botaoAceptAlert();
		
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test  // TESTE MAPEAMENTO E INTERAÇÃO COM ALERTS
	public void deveInteragirComAlertPromptOpcaoOk() throws InterruptedException {
		inicializa();
		
		//getDriver().findElement(By.id("prompt")).click();
		dsl.clicarBotao("prompt");
		
		//ALTERA O FOCO DO SELENIU PARA O ALERT
		//Alert alert = getDriver().switchTo().alert();
		
		//VALIDANDO A MENSAGEM APRESENTADA NO ALERT
		//Assert.assertEquals("Digite um numero", alert.getText());
		Assert.assertEquals("Digite um numero", dsl.obterTextoAlert());
		
		//alert.sendKeys("2");
		dsl.escreverAlert("2");
		
		//CLICA EM OK
		//alert.accept();
		dsl.botaoAceptAlert();
		
		//VALIDANDO A MENSAGEM APRESENTADA NO ALERT
		//Assert.assertEquals("Era 2?", alert.getText());
		Assert.assertEquals("Era 2?", dsl.obterTextoAlert());
		
		//CLICA EM OK
		//alert.accept();
		dsl.botaoAceptAlert();
		
		//VALIDANDO A MENSAGEM APRESENTADA NO ALERT
		//Assert.assertEquals(":D", alert.getText());
		Assert.assertEquals(":D", dsl.obterTextoAlert());
		
		//FECHA O ALERT
		//alert.accept();
		dsl.botaoAceptAlert();
		
		Thread.sleep(5000);
		finaliza();
	}
}
