package com.clearcode.testsuite;

import java.awt.List;
import java.util.Iterator;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.clearcode.po.AdminLoginPage;
import com.clearcode.po.HomePage;
import com.clearcode.po.ReturnProduct;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;
import dataProvider.LoginData;

public class AdminModifyOrder extends TestSetup{
	int row,col;
	
	@BeforeMethod
	@Parameters({"admin_url","admin_uname","admin_pwd"})
	public void login_admin(String admin_url,String admin_uname,String admin_pwd) {
		wdu.navigate(admin_url);
		
		wdu.assertTitle("Administration");
		
		if(wdu.verifyElementOnPage(AdminLoginPage.admin_uname)) {
			wdu.type(AdminLoginPage.admin_uname, admin_uname);
			wdu.type(AdminLoginPage.admin_pwd, admin_pwd);
			wdu.click(AdminLoginPage.login_btn);
			ATUReports.add("Verify Home Screen is displayed",AdminLoginPage.dashboard_header.toString(), LogAs.PASSED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
			
			navigateToUserOrderPage();
			
			removeProduct();
		}
		else {
			ATUReports.add("Verify Home Screen is displayed",AdminLoginPage.dashboard_header.toString(), LogAs.FAILED, new CaptureScreen(
                    ScreenshotOf.BROWSER_PAGE));
		}
	
	}
	
	public void navigateToUserOrderPage() {
		wdu.click(AdminLoginPage.sales_icon);
		if(wdu.verifyElementOnPage(AdminLoginPage.order_icon)) {
			wdu.click(AdminLoginPage.order_icon);
			wdu.scrollToPage(AdminLoginPage.order_table);
			
			int row_no = wdu.getTextfromWebTable(AdminLoginPage.table_order, "Muthuselvi Rajapandi");
			
			By edit_icon = By.xpath("//tbody/tr["+row_no+"]/td[8]/a[@data-original-title='Edit']");
			wdu.click(edit_icon);
			
			if(wdu.verifyElementOnPage(AdminLoginPage.continue_btn)) {
				wdu.click(AdminLoginPage.continue_btn);
			}
			
		}
	}
	
	public void removeProduct() {
		wdu.scrollToPage(AdminLoginPage.remove_icon);
		By remove_icon = By.xpath("//tr/td/button[@data-original-title=\"Remove\"]");
		
		Iterator<WebElement> iter = wdu.findElements(remove_icon);
		 while(iter.hasNext()) {
         	WebElement remove_element = iter.next();
         	remove_element.click();
		 }
		
		
	}
	
	@Test(dataProvider = "AddProduct",dataProviderClass = LoginData.class)
	public void AddProduct(String desc,String order,String prod_name,String quanity) {
		if(wdu.verifyElementOnPage(AdminLoginPage.prod_name)) {
			wdu.type(AdminLoginPage.prod_name, prod_name);
			wdu.click(AdminLoginPage.prod_list);
			
			wdu.type(AdminLoginPage.prod_quantity, quanity);
			wdu.click(AdminLoginPage.add_prod_btn);
		}
	}

}
