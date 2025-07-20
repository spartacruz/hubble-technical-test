package test
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By


import java.awt.Robot;
import java.awt.event.InputEvent;

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import com.kms.katalon.keyword.excel.*

import org.apache.poi.hssf.usermodel.HSSFWorkbook

class CustomYuri {
	@Keyword
	def waitForElementSpinnersNotPresent() {
		TestObject[] spinners = [
			findTestObject('Object Repository/0_reusable/loadingSpinnerTopRight'),
			findTestObject('Object Repository/0_reusable/loadingBarTop'),
			findTestObject('Object Repository/0_reusable/loadingSpinnerMidProduct')
		]

		for (TestObject element : spinners) {
			WebUI.waitForElementNotPresent(element, GlobalVariable.waitForTimeout)
		}
	}


	/**
	 * Credit to mujahed.abuabdo, Katalon community member
	 * With some modify from Yuri, to match with personal usecase
	 */
	@Keyword
	def ScrollUntilElementPresent(TestObject toObject) {
		def b = 1
		for (def i = 0; i <b; i++) {

			def Status = WebUI.verifyElementPresent(toObject, 0, FailureHandling.OPTIONAL)
			if (Status == false ) {
				Robot robot = new Robot();
				robot.mouseWheel(8)
				b++
			}
		}

		WebUI.scrollToElement(toObject, 0)
		WebUI.mouseOver(toObject)
	}
}