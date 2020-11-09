package in.amazon.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import in.amazon.utils.TestDataSource;

public class AmazonHomepageTest extends BaseTests {

	@Test(dataProvider = "getDataForSearchProduct", dataProviderClass = TestDataSource.class)
	public void verifySearchProduct(String product, String category) throws Exception {

		extentReportUtils.createTest(
				"TC-001 - Verify Search Product with Product and category as - " + product + " and " + category);

		String result = homepage.searchProduct(product, category);

		extentReportUtils.addLog(Status.INFO, result);

		String expectedResult = "1-48 of over 10,000 results for \"apple watch\"";

		Assert.assertEquals(result, expectedResult);

	}

}
