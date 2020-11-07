package in.amazon.tests;

import org.testng.annotations.Test;

public class AmazonHomepageTest extends BaseTests {

	@Test
	public void searchProduct() throws Exception {

		String product = "Apple Watch";

		String category = "Electronics";

		homepage.searchProduct(product, category);

	}

}
