package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.builder.StandardToStringStyle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sun.org.apache.regexp.internal.recompile;

import br.ce.wcaquino.page.*;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

@RunWith(Parameterized.class)
public class TesteParametrizavelRegrasDeNegocioCadastro {
	
	private DSL dsl;
	private CampoDeTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter (value = 1)
	public String sobrenome;
	@Parameter (value = 2)
	public String sexo;
	@Parameter (value = 3)
	public List<String> comidas;
	@Parameter (value = 4)
	public String[] esportes;
	@Parameter (value = 5)
	public String mensagem;
	
	
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
	
	@Parameters
	public static Collection<Object[]> massaDeTestes(){
		return Arrays.asList(new Object[][] {
			{"","","",Arrays.asList(),new String[]{}, "Nome eh obrigatorio"},
			{"Haroldo","","",Arrays.asList(),new String[]{}, "Sobrenome eh obrigatorio"},
			{"Haroldo","R Santos","",Arrays.asList(),new String[]{}, "Sexo eh obrigatorio"},
			{"Haroldo","R Santos","Masculino",Arrays.asList("Carne", "Vegetariano"),new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Haroldo","R Santos","Masculino",Arrays.asList("Carne"),new String[]{"Natacao", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void testarEsportistaIndeciso() throws InterruptedException {
		inicializa();
		
		
		page.setNome(nome);
		page.setSobreNome(sobrenome);
		if (sexo.equals("Masculino")) {
			page.clicarRadioSexoMasculino();
		}
		if (sexo.equals("Feminino")) {
			page.clicarRadioSexoFeminino();
		}
		if (comidas.contains("Carne")) {
			page.clicarCheckBoxComidaCarne();
		}
		if (comidas.contains("Pizza")) {
			page.clicarCheckBoxComidaPizza();
		}
		if (comidas.contains("Vegetariano")) {
			page.clicarCheckBoxComidaVegetariano();
		}
		page.selecionaComboMultEsportes(esportes);
		page.clicarBotaoCadastrar();
		System.out.println(mensagem);
		Assert.assertEquals(mensagem, page.obterTextAlert());
		
		Thread.sleep(5000);
		finaliza();
	}

}
