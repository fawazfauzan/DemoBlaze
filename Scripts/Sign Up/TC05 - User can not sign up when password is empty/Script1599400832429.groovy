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
import java.time.*

WebUI.callTestCase(findTestCase('Web Demoblaze'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Home Page/menuSignUp'))

LocalDateTime dt = LocalDateTime.now()

WebUI.waitForElementPresent(findTestObject('Sign Up Page/labelSignUp'), 0)

WebUI.setText(findTestObject('Sign Up Page/inputUsername'), username + dt.toString())

WebUI.click(findTestObject('Sign Up Page/buttonSignUp'))

WebUI.waitForAlert(0)

def alertText = WebUI.getAlertText()

WebUI.verifyEqual(alertText, 'Please fill out Username and Password.')

WebUI.acceptAlert()
