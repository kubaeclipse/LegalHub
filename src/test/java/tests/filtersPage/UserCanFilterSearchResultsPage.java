package tests.filtersPage;

import config.CredentialsManager;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import tests.TestDriver;

import java.util.Arrays;

public class UserCanFilterSearchResultsPage extends TestDriver {

    @Override
    public String getInitialUrl() {
        return "http://docker-host-test-1.test.fruugo:8110/";
    }

    @Test
    public void verifyFilterLabelsTest() {

        new HomePage(driver)
                .logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword());
        new SearchResultsPage(driver)
                .performSearchAndVerify("Wolfmother Album Cover", "Wolfmother Album Cover")
                .navigateToViewFilters()
                .verifyFilterLabels();

    }

    @Test
    public void filterByRetailerName() {
        new HomePage(driver)
                .logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword());
        new SearchResultsPage(driver)
                .performSearchAndVerify("In-dash system A/V", "In-dash system A/V")
                .navigateToViewFilters()
                .verifyFilterLabels()
                .selectRetailerFilter("Eclipse Test Account")
                .clickApplyButton()
                .verifyChipName("9097")
                .verifyRetailerName("Eclipse Test Account");
    }

    @Test
    public void filterByBrandName() {
        new HomePage(driver)
                .logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword());
        new SearchResultsPage(driver)
                .performSearchAndVerify("Nicola Spring Square Padded French Mattress Dining Chair Cushion Seat Pad", "Nicola Spring Square Padded French Mattress Dining Chair Cushion Seat Pad")
                .navigateToViewFilters()
                .verifyFilterLabels()
                .selectBrandFilter("Nicola Spring")
                .clickApplyButton()
                .verifyChipName("Nicola Spring")
                .verifyBrandName("Nicola Spring");
    }

    @Test
    public void filterBySelectingMarketplaceCountriesTest() {
        new HomePage(driver)
                .logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword());
        new SearchResultsPage(driver)
                .performSearchAndVerify("Pioneer NABRTS Test Strips", "Pioneer NABRTS Test Strips")
                .navigateToViewFilters()
                .verifyFilterLabels()
                .selectMarketplaceCountriesFromList(Arrays.asList("AU", "FI", "GB"))
                .clickApplyButton()
                .verifyChipName("\"AU\", \"FI\", \"GB\"")
                .readAndVerifyMarketplaceCountryList(Arrays.asList("Australia", "Finland", "United Kingdom"));

    }

    @Test
    public void filterBySearchingMarketplaceCountriesTest() {
        new HomePage(driver)
                .logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword());
        new SearchResultsPage(driver)
                .performSearchAndVerify("Eclipse 70-14JR Junior Hacksaw", "Eclipse 70-14JR Junior Hacksaw")
                .navigateToViewFilters()
                .verifyFilterLabels()
                .selectMarketplaceCountriesByEnteringName(Arrays.asList("Australia", "United Kingdom", "United States"))
                .clickApplyButton()
                .verifyChipName("\"AU\", \"GB\", \"US\"")
                .readAndVerifyMarketplaceCountryList(Arrays.asList("Australia", "United States", "United Kingdom"));
    }

    @Test
    public void filterBySearchingRetailerCountriesTest() {
        new HomePage(driver)
                .logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword());
        new SearchResultsPage(driver)
                .performSearchAndVerify("Fruit Basket Chrome Round TEST", "Fruit Basket Chrome Round TEST")
                .navigateToViewFilters()
                .verifyFilterLabels()
                .selectRetailerCountriesByEnteringName(Arrays.asList("United Kingdom"))
                .clickApplyButton()
                .verifyChipName("\"GB\"")
                .readAndVerifyRetailerCountry(Arrays.asList("GB"));
    }

    @Test
    public void filterBySelectingRetailerCountriesTest() {
        new HomePage(driver)
                .logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword());
        new SearchResultsPage(driver)
                .performSearchAndVerify("Pioneer NABRTS Test Strips", "Pioneer NABRTS Test Strips")
                .navigateToViewFilters()
                .verifyFilterLabels()
                .selectRetailerCountriesFromList(Arrays.asList("AU", "FI", "GB"))
                .clickApplyButton()
                .verifyChipName("\"AU\", \"FI\", \"GB\"")
                .readAndVerifyRetailerCountry(Arrays.asList("GB"));

    }

    @Test
    public void filterByCategoryTest() {
        new HomePage(driver)
                .logInToLegalHub(CredentialsManager.getUsername(), CredentialsManager.getPassword());
        new SearchResultsPage(driver)
                .performSearchAndVerify("Vinsetto 360° Swivel Office Chair", "Vinsetto 360° Swivel Office Chair")
                .navigateToViewFilters()
                .verifyFilterLabels()
                .enterCategoryPath("Furniture > Office Furniture")
                .selectSubCategory("Office Chairs")
                .verifyCategoryPath("Furniture > Office Furniture > Office Chairs")
                .clickApplyButton()
                .verifyChipName("Furniture > Office Furniture > Office Chairs")
                .readAndVerifyProductCategoryPath("Furniture > Office Furniture > Office Chairs");

    }

}

// TO DO:
// Verify Reset and Close buttons
// Verify Creation Date filter