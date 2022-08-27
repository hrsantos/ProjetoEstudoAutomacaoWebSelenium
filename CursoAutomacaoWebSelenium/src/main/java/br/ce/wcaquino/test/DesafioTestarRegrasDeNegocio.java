package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.support.ui.Select;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoDeTreinamentoPage;

public class DesafioTestarRegrasDeNegocio extends BaseTest{
	
	private DSL dsl;
	private CampoDeTreinamentoPage page;
	
	@Before
	public void inicializa() {
		getDriver().manage().window().maximize();
		//definindo um caminho para testes com diretório interno no projeto
		//possibilita a execução dos testes em qualquer máquina sem precisar alterar o caminho dos arquivos
		//que serão testados
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoDeTreinamentoPage();
	}
	
	@Test
	public void testarPreenchimentoObrigatorioCampoNome() throws InterruptedException, IOException {
		inicializa();
		
		//getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		dsl.clicarBotao("elementosForm:cadastrar");
		
		getDriver().switchTo().alert();
		Alert alert = getDriver().switchTo().alert();
		
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		Thread.sleep(5000);
		
		//alert.accept();
		dsl.botaoAceptAlert();
		
		finaliza();
	}
	
	@Test
	public void testarPreenchimentoObrigatorioCampoSobreNome() throws InterruptedException, IOException {
		inicializa();
		
		//getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Haroldo");
		//dsl.escreve("elementosForm:nome", "Haroldo");
		page.setNome("Haroldo");
		
		//getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.clicarBotaoCadastrar();
		
		getDriver().switchTo().alert();
		Alert alert = getDriver().switchTo().alert();
		
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		Thread.sleep(5000);
		
		//alert.accept();
		dsl.botaoAceptAlert();
		
		finaliza();
	}

	@Test
	public void testarPreenchimentoObrigatorioCampoSexo() throws InterruptedException, IOException {
		inicializa();
		
		//getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Haroldo");
		//dsl.escreve("elementosForm:nome", "Haroldo");
		page.setNome("Haroldo");
		//getDriver().findElement(By.id("elementosForm:sobrenome")).sendKeys("R Santos");
		//dsl.escreve("elementosForm:sobrenome", "R Santos");
		page.setSobreNome("R Santos");
		//getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.clicarBotaoCadastrar();
		
		getDriver().switchTo().alert();
		Alert alert = getDriver().switchTo().alert();
		
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		Thread.sleep(5000);
		
		//alert.accept();
		dsl.botaoAceptAlert();
		
		finaliza();
	}
	
	@Test
	public void testarSeEVegetariano() throws InterruptedException, IOException {
		inicializa();
		
		//getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Haroldo");
		//dsl.escreve("elementosForm:nome", "Haroldo");
		page.setNome("Haroldo");
		//getDriver().findElement(By.id("elementosForm:sobrenome")).sendKeys("R Santos");
		//dsl.escreve("elementosForm:sobrenome", "R Santos");
		page.setSobreNome("R Santos");
		//getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		//dsl.clicarRadio("elementosForm:sexo:0");
		page.clicarRadioSexoMasculino();
		//getDriver().findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
		page.clicarCheckBoxComidaCarne();
		//getDriver().findElement(By.id("elementosForm:comidaFavorita:3")).click();
		//dsl.clicarCheckBox("elementosForm:comidaFavorita:3");
		page.clicarCheckBoxComidaVegetariano();
		
		//getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.clicarBotaoCadastrar();
		
		getDriver().switchTo().alert();
		Alert alert = getDriver().switchTo().alert();
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		Thread.sleep(5000);
		
		//alert.accept();
		dsl.botaoAceptAlert();
		
		finaliza();
	}
	
	@Test
	public void testarEsportistaIndeciso() throws InterruptedException, IOException {
		inicializa();
		
		//getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Haroldo");
		//dsl.escreve("elementosForm:nome", "Haroldo");
		page.setNome("Haroldo");
		//getDriver().findElement(By.id("elementosForm:sobrenome")).sendKeys("R Santos");
		//dsl.escreve("elementosForm:sobrenome", "R Santos");
		page.setSobreNome("R Santos");
		//getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		//dsl.clicarRadio("elementosForm:sexo:0");
		page.clicarRadioSexoMasculino();
		
		//Select esportes = new Select(getDriver().findElement(By.id("elementosForm:esportes")));
		//esportes.selectByVisibleText("Natacao");
		//esportes.selectByVisibleText("O que eh esporte?");
		//dsl.selecionaComboBox("elementosForm:esportes", "Natacao");
		//dsl.selecionaComboBox("elementosForm:esportes", "O que eh esporte?");
		page.selecionaComboMultEsportes("Natacao","O que eh esporte?");
		
		//getDriver().findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.clicarBotaoCadastrar();
		
		getDriver().switchTo().alert();
		Alert alert = getDriver().switchTo().alert();
		
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		Thread.sleep(5000);
		
		//alert.accept();
		dsl.botaoAceptAlert();
		
		finaliza();
	}
}
