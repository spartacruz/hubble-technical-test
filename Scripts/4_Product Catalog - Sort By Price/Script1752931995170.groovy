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

WebUI.callTestCase(findTestCase('3_Product Catalog - Filter By'), [('minimumPrice') : '100000', ('maximumPrice') : '3000000'], 
    FailureHandling.STOP_ON_FAILURE)

TestObject selectDropdownObj = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/1_Header/2_Sort Dropdown/div_selectDropdown')

TestObject optionSelect;
if ("${stringFilterBy}" == 'Harga Rendah ke Tinggi') {
	optionSelect = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/1_Header/2_Sort Dropdown/option_hargaRendahKeTinggi')
} else if ("${stringFilterBy}" == 'Harga Tinggi ke Rendah') {
	optionSelect = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/1_Header/2_Sort Dropdown/option_hargaTinggiKeRendah')
} else if ("${stringFilterBy}" == 'Terkait') {
	optionSelect = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/1_Header/2_Sort Dropdown/option_Terkait')
}

WebUI.click(selectDropdownObj)

WebUI.click(optionSelect)

TestObject[] spinners = [findTestObject('Object Repository/0_reusable/loadingSpinnerTopRight'),
	findTestObject('Object Repository/0_reusable/loadingBarTop')
	]

for (TestObject element : spinners) {
	WebUI.waitForElementNotPresent(element, GlobalVariable.waitForTimeout)
}

String selectedSortDropdown = WebUI.getText(findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/1_Header/2_Sort Dropdown/div_selectDropdown'))
String expectedDropdown = "${stringFilterBy}";

WebUI.verifyElementText(selectDropdownObj, expectedDropdown)
