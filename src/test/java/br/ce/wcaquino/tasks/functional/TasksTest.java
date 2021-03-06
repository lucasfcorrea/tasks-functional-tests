package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import net.bytebuddy.agent.builder.AgentBuilder.Identified;

public class TasksTest 
{	
	public WebDriver acessarAplicacao() throws MalformedURLException
	{
//		WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.151:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.0.151:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException
	{
		WebDriver driver = acessarAplicacao();
		try
		{
		
		  //clicar em Add ToDo
		  driver.findElement(By .id("addTodo")).click();
		
		  //Escrever a descri??o
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
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException
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
	public void naoDeveSalvarComDataPassada() throws MalformedURLException
	{
		WebDriver driver = acessarAplicacao();
		try
		{
		
		  //clicar em Add ToDo
		  driver.findElement(By .id("addTodo")).click();
		
		  //Escrever a descri??o
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
	
	@Test
	public void deveRemoverTarefaComSucesso() throws MalformedURLException
	{
		WebDriver driver = acessarAplicacao();
		try
		{
		  //inserir tarefa
		  driver.findElement(By.id("addTodo")).click();
		  driver.findElement(By .id("task")).sendKeys("Teste via Selenium");
		  driver.findElement(By .id("dueDate")).sendKeys("01/02/2030");
		  driver.findElement(By .id("saveButton")).click();
		  String message = driver.findElement(By .id("message")).getText();
		  Assert.assertEquals("Due date must not be in past", message);
		  
		  //remover a tarefa
		  driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
		  message = driver.findElement(By .id("message")).getText();
		  Assert.assertEquals("Due date must not be in past", message);
		}
		
		finally
		{
		  //Fechar o browser
		  driver.quit();
		}
	}

}
