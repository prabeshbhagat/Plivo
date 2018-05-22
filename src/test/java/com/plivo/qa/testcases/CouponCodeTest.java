package com.plivo.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.plivo.qa.base.TestBase;
import com.plivo.qa.pages.CouponCode;

public class CouponCodeTest extends TestBase {

	CouponCode page;

	public CouponCodeTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		intilization();
		page = new CouponCode();
	}


	@Test(priority = 1)
	public void validateCouponCodeTest() throws InterruptedException {
		String msg = page.validateCouponcode(prop.getProperty("username"), prop.getProperty("password"),prop.getProperty("couponcode"));
		Assert.assertEquals(msg, "This coupon code is invalid.","The test case Failed As the Entered Coupon is valid");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
