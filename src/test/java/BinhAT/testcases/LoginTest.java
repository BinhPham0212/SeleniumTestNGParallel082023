package BinhAT.testcases;

import BinhAT.common.BaseTest;
import BinhAT.dataproviders.DataLogin;
import BinhAT.drivers.DriverManager;
import BinhAT.helpers.CaptureHelper;
import BinhAT.helpers.ExcelHelper;
import BinhAT.listeners.TestListener;
import BinhAT.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Hashtable;
@Listeners(TestListener.class)         //Goi class Listener mới thiết lập
public class LoginTest extends BaseTest {

    //Khởi tạo đối tượng dùng chung cho toàn bộ class
    LoginPage loginPage;
//    @BeforeClass            //Khởi tạo 1 lần và ghi record từ đầu cho 1 class
//    public void setupStartRecord() {
//        CaptureHelper.startRecord("LoginTest");
//    }
//    @AfterClass
//    public void tearDownClass() {
//        CaptureHelper.stopRecord();
//    }

    @Test
    public void loginTestSuccess() {
        //Khởi tạo đối tượng trang LoginPage
        //truyền driver từ BaseTest
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/Resources/datatest/CRM.xlsx","Login");

        //gọi hàm "Login"
        loginPage.login(excelHelper.getCellData("EMAIL",1), excelHelper.getCellData("PASSWORD",1));
        excelHelper.setCellData("Passed",1,"RESULT");
    }

    @Test
    public void loginTestInvalidEmail() {
        //Khởi tạo đối tượng trang LoginPage
        //truyền driver từ BaseTest
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/Resources/datatest/CRM.xlsx","Login");

        //gọi hàm "Login"
        loginPage.login(excelHelper.getCellData("EMAIL",2), excelHelper.getCellData("PASSWORD",1));
    }
    //Truyền data từ dataProvider
    @Test(dataProvider = "data_provider_login_excel", dataProviderClass = DataLogin.class)    //Truyền name dataprovider và class dataprovider
    public void loginTestFromDataproviderExcel(String email, String password, String result) {
        //Khởi tạo đối tượng trang LoginPage
        //truyền driver từ BaseTest
        loginPage = new LoginPage();
        //gọi hàm "Login"
        loginPage.login(email, password);
    }

    //Truyền data từ dataProvider Custom Row
    @Test(priority = 1,dataProvider = "data_provider_login_excel_custom_row", dataProviderClass = DataLogin.class)    //Truyền name dataprovider và class dataprovider
    public void loginTestFromDataproviderExcelCustomRow(Hashtable< String, String > data) {
        //Khởi tạo đối tượng trang LoginPage
        //truyền driver từ BaseTest
        loginPage = new LoginPage();
        //gọi hàm "Login"
        loginPage.login(data.get("EMAIL"), data.get("PASSWORD"));
    }
}


