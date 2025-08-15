package com.ranjithlearners.validation;

import raven.toast.Notifications;

public class Validator {

    public static boolean isEmailValid(String value) {
        if (value.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "Email can't be Empty");
            return false;
        } else if (!value.matches(Validation.EMAIL.Validate())) {
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "Invalid Email");
            return false;
        }
        return true;
    }

    public static boolean isMobileValid(String value) {
        if (value.isBlank()) {
            
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "Mobile can't be Empty");
            return false;
        } else if (!value.matches(Validation.MOBILE.Validate())) {
            
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "Invalid Mobile");
            return false;
        }
        return true;
    }

    public static boolean isPasswordValid(String value) {
        if (value.isBlank()) {
            
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "Password can't be Empty");
            return false;
        } else if (!value.matches(Validation.PASSWORD.Validate())) {
            
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "Password must include the following things. \n"
                            + "At least one lowercase, \n"
                            + "At least one uppercase \n"
                            + "at least one digit \n"
                            + "The Password must be greater than 4 and less than 8 characters");
            return false;
        }
        return true;
    }
    
    public static boolean isNameValid(String value){
        if (value.isBlank()) {
            
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "Name can't be Empty");
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean isNicValid(String value){
        if (value.isBlank()) {
            
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "NIC Number can't be Empty");
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean isDOBValid(java.sql.Date value){
        if (value.toString().isEmpty()) {
            
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "Date of Birth can't be Empty");
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean isAddressvalid(String value){
        if (value.isBlank()) {
            
            Notifications.getInstance().show(Notifications.Type.WARNING,
                    Notifications.Location.TOP_CENTER,
                    2000,
                    "Address can't be Empty");
            return false;
        } else {
            return true;
        }
    }
    
}
