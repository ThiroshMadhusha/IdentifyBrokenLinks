package brokenURL;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * How to find Broken Links using Selenium WebDriver
 */
public class BrokenURL {
	
	@Test
	public void brokenLinks() throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(5000);

		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());

		for (WebElement link : links) {
			String linkURL = link.getAttribute("href");	
			URL url = new URL(linkURL);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
			httpURLConnection.setConnectTimeout(2000);
			httpURLConnection.connect();
			if(httpURLConnection.getResponseCode() == 200){
				System.out.println(linkURL + " - " + httpURLConnection.getResponseMessage());
			}else
				System.err.println(linkURL + " - " + httpURLConnection.getResponseCode() + " - " + httpURLConnection.getResponseMessage());
			
			httpURLConnection.disconnect();
		}

		Thread.sleep(2000);
		driver.quit();

	}
}