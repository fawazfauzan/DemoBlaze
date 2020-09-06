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

WebUI.callTestCase(findTestCase('Web Demoblaze'), [:], FailureHandling.STOP_ON_FAILURE)

if (WebUI.verifyElementNotPresent(findTestObject('Home Page/chooseProduct', [('productName') : productName]), 2, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Object Repository/Home Page/buttonNextProduct'))

    productPrice = WebUI.getText(findTestObject('Home Page/chooseProductPrice', [('productName') : productName]))

    productDescription = WebUI.getText(findTestObject('Home Page/chooseProductDescription', [('productName') : productName]))

    WebUI.click(findTestObject('Home Page/chooseProduct', [('productName') : productName]))
} else {
    productPrice = WebUI.getText(findTestObject('Home Page/chooseProductPrice', [('productName') : productName]))

    productDescription = WebUI.getText(findTestObject('Home Page/chooseProductDescription', [('productName') : productName]))

    WebUI.click(findTestObject('Home Page/chooseProduct', [('productName') : productName]))
}

WebUI.verifyElementText(findTestObject('Home Page/Product Detail Page/labelProductName'), productName)

WebUI.verifyTextPresent(productPrice, false)

WebUI.verifyTextPresent(productDescription, false)

WebUI.click(findTestObject('Home Page/Product Detail Page/buttonAddToCart'))

WebUI.waitForAlert(0)

def alertText = WebUI.getAlertText()

WebUI.verifyEqual(alertText, 'Product added')

WebUI.acceptAlert()

WebUI.click(findTestObject('Home Page/menuCart'))

WebUI.waitForElementPresent(findTestObject('Cart Page/labelTitleProduct', [('productName') : productName]), 0)

WebUI.verifyElementPresent(findTestObject('Cart Page/labelTitleProduct', [('productName') : productName]), 0)

WebUI.verifyElementPresent(findTestObject('Cart Page/labelProductPrice', [('productPrice') : productPrice.toString().minus(
                '$')]), 0)

WebUI.verifyElementText(findTestObject('Object Repository/Cart Page/labelTotalPrice'), productPrice.toString().minus('$'))

WebUI.click(findTestObject('Cart Page/buttonPlaceOrder'))

WebUI.verifyElementPresent(findTestObject('Cart Page/labelPlaceOrderForm'), 0)

def totalPriceText = WebUI.getText(findTestObject('Cart Page/labelTotalPricePlaceOrderForm'), FailureHandling.STOP_ON_FAILURE)

def totalPrice = totalPriceText.minus('Total:').trim()

WebUI.verifyEqual(totalPrice, productPrice.toString().minus('$'), FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Cart Page/inputName'), name)

WebUI.setText(findTestObject('Cart Page/inputCountry'), country)

WebUI.setText(findTestObject('Cart Page/inputCity'), city)

WebUI.setText(findTestObject('Cart Page/inputCreditCard'), creditCard)

Date now = new Date()

def month = now.format('MMMM')

def year = now.format('yyyy')

WebUI.setText(findTestObject('Cart Page/inputMonth'), month)

WebUI.setText(findTestObject('Cart Page/inputYear'), year)

WebUI.click(findTestObject('Cart Page/buttonPurchase'))

WebUI.waitForAlert(0, FailureHandling.STOP_ON_FAILURE)

alertText = WebUI.getAlertText(FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(alertText, 'Please fill out Name and Creditcard.')

WebUI.acceptAlert()

