package br.ce.wcaquino.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.ce.wcaquino.core.BasePage;
import br.ce.wcaquino.core.DSL;

public class CampoDeTreinamentoPage extends BasePage{
	
	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);
	}

	public void setSobreNome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void clicarRadioSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public void clicarRadioSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void clicarCheckBoxComidaCarne() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
	}
	
	public void clicarCheckBoxComidaPizza() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
	}
	
	public void clicarCheckBoxComidaVegetariano() {
		dsl.clicarCheckBox("elementosForm:comidaFavorita:3");
	}
	
	public void clicarComboEscolaridadeSuperior() {
		dsl.selecionaComboBox("elementosForm:escolaridade", "Superior");
	}
	
	public void clicarComboMultEsporteKarate() {
		dsl.selecionaComboBox("elementosForm:esportes", "Karate");
	}
	
	public void clicarComboMultEsporteNatacao() {
		dsl.selecionaComboBox("elementosForm:esportes", "Natacao");
	}
	
	public void clicarComboMultEsporteNaoEsportista() {
		dsl.selecionaComboBox("elementosForm:esportes", "O que eh esporte?");
	}
	
	public void selecionaComboMultEsportes(String... valores) {
		for (String valor: valores) {
			dsl.selecionaComboBox("elementosForm:esportes", valor);
		}
		
	}
	
	public void clicarBotaoCadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
	}

	public String obterNomeCadastro() {
		//return dsl.obterTexto("descNome");
		return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String obterSobreNomeCadastro() {
		//return dsl.obterTexto("descSobrenome");
		return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
	}
	
	public String obterSexoCadastro() {
		//return dsl.obterTexto("descSexo");
		return dsl.obterTexto(By.xpath("//*[@id='descSexo']/span"));
	}
	
	public String obterComidaCadastro() {
		//return dsl.obterTexto("descComida");
		return dsl.obterTexto(By.xpath("//*[@id='descComida']/span"));
		
	}
	
	public String obterEscolaridadeCadastro() {
		//return dsl.obterTexto("descEscolaridade");
		return dsl.obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String obterEsporteCadastro() {
		//return dsl.obterTexto("descEsportes");
		return dsl.obterTexto(By.xpath("//*[@id='descEsportes']/span"));
	}
	
	public String obterTextAlert() {
		return dsl.obterTextoAlert();
	}
	
	public void fecharAlert() {
		dsl.botaoAceptAlert();
	}
	
	public String obterValorCampoSexoFeminino() {
		return dsl.obterTexto("elementosForm:sexo:1");
	}
	
	public String obterValorCampoSexoMasculino() {
		return dsl.obterTexto("elementosForm:sexo:0");
	}
}
