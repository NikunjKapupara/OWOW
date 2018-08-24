package com.owow.androidapp.utils

class ValidationUtils {

    companion object {
        fun isStringEmpty(fieldValue: String?): Boolean {
            return fieldValue == null || fieldValue.trim { it <= ' ' }.isEmpty()
        }


        //CHECK FOR EMAIL ADDRESS VALIDATION /// allow capital letter email address also
        fun isEmailAddressCorrect(emailAddress: String): Boolean {
            return emailAddress.trim { it <= ' ' }.matches("[a-zA-Z0-9._-]+@[A-Za-z]+\\.+[A-Za-z]+".toRegex())
        }
    }

}