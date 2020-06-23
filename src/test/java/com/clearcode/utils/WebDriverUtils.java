package com.clearcode.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverUtils {
	
	WebDriver driver = null;
	WebDriverWait wait = null;
	JavascriptExecutor js;
	Properties p;
		public void initialize() {
			String b=null;
			PropertyUtils pu = new PropertyUtils();
			p = pu.loadProperty("Project.properties");
//			Properties db = pu.loadProperty("Db.properties");
			String browser = p.getProperty("browser");
			try {
				switch(browser)
				{
				case "gc":
					b = "Google Chrome";
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;
				case "ff":
					b = "FireFox";
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					break;
				case "ie":
					b = "Internet Explorer";
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
					break;
				default:
					b = "Default - Google Chrome";
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				}
				ATUReports.setWebDriver(driver);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				wait = new WebDriverWait(driver,30);
				ATUReports.add("Initializing browser - "+b, LogAs.PASSED, new CaptureScreen(
	                    ScreenshotOf.DESKTOP));
			}
			catch(Exception e)
			{
				ATUReports.add("Initializing browser - "+b, e.getMessage() , LogAs.FAILED, new CaptureScreen(
	                    ScreenshotOf.DESKTOP));
			}
		}
		
		public void launchUrl() {
			String url = p.getProperty("url");
			ATUReports.add("Launch URL",url, LogAs.INFO, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
			driver.get(url);
			ATUReports.add("Loaded URL",driver.getCurrentUrl() , LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
		
		public void navigate(String url) {
			ATUReports.add("Navigate from "+driver.getCurrentUrl()+" to "+url, LogAs.INFO, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
			driver.get(url);
			ATUReports.add("Navigation",driver.getCurrentUrl() , LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
		
		public void type(By loc, String value) {
			if(value==null)
			{
				value="";
			}
			find(loc).clear();
			find(loc).sendKeys(value);
			ATUReports.add("Type into "+loc.toString(),value, LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
		
		public void click(By loc) {
			try
			{
				find(loc).click();
			}
			catch(WebDriverException e) {
				try
				{
					Actions act = new Actions(driver);
					act.click(find(loc)).perform();
				}
				catch(Exception ex)
				{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("argument[0].click()", find(loc));
				}
			}
			ATUReports.add("Click at"+loc.toString(), LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
		
		public void selectByVisibleText(By loc, String value) {
			Select obj = new Select(find(loc));
			obj.selectByVisibleText(value);
			ATUReports.add("Select By Visible Text at "+loc.toString(),value, LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
		
		public void selectByValue(By loc, String value) {
			Select obj = new Select(find(loc));
			obj.selectByValue(value);
			ATUReports.add("Select By Value Text at "+loc.toString(),value, LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
		
		public WebDriver driver() {
			return driver;
		}
		
		public void mouseOver(By loc) {
			Actions act = new Actions(driver);
			act.moveToElement(find(loc)).perform();
			ATUReports.add("Mouse over "+loc.toString(), LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
		
		public void assertTitle(String title) {
			try
			{
			Assert.assertEquals(driver.getTitle(), title);
			ATUReports.add("Assert Title",title,driver.getTitle(), LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
			}
			catch(AssertionError e) {
				ATUReports.add("Assert Title",title,driver.getTitle(), LogAs.FAILED, new CaptureScreen(
	                    ScreenshotOf.BROWSER_PAGE));
			}
		}
		
		public void assertText(By loc, String text) {
			try
			{
			Assert.assertEquals(find(loc).getText(), text);
			ATUReports.add("Assert Text",text,find(loc).getText(), LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
			}
			catch(AssertionError e) {
				ATUReports.add("Assert Text",text,find(loc).getText(), LogAs.FAILED, new CaptureScreen(
	                    ScreenshotOf.BROWSER_PAGE));
			}
		}
		
		public void switchWindowByTitle(String title) {
			Set<String> winids = driver.getWindowHandles();
			for(String win:winids) {
				if(driver.switchTo().window(win).getTitle().equals(title))
				{
					ATUReports.add("Switch to Window By Title",title, LogAs.PASSED, new CaptureScreen(
		                    ScreenshotOf.BROWSER_PAGE));
					return;
				}
			}
					ATUReports.add("No Such Window Title Found",title, LogAs.FAILED, new CaptureScreen(
		                    ScreenshotOf.BROWSER_PAGE));
		}
		
		public void switchWindowByTitleContains(String title) {
			Set<String> winids = driver.getWindowHandles();
			for(String win:winids) {
				if(driver.switchTo().window(win).getTitle().contains(title))
				{
					ATUReports.add("Switch to Window By Title Content",title, LogAs.PASSED, new CaptureScreen(
		                    ScreenshotOf.BROWSER_PAGE));
					return;
				}
			}
					ATUReports.add("No Such Window Title Content Found",title, LogAs.FAILED, new CaptureScreen(
		                    ScreenshotOf.BROWSER_PAGE));
		}
		
		private WebElement find(By loc) {
			driver.switchTo().defaultContent();
			WebElement element=null;
			try
			{
				element = driver.findElement(loc);
//				wait.until(ExpectedConditions.presenceOfElementLocated(loc));
//				wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
				wait.until(ExpectedConditions.visibilityOf(element));
			}
			catch(NoSuchElementException e)
			{
				List<WebElement> frames = driver.findElements(By.tagName("frame"));
				if(frames.size()==0)
				{
					frames = driver.findElements(By.tagName("iframe"));
				}
				if(frames.size()==0)
				{
					ATUReports.add("No Element Found and No Frames Present",loc.toString(), LogAs.FAILED, new CaptureScreen(
		                    ScreenshotOf.BROWSER_PAGE));
				}
				for(WebElement frame:frames)
				{
					driver.switchTo().frame(frame);
					if(driver.findElements(loc).size()>0)
						return driver.findElement(loc);
					driver.switchTo().defaultContent();
				}
				ATUReports.add("No Element Found Also inside frames",loc.toString(), LogAs.FAILED, new CaptureScreen(
	                    ScreenshotOf.BROWSER_PAGE));
				
			}
			catch(TimeoutException e)
			{
				ATUReports.add("TimeOut Waiting for the Element",loc.toString(), LogAs.FAILED, new CaptureScreen(
	                    ScreenshotOf.BROWSER_PAGE));
			}
			return element;
		}
		
		public void switchFrame(By loc) {
			driver.switchTo().frame(driver.findElement(loc));
		}
		
		public void switchOut() {
			driver.switchTo().defaultContent();
		}
		
		public void enableCheckbox(By loc) {
			WebElement e = find(loc);
			if(!e.isSelected()) {
				e.click();
			}
			ATUReports.add("Select RadioButton",loc.toString(), LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
		
		public Boolean verifyElementOnPage(By loc) {
			WebElement we = find(loc);
			Boolean elementVisibleFlag = true;
			if(we.isDisplayed() || we.isEnabled()) {
				ATUReports.add("Verify Element on Page",loc.toString(), LogAs.PASSED, new CaptureScreen(
	                    ScreenshotOf.BROWSER_PAGE));
			}
			else {
				ATUReports.add("Verify Element on Page",loc.toString(), LogAs.FAILED, new CaptureScreen(
	                    ScreenshotOf.BROWSER_PAGE));
				elementVisibleFlag = false;
			}
			return elementVisibleFlag;
		}
		
		public void scrollToPage(By loc) {
			js = (JavascriptExecutor)driver;
			WebElement locateElement = find(loc);
			js.executeScript("arguments[0].scrollIntoView();", locateElement);
			ATUReports.add("Scrolling till the element is visible", LogAs.PASSED, new CaptureScreen(
					ScreenshotOf.BROWSER_PAGE));
		}
		
		public Iterator<WebElement> findElements(By loc) {
			List<WebElement> elements = driver.findElements(loc);
			Iterator<WebElement> iter = elements.iterator();
			
			return iter;
			
		}
		
		public int getTextfromWebTable(By loc,String table_text) {
			WebElement table = find(loc);
			int row_no = 0;
			String cell_text="";
			List<WebElement> table_rows = table.findElements(By.tagName("tr"));
			int rows_count = table_rows.size();
			
			outerloop:
			for(int row=0;row<=rows_count;row++) {
				List<WebElement> table_cols = table_rows.get(row).findElements(By.tagName("td"));
				int cols_count = table_cols.size();
				
				for(int col=1;col<cols_count;col++) {
				 cell_text = table_cols.get(col).getText();
				 System.out.println(cell_text);
					if(cell_text.contentEquals(table_text)) {
						
						 row_no = row;
						 break outerloop;
					 }
				}
			}
			return row_no;
			
		}
		
		public void exit() {
			try
			{
				driver.quit();
				ATUReports.add("Closing browser", LogAs.PASSED, new CaptureScreen(
						ScreenshotOf.DESKTOP));
			}
			catch(Exception e)
			{
				ATUReports.add("Closing browser", LogAs.FAILED, new CaptureScreen(
	                    ScreenshotOf.DESKTOP));
			}
		}
}
