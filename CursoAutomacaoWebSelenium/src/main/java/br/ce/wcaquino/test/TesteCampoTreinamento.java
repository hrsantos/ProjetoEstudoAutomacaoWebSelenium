package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import br.ce.wcaquino.page.CampoDeTreinamentoPage;
import static br.ce.wcaquino.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.FinalizablePhantomReference;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteCampoTreinamento {
	
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
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void TesteTextField() throws InterruptedException {
		inicializa();
		
		//mapeando elementos via Selenium e passando valores de entrada
		//getDriver().findElement(By.id("elementosForm:nome")).sendKeys("Haroldo");
		
		//UTLIZANDO DSL
		//dsl.escreve("elementosForm:nome", "Haroldo");
		page.setNome("Haroldo");
		
		//Usando o Assert do JUnit e mapeamento via id para validar o preenchimento do elemento
		//Assert.assertEquals("Haroldo", getDriver().findElement(By.id("elementosForm:nome")).getAttribute("value"));
		Assert.assertEquals("Haroldo", dsl.obterValorCampo("elementosForm:nome"));
		
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test
	public void deveInteragirComTextArea() throws InterruptedException {
		inicializa();
		
		dsl.escreve("elementosForm:sugestoes", "Teste preenchimento TextArea");
		
		//getDriver().findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste preenchimento Text Área");
		Assert.assertEquals("Teste preenchimento TextArea", dsl.obterValorCampo("elementosForm:sugestoes"));
		
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test
	public void deveInteragirComRadioECheck() throws InterruptedException {
		inicializa();
		
		Thread.sleep(5000);
		//getDriver().findElement(By.id("elementosForm:sexo:0")).click();
		dsl.clicarRadio("elementosForm:sexo:0");
		//getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).click();
		dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
		//Assert.assertTrue(getDriver().findElement(By.id("elementosForm:sexo:0")).isSelected());
		Assert.assertTrue(dsl.radioSelecionado("elementosForm:sexo:0"));
		//Assert.assertTrue(getDriver().findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		Assert.assertTrue(dsl.checkBoxSelecionado("elementosForm:comidaFavorita:2"));
		Thread.sleep(5000);
		finaliza();
	}

	// ==============  UTILIZANDO O MÉTODO SELECT PARA MAPEAR ELEMENTOS NO SELENIUM ===============
	@Test
	public void deveInteragirComComboBox() throws InterruptedException {
		inicializa();
		
		Thread.sleep(5000);
		// MÉTODO ARCAICO SEM SELECT
			//getDriver().findElement(By.id("elementosForm:escolaridade")).click();
			//getDriver().findElement(By.xpath("//option[@value='2graucomp']")).click();
			//Assert.assertEquals("2o grau completo", getDriver().findElement(By.xpath("//option[@value='2graucomp']")).getText());
		
		// MÉTODOS COM O SELECT DO SELENIUM
			//WebElement elemento = getDriver().findElement(By.id("elementosForm:escolaridade"));
			//Select comboBox = new Select(elemento);
			//comboBox.selectByIndex(2);
			//Thread.sleep(5000);
			//comboBox.selectByValue("2graucomp");
			//Thread.sleep(5000);
			//comboBox.selectByVisibleText("Superior");
		
		dsl.selecionaComboBox("elementosForm:escolaridade", "Superior");
		
		//ASSERT COM SELECT VERIFICANDO O ELEMENTO SELECIONADO NO COMBOBOX
		//Assert.assertEquals("Superior", comboBox.getFirstSelectedOption().getText());
		Assert.assertEquals("Superior",dsl.obterValorCombo("elementosForm:escolaridade"));
		
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test
	public void deveVerificarValoresCombobBox() throws InterruptedException {
		inicializa();
		
		// MÉTODOS COM O SELECT DO SELENIUM
		//WebElement elemento = getDriver().findElement(By.id("elementosForm:escolaridade"));
		//Select comboBox = new Select(elemento);
		
		//COLETANDO TODOS OS ELELEMENTOS CONTIDOS NO COMBOBOX E PASSANDO PARA UMA LISTA
		//List<WebElement> opcoes = comboBox.getOptions();
		
		//COMPARANDO A QUANTIDADE DE ELEMENTOS
		//Assert.assertEquals(8, opcoes.size());
		Assert.assertEquals(8, dsl.obterQuantidade("elementosForm:escolaridade"));
		
		//VALIDANDO SE O ELEMENTO BUSCADO ESTÁ NA LISTA DE OPÇÕES DO COMBOBOX
		/*boolean existe = false;
		for (WebElement lista : opcoes) {
			if (lista.getText().equals("Superior")) {
				existe = true;
				break;
			}
		}*/
		//Assert.assertTrue(existe);
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade"));
		
		Thread.sleep(5000);
		finaliza();
		
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() throws InterruptedException {
		inicializa();
		
		// MÉTODOS COM O SELECT DO SELENIUM
		WebElement elemento = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(elemento);
		
		// SELECIONANDO AS OPÇÕES DA LISTA
		//combo.selectByVisibleText("Natacao");
		//combo.selectByVisibleText("Corrida");
		//combo.selectByVisibleText("O que eh esporte?");
		
		combo.getAllSelectedOptions();
		
		//UTILIZANDO DSL PARA SELECIONAR AS OPÇÕES
		dsl.selecionaComboBox("elementosForm:esportes", "Natacao");
		dsl.selecionaComboBox("elementosForm:esportes", "Corrida");
		dsl.selecionaComboBox("elementosForm:esportes", "O que eh esporte?");
		
		Assert.assertEquals(3, dsl.obterValoresCombo("elementosForm:esportes"));
		
		dsl.deselecionaComboBox("elementosForm:esportes", "Corrida");
		//COLETANDO TODOS OS ELELEMENTOS CONTIDOS NO COMBOBOX E PASSANDO PARA UMA LISTA
		//List<WebElement> opcoes = combo.getAllSelectedOptions();
	
		//VALIDANDO A SELEÇÃO DOS ELEMENTOS PELA QUANTIDADE SELECIONADA
		//Assert.assertEquals(3, combo.getAllSelectedOptions().size());
		Assert.assertEquals(2, dsl.obterValoresCombo("elementosForm:esportes"));
		List<String> opcoesMarcadas = dsl.verificarOpcoesCombo("elementosForm:esportes");
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao","O que eh esporte?")));
		
		Thread.sleep(5000);
		finaliza();
	}
	
	// ================================================================================================

	@Test  // TESTE CLIQUE NO BOTÃO
	public void deveClicarNoBotao() throws InterruptedException {
		inicializa();
		
		//CLICANDO NO BOTÃO
		//getDriver().findElement(By.id("buttonSimple")).click();
		dsl.clicarBotao("buttonSimple");
		
		//VALIDANDO A MUDANÇA DO TEXTO DO BOTÃO CHECANDO O ATRIBUTO VALUE DO ELEMENTO
		//Assert.assertEquals("Obrigado!", getDriver().findElement(By.id("buttonSimple")).getAttribute("value"));
		Assert.assertEquals("Obrigado!", dsl.obterOValorDoElemento("buttonSimple", "value"));
		
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test  // TESTE CLIQUE NO LINK
	public void deveClicarNoLink() throws InterruptedException {
		inicializa();
		
		//CLICANDO EM UM LINK UTILIZANDO O MÉTODO LINKTEXT DO SLENIUM
		//getDriver().findElement(By.linkText("Voltar")).click();
		dsl.clicarLink("Voltar");
		
		//VALIDANDO A APRESENTAÇÃO DO TEXTO PELA ID DO SELENIUM
		//Assert.assertEquals("Voltou!", getDriver().findElement(By.id("resultado")).getText());
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test  // TESTE DE BUSCA DE TEXTOS NA PÁGINA
	public void deveBuscarTextosNaPagina() throws InterruptedException {
		inicializa();
		
		//VALIDANDO A APRESENTAÇÃO DO TEXTO PELA TAGNAME DO SELENIUM
		//Assert.assertEquals("Campo de Treinamento", getDriver().findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		//VALIDANDO APRESENTAÇÃO DO TEXTO UTLIZANDO O MÉTODO CLASSNAME DO SELENIUM
		//Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", getDriver().findElement(By.className("facilAchar")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
		Thread.sleep(5000);
		finaliza();
	}
	
	@Test //UTIZANDO JAVASCRIPT PARA TESTES
	public void testJavascript() throws InterruptedException {
		//Instanciar a classe JavascriptExecutor do Selenium (é necessário fazer um cast)
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		//Executando comandos Javascript
		//jsExecutor.executeScript("alert('Teste de alerta via Javascript Selenium')");
		jsExecutor.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via JS'");
		
		WebElement el = getDriver().findElement(By.id("elementosForm:nome"));
		jsExecutor.executeScript("arguments[0].style.border = arguments[1]", el, "solid 4px red");
		Thread.sleep(5000);
	}
	
	@Test
	public void deveClicarBotaoTabela() throws InterruptedException {
		Thread.sleep(5000);
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
		Thread.sleep(5000);
	}
	

}