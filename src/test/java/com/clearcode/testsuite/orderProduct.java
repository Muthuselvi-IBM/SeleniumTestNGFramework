package com.clearcode.testsuite;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearcode.po.HomePage;
import com.clearcode.po.ReturnProduct;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import dataProvider.LoginData;

public class orderProduct extends TestSetup{
	String success_msg_text1 = "Thank you for submitting your return request. Your request has been sent to the relevant department for processing.";
	String success_msg_text2 = "You will be notified via e-mail as to the status of your request.";
	
	//@Test(dataProvider ="loginUser", dataProviderClass = LoginData.class,groups = {"OrderCancellation"},priority=1)
	@BeforeTest
	@Parameters({"user_url","username","password"})
	public void loginUser(String user_url,String username, String password) {
		System.out.println(user_url);
		wdu.navigate(user_url);
		Boolean logo = wdu.verifyElementOnPage(HomePage.title_logo);
		if(logo) {
			ATUReports.add("Verify Uniform title logo",HomePage.title_logo.toString(), LogAs.PASSED, new CaptureScreen(
	                    ScreenshotOf.BROWSER_PAGE));
			wdu.click(HomePage.title_logo);
			wdu.click(HomePage.user_icon);
			wdu.click(HomePage.login_link);
			Boolean login_page = wdu.verifyElementOnPage(HomePage.user_name);
			if(login_page) {
				wdu.type(HomePage.user_name, username);
				wdu.type(HomePage.password,password);
				wdu.click(HomePage.login_button);
				
				if(wdu.verifyElementOnPage(HomePage.home_screen)==true) {
					ATUReports.add("Verify Home Screen is displayed",HomePage.home_screen.toString(), LogAs.PASSED, new CaptureScreen(
		                    ScreenshotOf.BROWSER_PAGE));
					
				}
			}
			else {
				ATUReports.add("Verify Login Page is loaded",HomePage.user_name.toString(), LogAs.FAILED, new CaptureScreen(
	                    ScreenshotOf.BROWSER_PAGE));
			}
		}
		else {
			ATUReports.add("Verify Logo of Uniform page is displayed",HomePage.title_logo.toString(), LogAs.FAILED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
		
	}
	
	@Test(priority=2,dataProvider = "ReturnOrder",dataProviderClass = LoginData.class)  //,groups = {"OrderCancellation"}
	public void returnProduct(String desc, String order_id,String return_reason,String product_status,String faulty_reason)  {
			
		wdu.click(ReturnProduct.user_icon);
		wdu.click(ReturnProduct.myAcc_link);
		Boolean viewOrderHist = wdu.verifyElementOnPage(ReturnProduct.view_order);
		if(viewOrderHist) {
			ATUReports.add("Verify View Order History Link",ReturnProduct.view_order.toString(), LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));;
            wdu.click(ReturnProduct.view_order); 
            
            wdu.click(ReturnProduct.view_icon);
            
            ATUReports.add("Verify Ordered Products Page",ReturnProduct.view_icon.toString(), LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));;
		
		}
		
            wdu.scrollToPage(ReturnProduct.order_table);
            
            By return_icon = By.xpath("//tr["+order_id+"]/td[6]/a[@data-original-title=\"Return\"]");
            
            Iterator<WebElement> iter = wdu.findElements(return_icon);
            while(iter.hasNext()) {
            	WebElement return_product = iter.next();
            	return_product.click();
            	wdu.assertText(ReturnProduct.return_page, "PRODUCT RETURNS");
            	wdu.scrollToPage(ReturnProduct.prod_quanity);
            	
            	By return_radio_btn = By.xpath("//label[text()[normalize-space()  = '"+return_reason+"']]");
            	wdu.click(return_radio_btn);
            	
            	By product_opened = By.xpath("//label[text()[normalize-space() ='"+product_status+"']]");
            	wdu.click(product_opened);
            	
            	wdu.type(ReturnProduct.faulty_text, faulty_reason);
            	
            	wdu.click(ReturnProduct.agree_terms);
            	
            	ATUReports.add("Verify Return Page",ReturnProduct.agree_terms.toString(), LogAs.PASSED, new CaptureScreen(
                        ScreenshotOf.BROWSER_PAGE));;
                        
                wdu.click(ReturnProduct.submit_Btn);
            	
                if(wdu.verifyElementOnPage(ReturnProduct.success_msg1)) {
                	 wdu.assertText(ReturnProduct.success_msg1,success_msg_text1);
                     wdu.assertText(ReturnProduct.success_msg2,success_msg_text2);	
                     wdu.click(ReturnProduct.continue_Btn);
                }
                else {
                	ATUReports.add("Verify Success Message for Product Return Request",ReturnProduct.success_msg1.toString(), LogAs.FAILED, new CaptureScreen(
                            ScreenshotOf.BROWSER_PAGE));;
                }
               
            	}
            	
            }
                    
            
		}
		
	

