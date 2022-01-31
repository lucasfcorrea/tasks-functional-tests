package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest 
{	
	public WebDriver acessarAplicacao()
	{
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso()
	{
		WebDriver driver = acessarAplicacao();
		try
		{
		
		  //clicar em Add ToDo
		  driver.findElement(By .id("addTodo")).click();
		
		  //Escrever a descrição
		  driver.findElement(By .id("task")).sendKeys("Teste via Selenium");
		
		  //Escrever a data
		  driver.findElement(By .id("dueDate")).sendKeys("01/02/2030");
		
		  //Escrever em Salvar
		  driver.findElement(By .id("saveButton")).click();
		
		  //Validar mensagem de sucesso
		  String message = driver.findElement(By .id("message")).getText();
		  Assert.assertEquals("Sucess!", message);
		}
		
		finally
		{
		  //Fechar o browser
		  driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao()
	{
		WebDriver driver = acessarAplicacao();
		try
		{
		
		  //clicar em Add ToDo
		  driver.findElement(By .id("addTodo")).click();
		
		  //Escrever a data
		  driver.findElement(By .id("dueDate")).sendKeys("01/02/2030");
		
		  //Escrever em Salvar
		  driver.findElement(By .id("saveButton")).click();
		
		  //Validar mensagem de sucesso
		  String message = driver.findElement(By .id("message")).getText();
		  Assert.assertEquals ("Fill the task description", message);
		}
		
		finally
		{
		  //Fechar o browser
		  driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarComDataPassada()
	{
		WebDriver driver = acessarAplicacao();
		try
		{
		
		  //clicar em Add ToDo
		  driver.findElement(By .id("addTodo")).click();
		
		  //Escrever a descrição
		  driver.findElement(By .id("task")).sendKeys("Teste via Selenium");
		
		  //Escrever a data
		  driver.findElement(By .id("dueDate")).sendKeys("01/02/2010");
		
		  //Escrever em Salvar
		  driver.findElement(By .id("saveButton")).click();
		
		  //Validar mensagem de sucesso
		  String message = driver.findElement(By .id("message")).getText();
		  Assert.assertEquals("Due date must not be in past", message);
		}
		
		finally
		{
		  //Fechar o browser
		  driver.quit();
		}
	}

}
