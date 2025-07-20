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
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import java.io.File as File

Map scrapeValueFromPreviousTest = WebUI.callTestCase(findTestCase('5_Obtain item names'), [('scrapeToPage') : 3], FailureHandling.STOP_ON_FAILURE)

String excelFile = 'Data Files\\searchResult.xlsx'

ExcelKeywords.createExcelFile(excelFile)

File file1 = new File(excelFile)

assert file1.exists() == true

workbook = ExcelKeywords.getWorkbook(excelFile)

sheet0 = ExcelKeywords.getExcelSheet(workbook, 'Sheet0')
scrapeValueFromPreviousTest.putAt("A1", "No")
scrapeValueFromPreviousTest.putAt("B1", "Page")
scrapeValueFromPreviousTest.putAt("C1", "Product Name")
ExcelKeywords.setValueToCellByAddresses(sheet0, scrapeValueFromPreviousTest)

ExcelKeywords.saveWorkbook(excelFile, workbook)
