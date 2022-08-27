package br.ce.wcaquino.core;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	
	//MÉTODOS PERSONALIZADOS PARA EXECUTAREM FUNÇÕES ESPECÍFICAS
	
	public void escreve(String idCampo, String texto) {
		getDriver().findElement(By.id(idCampo)).clear();
		getDriver().findElement(By.id(idCampo)).sendKeys(texto);
	}
	 
	public void escreve(By by, String texto) {
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public String obterValorCampo(String idCampo) {
		return getDriver().findElement(By.id(idCampo)).getAttribute("value");
	}
	
	public void clicarRadio(String idRadio) {
		getDriver().findElement(By.id(idRadio)).click();
	}
	
	public void clicarCheckBox(String idCheckBox) {
		getDriver().findElement(By.id(idCheckBox)).click();
	}
	
	public boolean radioSelecionado(String idRadio) {
		return getDriver().findElement(By.id(idRadio)).isSelected();
	}
	
	public boolean checkBoxSelecionado(String idCheckBox) {
		return getDriver().findElement(By.id(idCheckBox)).isSelected();
	}
	
	public void selecionaComboBox(String idComboBox, String opcao) {
		WebElement elemento = getDriver().findElement(By.id(idComboBox));
		Select comboBox = new Select(elemento);
		comboBox.selectByVisibleText(opcao);
	}
	
	public void deselecionaComboBox(String idComboBox, String opcao) {
		WebElement elemento = getDriver().findElement(By.id(idComboBox));
		Select comboBox = new Select(elemento);
		comboBox.deselectByVisibleText(opcao);
	}
	
	public String obterValorCombo(String idComboBox) {
		WebElement elemento = getDriver().findElement(By.id(idComboBox));
		Select comboBox = new Select(elemento);
		return comboBox.getFirstSelectedOption().getText();
	}
	
	public void clicarBotao(String idBotao) {
		getDriver().findElement(By.id(idBotao)).click();
	}
	
	public void clicarLink(String idLink) {
		getDriver().findElement(By.linkText(idLink)).click();
	}
	
	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String idElemento) {
		return getDriver().findElement(By.id(idElemento)).getText();
	}
	
	public int obterQuantidade(String idComboBox) {
		WebElement elemento = getDriver().findElement(By.id(idComboBox));
		Select comboBox = new Select(elemento);
		List<WebElement> elementos = comboBox.getOptions();
		return elementos.size();
	}
	
	public boolean verificarOpcaoCombo(String idComboBox) {
		WebElement elemento = getDriver().findElement(By.id(idComboBox));
		Select comboBox = new Select(elemento);
		boolean existe = false;
		List<WebElement> elementos = comboBox.getOptions();
		for (WebElement lista : elementos) {
			if (lista.getText().equals("Superior")) {
				existe = true;
				break;
			}
		}
		return existe;
	}
	
	public int obterValoresCombo(String idComboBox) {
		WebElement elemento = getDriver().findElement(By.id(idComboBox));
		Select comboBox = new Select(elemento);
		List<WebElement> elementos = comboBox.getAllSelectedOptions();
		return elementos.size();
	}
	
	public List<String> verificarOpcoesCombo(String idComboBox) {
		WebElement elemento = getDriver().findElement(By.id(idComboBox));
		Select comboBox = new Select(elemento);
		boolean existe = false;
		List<String> opcoes = new ArrayList<String>();
		List<WebElement> elementos = comboBox.getAllSelectedOptions();
		for (WebElement lista : elementos) {
			opcoes.add(lista.getText());
			//System.out.println(lista.getText());
		}
		return opcoes;
	}
	
	public String obterOValorDoElemento(String idElemento, String valor) {
		return getDriver().findElement(By.id(idElemento)).getAttribute(valor);
	}
	
	public String obterTextoAlert() {
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public void botaoAceptAlert() {
		Alert alert = getDriver().switchTo().alert();
		alert.accept();
	}
	
	public void botaoDismissAlert() {
		Alert alert = getDriver().switchTo().alert();
		alert.dismiss();
	}
	
	public void escreverAlert(String texto) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(texto);
	}
	
	public void alterarFrame(String frame) {
		getDriver().switchTo().frame(frame);
	}
	
	public void voltarFramePrincipal() {
		getDriver().switchTo().parentFrame();
	}
	
	public void alterarJanela(String janela) {
		getDriver().switchTo().window(janela);
	}
	
	public void alterarJanelaSemTitulo() {
		getDriver().switchTo().window((String)getDriver().getWindowHandles().toArray()[1]);
	}
	
	public void voltarJanelaPrincipal() {
		getDriver().switchTo().window("");
	}
	
	public void voltarJanelaSemTitulo() {
		getDriver().switchTo().window((String)getDriver().getWindowHandles().toArray()[0]);
	}
	
	//******* JS ******** (A passagem de parâmetros não é obrigatória)
	public Object executarJS(String comando, Object... parametros) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		return jsExecutor.executeScript(comando, parametros);
	}
	
	/*********** Tabela   ******************************/
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		//procurar coluna registro
		WebElement tabela = getDriver().findElement
				(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha de registro
		int idLinha = obetrIndiceLinha(valor, tabela, idColuna);
		
		//procura coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da célula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+ idLinha +"]/td["+ idColunaBotao +"]"));
		celula.findElement(By.xpath(".//input"));
	}

	private int obetrIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+ idColuna +"]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)){
				idLinha = i + 1; // deve-se incrementar mais um devido ao indice do xpath iniciar de 1 e não de 0
				break;
			}
		}
		return idLinha;
	}

	private int obterIndiceColuna(String colunaBusca, WebElement tabela) {
		List<WebElement>colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(colunaBusca)){
				idColuna = i + 1; // deve-se incrementar mais um devido ao indice do xpath iniciar de 1 e não de 0
				break;
			}
		}
		return idColuna;
	}

	
	// 	VALTER ===================== 888888888888
	/*public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
		//procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
		
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}*/
	
}
