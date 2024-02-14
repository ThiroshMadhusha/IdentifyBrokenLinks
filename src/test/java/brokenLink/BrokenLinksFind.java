package brokenLink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BrokenLinksFind {

	/**
	 * Retrive all links get all anchor tags
	 */
	@Test
	public void brokenLinks() {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://tutorialsninja.com/demo/");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		for(WebElement link : links) {
			//get the url of the link using "href"
			String url = link.getAttribute("href");
			
			System.out.println("----------------------------------------");
			
			//check this url is blank or not
			if(url == null || url.isEmpty()) {
				System.out.println("URL is Empty.");
				continue;
				//continue mean stop and go to next url. because empty urls cannot get responce
			}
			//retrive the response code
			try {
				HttpURLConnection huc = (HttpURLConnection)(new URL(url).openConnection());
				
				huc.connect();
				if(huc.getResponseCode() >= 400) {
					System.out.println(url + " is broken!");
				}else {
					System.out.println(url + " is valid!");
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.quit();
	}

}
