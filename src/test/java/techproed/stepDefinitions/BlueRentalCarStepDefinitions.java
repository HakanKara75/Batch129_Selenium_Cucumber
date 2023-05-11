package techproed.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import techproed.pages.BlueRentalCarPage;
import techproed.utilities.ConfigReader;
import techproed.utilities.Driver;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class BlueRentalCarStepDefinitions {
    BlueRentalCarPage blueRentalCarPage = new BlueRentalCarPage();

    @Given("kullanici blue rental car sayfasina gider")
    public void kullanici_blue_rental_car_sayfasina_gider() {

        Driver.getDriver().get(ConfigReader.getProperty("blueRentACarUrl"));
           }

    @When("login olur")
    public void login_olur(DataTable dataTable) {
        blueRentalCarPage.loginButonu.click();
        blueRentalCarPage.emailBox.sendKeys(dataTable.row(1).get(0));
        blueRentalCarPage.passwordBox.sendKeys(dataTable.row(1).get(1));
        blueRentalCarPage.loginConfirm.click();

        //cikis yapiyoruz
        blueRentalCarPage.dropDown.click();
        blueRentalCarPage.logOut.click();
        blueRentalCarPage.ok.click();

        //2.data girisi
        blueRentalCarPage.loginButonu.click();
        blueRentalCarPage.emailBox.sendKeys(dataTable.row(2).get(0));
        blueRentalCarPage.passwordBox.sendKeys(dataTable.row(2).get(1));
        blueRentalCarPage.loginConfirm.click();
    }


    @When("login olur map")
    public void loginOlurMap(DataTable dataTable) {
        dataTable.asMaps().get(0).get("emailadress");
        dataTable.asMaps().get(0).get("password");

        //Datayı birinci satırı Key olarak kabul eden List<Map> data türüne çavirdik.
        List<Map<String, String>> bilgiler = dataTable.asMaps();//[{emailadress=jack@gmail.com, password=12345}, {emailadress=sam.walker@bluerentalcars.com, password=c!fas_art}]
        System.out.println("bilgiler = " + bilgiler);


        for (Map<String, String> w : bilgiler) {//Her bir Map'i alarak "Key değerlerini kullanarak "Value" değerlerini çağırdık ve testimizde kullandık.
            blueRentalCarPage.loginButonu.click();
            blueRentalCarPage.emailBox.sendKeys(w.get("emailadress"));
            blueRentalCarPage.passwordBox.sendKeys(w.get("password"));
            blueRentalCarPage.loginConfirm.click();

            //cikis yapiyoruz
            blueRentalCarPage.dropDown.click();
            blueRentalCarPage.logOut.click();
            blueRentalCarPage.ok.click();

        }


    }
}