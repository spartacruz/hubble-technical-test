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
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.WebElement


WebUI.callTestCase(findTestCase('4_Product Catalog - Sort By Price'), [:], FailureHandling.STOP_ON_FAILURE)
TestObject groupPaginationElement = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/3_Footer/groupPaginationElement')
TestObject groupProductNames = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/2_Body/groupProductNames')
TestObject btnNextPage = findTestObject('Object Repository/2_Body/Search Result Page/2_Product Sections/3_Footer/btnNextPage')

int toPage
try {
	toPage = "${scrapeToPage}".toInteger();
	
	if (toPage < 1) {
		System.out.println("Unexpected scrapeToPage format, must equal or greater than 1")
		toPage = 1
	}
} catch (NumberFormatException e) {
	System.out.println("Unexpected scrapeToPage format, must be an int")
	System.out.println("Default scrapeToPage = 1")
	toPage = 1
}
Map scrapeValue = new HashMap<>()
Number startExcelRow = 2

for (int i = 1; i <= toPage; i++) {
	CustomKeywords.'test.CustomYuri.ScrollUntilElementPresent'(groupPaginationElement)
	WebUI.scrollToPosition(0, 0)
	
	List<WebElement> elements = WebUI.findWebElements(groupProductNames, GlobalVariable.waitForTimeout)
	for (int j = 0;  j < elements.size(); j++) {
		scrapeValue.putAt("A${startExcelRow}", "${startExcelRow - 1}")
		scrapeValue.putAt("B${startExcelRow}", "Page_${i}")
		scrapeValue.putAt("C${startExcelRow}", elements.get(j).getText())
		System.out.println("C${startExcelRow} - " + elements.get(j).getText())
		startExcelRow++
	}
	
	if (i != toPage) {
		WebUI.sendKeys(btnNextPage, Keys.chord(Keys.ARROW_DOWN))
		WebUI.sendKeys(btnNextPage, Keys.chord(Keys.ARROW_DOWN))
		WebUI.click(btnNextPage);
		
		CustomKeywords.'test.CustomYuri.waitForElementSpinnersNotPresent'()
	}
}

//for (String name: scrapeValue.keySet()) {
//	String key = name.toString();
//	String value = scrapeValue.get(name).toString();
//	System.out.println(key + " " + value);
//}

return scrapeValue;