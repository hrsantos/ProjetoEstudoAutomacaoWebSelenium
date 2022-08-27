package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.awt.Frame;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.TagName;

import br.ce.wcaquino.page.*;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteFrame {
	
	DSL dsl;
	CampoDeTreinamentoPage page;
	
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
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void deveInteragirComFrames() throws InterruptedException {
		inicializa();
		/*
		 *  QUANDO O ELEMENTO SE ENCONTRA DENTRO DE UMA TAG <iframe> (frame), É NECESSÁRIO TROCAR O FOCO
		 *  PARA O FRAME CORRESPONDENTE USANDO O METODO switchTo DO SELENIUM 
		 **/
		
		//TROCAR O FOCO DO SELENIUM PARA O FRAME
		//getDriver().switchTo().frame("frame1");
		dsl.alterarFrame("frame1");
		
		//MAPEANDO O BOTÃO
		//getDriver().findElement(By.id("frameButton")).click();
		dsl.clicarBotao("frameButton");
		
		//TROCAR O FOCO DO SELENIUM PARA O ALERT
		//Alert alerta = getDriver().switchTo().alert();
		
		//ARMAZENAR O VALOR DO ALERT
		//String valorString = alerta.getText();
		String valorString = dsl.obterTextoAlert();
		
		//VALIDAR O VALOR OBITIDO COM O ESPERADO
		//Assert.assertEquals("Frame OK!", valorString);
		Assert.assertEquals("Frame OK!", valorString);
		
		//FECHAR O ALERT
		//alerta.accept();
		dsl.botaoAceptAlert();
		
		//VOLTAR PARA O FRAME PRINCIPAL
		//getDriver().switchTo().parentFrame();
		dsl.voltarFramePrincipal();
		
		//PREENCHER O CAMPO NOME
		//getDriver().findElement(By.id("elementosForm:nome")).sendKeys(valorString);
		dsl.escreve("elementosForm:nome", valorString);
		
		Thread.sleep(5000);
		finaliza();
	
	}	
	
	@Test
	public void deveInteragirComJanelaSimples() throws InterruptedException {
		inicializa();
		//CLICAR NO BOTAO PARA ABRIR A POPUP
		//getDriver().findElement(By.id("buttonPopUpEasy")).click();
		dsl.clicarBotao("buttonPopUpEasy");
		
		//ALTERAR O FOCO DO SELENIUM PARA O POPUP
		//getDriver().switchTo().window("Popup");
		dsl.alterarJanela("Popup");
		
		//ESCREVER O TEXTO NO ELELEMNTO TEXTAREA DO POPUP
		//getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		dsl.escreve(By.tagName("textarea"), "Deu certo?");
		
		//OBTER A INFORMAÇÃO INSERIDO NO TEXTAREA
		//String texto =  getDriver().findElement(By.tagName("textarea")).getText();
		String texto =  dsl.obterTexto(By.tagName("textarea"));
		
		Thread.sleep(5000);
		//ALTERAR O FOCO PARA A PAGINA NOVAMENTE
		//getDriver().switchTo().window("");
		dsl.voltarJanelaPrincipal();
	
		//ESCREVER O TEXTO OBTIDO NO TEXTAREA DO POPUP NO TEXTAREA DA PÁGINA
		//getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		dsl.escreve(By.tagName("textarea"), texto);
		
		//VALIDANDO O TEXTO OBITIDO COM O TEXTO ESPERADO
		//Assert.assertEquals(texto, getDriver().findElement(By.tagName("textarea")).getText());
		Assert.assertEquals(texto, dsl.obterTexto(By.tagName("textarea")));
		
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test
	public void deveInteragirComJanelaSemTitulo() throws InterruptedException {
		inicializa();
		//CLICAR NO BOTAO PARA ABRIR A POPUP
		//getDriver().findElement(By.id("buttonPopUpEasy")).click();
		dsl.clicarBotao("buttonPopUpEasy");
		
		/* O MÉTODO WindowHandle DO SELENIUM, PERMITE O GERENCAMENTO DE TODAS AS JANELAS ABERTAS DO NAVEGADOR
		 * ELE FORNECE UM IDENTIFICADOR ÚNICO PARA CADA JANELA
		 * PODE SER UTILIZADO PARA INTERAGIR COM POPUPS QUE NÃO POSSUAM IDENTIFICADORES
		 **/
		
		//ENCONTRANDO O IDENTIFICADOR DA JANELA PRINCIPAL
		System.out.println(getDriver().getWindowHandle());
		
		//ENCONTRANDO O IDENTIFICADOR DA POPUP
		System.out.println(getDriver().getWindowHandles());
		
		//ALTERANDO O FOCO PARA O POPUP - (String) é um "cast" (conversão de objeto para string conforme owindow solicita)
		//getDriver().switchTo().window((String)getDriver().getWindowHandles().toArray()[1]);
		dsl.alterarJanelaSemTitulo();
		
		//ESCREVENDO NA POPUP
		getDriver().findElement(By.tagName("textarea")).sendKeys("PopUp");
		dsl.escreve(By.tagName("textarea"), "PopUp");
		Thread.sleep(5000);
		
		//ALTERANDO O FOCO PARA O POPUP - (String) é um "cast" (conversão de objeto para string conforme owindow solicita)
		//getDriver().switchTo().window((String)getDriver().getWindowHandles().toArray()[0]);
		dsl.voltarJanelaSemTitulo();
				
		//ESCREVENDO NA POPUP
		getDriver().findElement(By.tagName("textarea")).sendKeys("JANELA PRINCIPAL");
		dsl.escreve(By.tagName("textarea"), "JANELA PRINCIPAL");
		
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test //UTILIZANDO JS PARA INTERAGIR COM ELEMENTO QUE NÃO É APRESENTADO NA TELA
	public void clicarNoBotaoEscondido() throws InterruptedException {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		//movendo o scroll para baixo
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		Thread.sleep(5000);
		dsl.alterarFrame("frame2");
		dsl.clicarBotao("frameButton");
		Thread.sleep(5000);
		Assert.assertEquals("Frame OK!", page.obterTextAlert());
		
	}
	
}
