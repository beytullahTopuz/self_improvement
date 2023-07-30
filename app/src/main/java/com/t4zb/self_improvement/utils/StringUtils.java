package com.t4zb.self_improvement.utils;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean registerValidation(String userName, String email, String password) {

        if (userName == null || userName.equals("") || email == null || email.equals("") || password == null || password.equals(""))
            return false;

        return isValidEmail(email);
    }

    public static boolean loginValidation(String mail, String pwd) {

        if (mail == null || mail.equals("") || pwd == null || pwd.equals(""))
            return false;
        return isValidEmail(mail);
    }

    /**
     * Function: isValidEmail()
     */
    public static boolean isValidEmail(String email) {

        if (email == null || email.isEmpty())
            return false;

        String regex = "^(.+)@(.+)$";
        return Pattern.matches(regex, email);
    }
}
