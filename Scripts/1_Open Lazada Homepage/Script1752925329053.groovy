import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.exception.*

WebUI.openBrowser(GlobalVariable.baseUrl)

WebUI.maximizeWindow()

String currentUrl = WebUI.getUrl();

if(!(currentUrl.contains("lazada"))) {
	throw new StepFailedException("URL not contain 'Lazada'")
}

TestObject[] elements = [findTestObject('Object Repository/2_Body/Homepage/spaceHeaderContent'), findTestObject('Object Repository/2_Body/Homepage/btnLogin')
    , findTestObject('Object Repository/2_Body/Homepage/btnSignUp-Daftar'), findTestObject('Object Repository/2_Body/Homepage/btnLogoLazadaTopLeft')
    , findTestObject('Object Repository/2_Body/Homepage/inputSearchQueryBox'), findTestObject('Object Repository/2_Body/Homepage/btnNavCart')
    , findTestObject('Object Repository/2_Body/Homepage/spaceBannersSlide')]

for (TestObject element : elements) {
    WebUI.waitForElementVisible(element, GlobalVariable.waitForTimeout)
}