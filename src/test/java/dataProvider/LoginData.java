package dataProvider;
import org.testng.annotations.DataProvider;
import com.clearcode.utils.ExcelUtils;


public class LoginData {
	int a=2;
	@DataProvider
	public Object[][] loginUser(){
		
		return ExcelUtils.getData("TestData.xls", "loginUser");
	}
	
	@DataProvider
	public Object[][] ReturnOrder(){
		return ExcelUtils.getData("SuiteData.xls", "Sheet1");
	}
	
	@DataProvider
	public Object[][] AddProduct(){
		return ExcelUtils.getData("AddProduct.xls", "AddProduct");
		//return new Object[][] { ExcelUtils.data("AddProduct.xls", "AddProduct", "AddProduct").toArray() };
	}
}
