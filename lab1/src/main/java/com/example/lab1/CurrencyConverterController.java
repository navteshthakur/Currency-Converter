/**Author: Navtesh Thakur
 * Date: 14th January, 2024
 * Application Purpose: This is the controller which contains the required methods for the currency converter.
 * The currency is first converted to USD and then to any other currency. This reduces the lines of code.
 */
package com.example.lab1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
//Creating a class named CurrencyConverterController
public class CurrencyConverterController {
//declaring a variable called usdRate
    private double usdRate;
//encapsulating the variable
    public void setUsdRate(double usdRate) {
        this.usdRate = usdRate;
    }
    public double getUsdRate() {
        return usdRate;
    }
//declaring a variable called finalRate
    private double finalRate;
//encapsulating the variable
    public void setFinalRate(double finalRate) {
        this.finalRate = finalRate;
    }
    public double getFinalRate() {
        return finalRate;
    }
//fxml variable for text-field
    @FXML
    private TextField currency1;

    @FXML
    private TextField currency2;
//combo-box has a drop-down list to display values
    @FXML
    private ComboBox<String> sourceCurrency;

    @FXML
    private ComboBox<String> resultCurrency;
//button will trigger the conversion
    @FXML
    private Button button;
//initializing the values for the combo-box
    @FXML
    protected void initialize() {
        sourceCurrency.getItems().addAll("USD", "EUR", "GBP","INR","CAD");
        resultCurrency.getItems().addAll("USD", "EUR", "GBP","INR","CAD");
        sourceCurrency.setValue("USD");//these are the default value for the first combo-box
        resultCurrency.setValue("Choose result currency");//this will be the default value for the second combo-box
    }
// convert method this called when the button is pressed
    @FXML
    protected void convert(){
        String source= sourceCurrency.getValue();//gets the value for the first currency
        String result= resultCurrency.getValue();//gets the value for the currency the conversion has to be done in.
        try {
            double amount = Double.parseDouble(currency1.getText());//getting the entered amount in the text-field
            double usd =convertToUSD(source,amount);//first change the value to usd
            double converted=finalConversion(result);//change usd to the selected currency
            // Display the converted amount.
            currency2.setText(String.format("%.2f %s", converted, result));
        } catch (NumberFormatException e) {
            // Handle invalid input (non-numeric).
            currency2.setText("Invalid input");
        }
    }
//this method converts the original currency to usd
    @FXML
    private double convertToUSD(String sourceCurrency, double currency1){
//using switch case instead of multiple if else to convert the original currency to usd first
        //here we are using hard coded values which will have a little deviation from actual values
        switch(sourceCurrency){
            case "USD":
                usdRate=currency1*1;
                break;
            case "EUR":
                usdRate=currency1*1.10;
                break;
            case "GBP":
                usdRate=currency1*1.27;
                break;
            case "INR":
                usdRate=currency1*0.012;
                break;
            case "CAD":
                usdRate=currency1*0.75;
                break;
        }
        return usdRate;
    }
    //This converts usd to the other currencies. Since every currency is converted to usd, this reduces the lines of code
    @FXML
    private double finalConversion(String resultCurrency){
        switch(resultCurrency){
            case "USD":
                finalRate=getUsdRate()*1;
                break;
            case "EUR":
                finalRate=getUsdRate()*0.91;
                break;
            case "GBP":
                finalRate=getUsdRate()*0.78;
                break;
            case "INR":
                finalRate=getUsdRate()*82.87;
                break;
            case "CAD":
                finalRate=getUsdRate()*1.34;
                break;
        }
        return finalRate;
    }

}