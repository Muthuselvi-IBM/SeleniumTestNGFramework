package com.clearcode.po;

import org.openqa.selenium.By;

public class ReturnProduct {
	public static By user_icon = By.xpath("//i[@class='fa fa-user']");
	public static By myAcc_link = By.linkText("My Account");
	public static By view_order = By.linkText("View your order history");
	public static By view_icon = By.xpath("//a[@data-original-title='View']");
	public static By order_table = By.xpath("//td[text()='Payment Address']");
	public static By return_page = By.xpath("//h1[text()='Product Returns']");
	public static By prod_quanity = By.xpath("//label[text()='Product Code']");
	public static By faulty_text = By.xpath("//textarea[@id='input-comment']");
	public static By agree_terms = By.name("agree");
	public static By submit_Btn = By.xpath("//input[@value='Submit']");
	public static By back_Btn = By.linkText("Back");
	public static By success_msg1 = By.xpath("//h1[text()='Product Returns']/../p[1]");
	public static By success_msg2 = By.xpath("//h1[text()='Product Returns']/../p[2]");
	public static By continue_Btn = By.linkText("Continue");
	
}
