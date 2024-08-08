package com.pay.abhay.valid;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pay.abhay.userpack.User;

public class ValidateUser {

    // match the pattern
    private static Matcher matcher;

    // create pattern
    private static Pattern pattern;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /*
     * Length Validation
     */
    public static boolean checkLength(int length, String text, boolean lenghtEquals) {
        if (lenghtEquals) {
            if (text.length() == length && text != null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (text.length() > length && text != null) {
                return true;
            } else {
                return false;
            }
        }
    }

    /*
     * Validate Email ID
     */
    // for email validation we have two classees matcher and pattern
    public static boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /*
     * Verify Pin
     */
    public static boolean verifyPin(int pin, User user) {
        if (pin == user.getAccountPin())
            return true;
        else
            return false;
    }

    /*
     * is null or not
     */
    public static boolean isNotNull(String txt) {
        // ternary operator it used when we want to solve any problem without if else
        return txt != null && txt.trim().length() > 0 ? true : false;
    }

    /*
     * Validate Password with retype password
     */
    public static boolean validatePassword(String pass) {
        return pass != null && pass.length() > 3 ? true : false;
    }

    /*
     * To check spaces
     */
    public static boolean haveSpace(String userName) {
        boolean checkSpace = false;
        int f = 0;
        for (int i = 0; i < userName.length(); i++) {
            if (userName.contains(" ")) {
                f = 1;
                checkSpace = true;
            }
        }
        return checkSpace;
    }

    /*
     * Validate maxMobile Number
     */
    public static boolean validateMaxMobile(String mobile) {
        return mobile != null && mobile.length() > 10 ? true : false;
    }

    /*
     * Validate minMobile Number
     */
    public static boolean validateMinMobile(String mobile) {
        return mobile != null && mobile.length() < 10 ? true : false;
    }

}
