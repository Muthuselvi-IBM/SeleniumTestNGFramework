package com.clearcode.po;

import org.openqa.selenium.By;

public class HomePage {
	//public static By username_ip = By.id("authUse");
	//public static By pwd_ip = By.id("clearPass");
	//public static By login_btn = By.className("fa-sign-in");
	
	public static By title_logo = By.xpath("//a[text()='Uniform Store']");
	public static By user_icon = By.xpath("//i[@class='fa fa-user']");
	public static By login_link = By.xpath("//a[text()='Login']");
	public static By user_name = By.id("input-email");
	public static By password = By.id("input-password");
	public static By login_button = By.xpath("//input[@value='Login']");
	public static By home_screen = By.linkText("HOMESCREEN");
	
}
