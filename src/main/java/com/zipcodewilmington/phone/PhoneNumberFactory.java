package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;
import com.zipcodewilmington.tools.RandomNumberFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */
    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) {
        PhoneNumber[] phoneNumbers = new PhoneNumber[phoneNumberCount];
        for (int i = 0; i < phoneNumberCount; i++) {
            PhoneNumber phoneNumber = createRandomPhoneNumber();
            phoneNumbers[i] = phoneNumber;
        }
        return phoneNumbers;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber(){

        return createPhoneNumberSafely(RandomNumberFactory.createInteger(100,999), RandomNumberFactory.createInteger(100,999), RandomNumberFactory.createInteger(1000,9999));
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(int areaCode, int centralOfficeCode, int phoneLineCode) {
        try {
            if(checkNumberSize(areaCode, 3) && checkNumberSize(centralOfficeCode, 3) && checkNumberSize(phoneLineCode, 4)) {
                return createPhoneNumber("(" + areaCode + ")-" + centralOfficeCode + "-" + phoneLineCode);
            }
        } catch (InvalidPhoneNumberFormatException e) {
            e.printStackTrace();
            System.out.println("(" + areaCode + ")-" + centralOfficeCode + "-" + phoneLineCode + " is not a valid phone number");
            return null;
        }
        return null;

    }

    public static boolean checkNumberSize(int number, int expectedLength){
        int actualLength = 0;
        while (number > 0) {
            actualLength++;
            number = number / 10;
        }
        return actualLength == expectedLength;
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException{
        System.out.println("Attempting to create a new PhoneNumber object with a value of " + phoneNumberString);
        PhoneNumber phoneNumber = new PhoneNumber(phoneNumberString);
        return phoneNumber;
    }
}
