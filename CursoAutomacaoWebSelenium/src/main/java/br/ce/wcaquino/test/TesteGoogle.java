package br.ce.wcaquino.test;
import java.awt.Dimension;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	@Test
	public void teste() {
		//criando o webdriver
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new ChromeDriver();
		//Configuração de tamanho de exibição de tela
		//driver.manage().window().setSize(new org.openqa.selenium.Dimension(1200, 765));
		driver.manage().window().maximize();
		//passando a caminho a página inicial
		driver.get("http://www.google.com");
		//imprimir o título da página no console
		System.out.println(driver.getTitle());
		Assert.assertEquals("Google", driver.getTitle());
		//fecha a instância do navegador e encerra a instancia do driver
		driver.quit();
	}

}
