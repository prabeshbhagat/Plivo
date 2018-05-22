package com.plivo.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.plivo.qa.base.TestBase;

public class CouponCode extends TestBase {
	
	

	@FindBy(id = "id_username")
	WebElement username;

	@FindBy(id = "id_password")
	WebElement password;

	@FindBy(id = "login-sub")
	WebElement loginBtn;

	@FindBy(xpath = "//a[contains(text(),'Account')]")
	WebElement accounts;

	@FindBy(xpath = "//a[contains(text(),'Payments')]")
	WebElement payments;

	@FindBy(xpath = "//a[contains(text(),'Redeem a coupon code instead')]")
	WebElement redeemCoupon;

	@FindBy(xpath = "//input[@id='coupon']")
	WebElement redeemCouponBox;

	@FindBy(xpath = "//a[contains(text(),'Submit')]")
	WebElement submitBtn;

	@FindBy(xpath = "//div[@id='coupon_error']")
	WebElement msgDisplayed;

	public CouponCode() {
		PageFactory.initElements(driver, this);
	}

	public String verifyLoginPageTitle() {
		return driver.getTitle();
	}

	public String validateCouponcode(String un, String pwd, String code) throws InterruptedException {
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		accounts.click();
		payments.click();
		redeemCoupon.click();
		WebDriverWait wait =new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(redeemCouponBox)).sendKeys(code);;
		submitBtn.click();
		WebElement text=wait.until(ExpectedConditions.visibilityOf(msgDisplayed));
		String msg = text.getText();
		return msg;

	}

}
