package com.clearcode.po;

import org.openqa.selenium.By;

public class AdminLoginPage {
	public static By admin_uname = By.id("input-username");
	public static By admin_pwd = By.id("input-password");
	public static By login_btn = By.xpath("//button[text()=' Login']");
	public static By dashboard_header = By.xpath("//h1[text()='Dashboard']");
	public static By sales_icon = By.xpath("//i[@class='fa fa-shopping-cart fa-fw']");
	public static By order_icon = By.xpath("//li[@id='sale']/ul/li/a[text()='Orders']");
	public static By order_table = By.xpath("//a[text()='Order ID']");
	
	public static By table_order = By.xpath("//table[@class='table table-bordered table-hover']");
	public static By continue_btn = By.id("button-customer");
	
	public static By remove_icon = By.xpath("//tr[1]/td/button[@data-original-title=\"Remove\"]");
	
	public static By prod_name = By.id("input-product");
	public static By prod_list = By.xpath("//li[1]/a[text()='Brown T shirt']");
	
	public static By prod_quantity = By.id("input-quantity");
	public static By add_prod_btn = By.id("button-product-add");
	
}
