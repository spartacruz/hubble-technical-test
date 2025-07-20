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

WebUI.callTestCase(findTestCase('2_Search a Product'), [('searchQueryKeyword') : 'Logitech Keyboard'], FailureHandling.STOP_ON_FAILURE)

TestObject inputPriceMinimum = findTestObject('Object Repository/2_Body/Search Result Page/1_Filter Sections/Harga/inputPriceMinimum')

TestObject inputPriceMaximum = findTestObject('Object Repository/2_Body/Search Result Page/1_Filter Sections/Harga/inputPriceMaximal')

TestObject btnApplyPriceFilter = findTestObject('Object Repository/2_Body/Search Result Page/1_Filter Sections/Harga/btnApplyPriceFilter')

TestObject titleSearchQueryResultObj = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/1_Header/1_Query Section/h1_titleSearchQueryResult')

TestObject txtDikirimDariObj = findTestObject('Object Repository/2_Body/Search Result Page/1_Filter Sections/Dikirim Dari/div_dikirimDari')

WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)", null)

WebUI.scrollToElement(btnApplyPriceFilter, 0)

WebUI.setText(inputPriceMinimum, "${minimumPrice}")

WebUI.setText(inputPriceMaximum, "${maximumPrice}")

WebUI.scrollToElement(txtDikirimDariObj, 0)

WebUI.click(btnApplyPriceFilter)

WebUI.scrollToPosition(0, 0)

TestObject strDifilterBerdasarkan = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/1_Header/1_Query Section/span_diFilterBerdasarkan')

TestObject appliedFilterPills = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/1_Header/1_Query Section/span_appliedFilterPills')

TestObject[] elements = [titleSearchQueryResultObj, strDifilterBerdasarkan, appliedFilterPills]

for (TestObject element : elements) {
    WebUI.waitForElementVisible(element, GlobalVariable.waitForTimeout)
}

CustomKeywords.'test.CustomYuri.waitForElementSpinnersNotPresent'()

String STR_DIFILTERBERDASARKAN_TEXT = 'Difilter berdasarkan:'
String expectedHargaPill = "Harga: ${minimumPrice}-${maximumPrice}"

WebUI.verifyElementText(strDifilterBerdasarkan, STR_DIFILTERBERDASARKAN_TEXT)
WebUI.verifyElementText(appliedFilterPills, expectedHargaPill)
