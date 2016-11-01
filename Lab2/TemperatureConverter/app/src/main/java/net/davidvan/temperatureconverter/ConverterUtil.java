package net.davidvan.temperatureconverter;

/**
 * Created by David on 9/9/2016.
 */
public class ConverterUtil {

    public static float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

    public static float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }

}
