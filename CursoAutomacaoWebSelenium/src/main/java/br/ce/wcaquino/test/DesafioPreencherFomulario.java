package br.ce.wcaquino.test;

import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoDeTreinamentoPage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class DesafioPreencherFomulario extends BaseTest{
	
	private CampoDeTreinamentoPage page;
	
	@Before
	public void inicializa() {
	getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoDeTreinamentoPage();
	}
	
	@Test
	public void devePreencherFormulario() throws InterruptedException, IOException {
		inicializa();
		
		//PREENCHENDO OS CAMPOS DO FORMULÁRIO
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Haroldo");
		//dsl.escreve("elementosForm:nome", "Haroldo");
		page.setNome("Haroldo");
		//driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("R Santos");
		//dsl.escreve("elementosForm:sobrenome", "R Santos");
		page.setSobreNome("R Santos");
		//driver.findElement(By.id("elementosForm:sexo:0")).click();
		//dsl.clicarRadio("elementosForm:sexo:0");
		page.clicarRadioSexoMasculino();
		//driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
		page.clicarCheckBoxComidaCarne();
		//WebElement escolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		//Select opcoes = new Select((WebElement) escolaridade);
		//opcoes.selectByVisibleText("Superior");
		//dsl.selecionaComboBox("elementosForm:escolaridade", "Superior");
		page.clicarComboEscolaridadeSuperior();
		//WebElement esporte = driver.findElement(By.id("elementosForm:esportes"));
		//Select esportes = new Select(esporte);
		//esportes.selectByVisibleText("Karate");
		//dsl.selecionaComboBox("elementosForm:esportes", "Karate");
		page.clicarComboMultEsporteKarate();
		//CLICAR NO BOTAO CADASTRAR
		//driver.findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.clicarBotaoCadastrar();
		
		//VALIDAR DADOS CADASTRADOS
		//assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		//assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		//assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertEquals("Cadastrado!",page.obterResultadoCadastro());
		//assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Haroldo"));
		//assertTrue(dsl.obterTexto("descNome").endsWith("Haroldo"));
		//assertTrue(page.obterNomeCadastro().endsWith("Haroldo"));
		Assert.assertEquals("Haroldo",page.obterNomeCadastro());
		//assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("R Santos"));
		//assertTrue(dsl.obterTexto("descSobrenome").endsWith("R Santos"));
		//assertTrue(page.obterSobreNomeCadastro().endsWith("R Santos"));
		Assert.assertEquals("R Santos",page.obterSobreNomeCadastro());
		//assertTrue(driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
		//assertTrue(dsl.obterTexto("descSexo").endsWith("Masculino"));
		//assertTrue(page.obterSexoCadastro().endsWith("Masculino"));
		Assert.assertEquals("Masculino",page.obterSexoCadastro());
		//assertTrue(driver.findElement(By.id("descComida")).getText().endsWith("Carne"));
		//assertTrue(dsl.obterTexto("descComida").endsWith("Carne"));
		//assertTrue(page.obterComidaCadastro().endsWith("Carne"));
		Assert.assertEquals("Carne",page.obterComidaCadastro());
		//assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith("superior"));
		//assertTrue(dsl.obterTexto("descEscolaridade").endsWith("superior"));
		//assertTrue(page.obterEscolaridadeCadastro().endsWith("superior"));
		//assertTrue(driver.findElement(By.id("descEsportes")).getText().endsWith("Karate"));
		//assertTrue(dsl.obterTexto("descEsportes").endsWith("Karate"));
		//assertTrue(page.obterEsporteCadastro().endsWith("Karate"));
		Assert.assertEquals("Karate",page.obterEsporteCadastro());
		//Assert.assertEquals(driver.findElement(By.id("elementosForm:nome")).getText(), driver.findElement(By.xpath("//span[contains(.,'Haroldo')]")).getText());
		//Assert.assertEquals(driver.findElement(By.id("elementosForm:sobrenome")).getText(), driver.findElement(By.xpath("//span[contains(,.'R Santos!')]")).getText());
		//Assert.assertEquals("Masculino", driver.findElement(By.id("descSexo")).getText().endsWith("Masculino"));
		//Assert.assertEquals(driver.findElement(By.id("elementosForm:sobrenome")).getText(), driver.findElement(By.xpath("//span[text()='R Santos']")).getText());
		Thread.sleep(5000);
		
		finaliza();
	}

}
