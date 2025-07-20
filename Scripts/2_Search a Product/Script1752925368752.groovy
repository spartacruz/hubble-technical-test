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
import java.nio.charset.StandardCharsets as StandardCharsets

WebUI.callTestCase(findTestCase('1_Open Lazada Homepage'), [:], FailureHandling.STOP_ON_FAILURE)

TestObject inputSearchQueryBox = findTestObject('Object Repository/2_Body/Homepage/inputSearchQueryBox')

TestObject btnSearch = findTestObject('Object Repository/2_Body/Homepage/btnSearch')

String searchQuery = "${searchQueryKeyword}"

WebUI.setText(inputSearchQueryBox, searchQuery)

WebUI.click(btnSearch)

TestObject titleSearchQueryResultObj = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/1_Header/1_Query Section/h1_titleSearchQueryResult')

TestObject[] elements = [titleSearchQueryResultObj, findTestObject('Object Repository/2_Body/Search Result Page/1_Filter Sections/Harga/btnApplyPriceFilter')
    , findTestObject('Object Repository/2_Body/Search Result Page/1_Filter Sections/Harga/inputPriceMinimum')]

for (TestObject element : elements) {
    WebUI.waitForElementVisible(element, GlobalVariable.waitForTimeout)
}

WebUI.verifyElementText(titleSearchQueryResultObj, searchQuery)

