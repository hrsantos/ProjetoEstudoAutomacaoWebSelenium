package br.ce.wcaquino.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	private static WebDriver driver;
	
	//construtor do driver
	private DriverFactory() {}
	
	//metodo para a criação do driver utilizando o padrão singleton
	public static WebDriver getDriver(){
		if(driver == null) {
			switch (Propriedades.browser) {
			case CHROME: 
				driver = new ChromeDriver();
				break;
			case FIREFOX: 
				driver = new FirefoxDriver();
			break;
			}
			driver.manage().window().setSize(new Dimension(1200, 765));
		}
		return driver;
	}
	
	//método para encerrar o driver
	public static void killDriver() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
