package com.ranjithlearners.validation;

public enum Validation {
    EMAIL() {
        @Override
        public String Validate() {
            return "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        }
    },
    MOBILE() {
        @Override
        public String Validate() {
            return "^(0{1})(7{1})([0|1|2|4|5|6|7|8]{1})([0-9]{7})";
        }
    },
    PASSWORD() {
        @Override
        public String Validate() {
            return "^.*(?=.{4,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$";
        }
    };
//private validation    

    public String Validate() {
        return "";
    }
;
}
//public static final Validation VALIDATE_EMAIL;
