package in.amazon.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class AmazonHomepageTest extends BaseTests {

	@Test
	public void searchProduct() throws Exception {

		String product = "Apple Watch";

		String category = "Electronics";

		extentReportUtils.createTest(
				"TC-001 - Verify Search Product with Product and category as - " + product + " and " + category);

		String result = homepage.searchProduct(product, category);

		extentReportUtils.addLog(Status.INFO, result);

	}

}
