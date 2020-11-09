package in.amazon.utils;

import org.testng.annotations.DataProvider;

import commonLibs.utils.DatabaseDriver;

public class TestDatasourceDb {

	DatabaseDriver dbDriver;

	@DataProvider
	public Object[][] getDataForSearchProduct() throws Exception {

		dbDriver = new DatabaseDriver();

		String server = "localhost";

		int portNumber = 3306;

		String database = "amazonTestData";

		String username = "root";

		String password = "admin@1234";

		String query = "select * from searchProductTestData";

		dbDriver.openConnection(server, database, portNumber, username, password);

		Object[][] data = dbDriver.executeSelectQuery(query);

		return data;

	}

}
