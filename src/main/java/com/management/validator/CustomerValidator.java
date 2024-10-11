package com.management.validator;

import com.management.utils.ErrorLogger;
import com.management.model.Customer;
import java.util.Set;
import java.util.regex.Pattern;

public class CustomerValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[^@ ]+@[^@ ]+\\.[^@ ]+");
    private static final Pattern PHONE_PATTERN = Pattern.compile("[0-9\\-\\+]{9,15}");

    public boolean validate(Customer customer, ErrorLogger errorLogger, Set<String> existingIds, Set<String> existingEmails, Set<String> existingPhones) {
        boolean isValid = true;
        String id = customer.getId();
        String name = customer.getName();
        String email = customer.getEmail();
        String phoneNumber = customer.getPhoneNumber();

        if (id == null || id.isEmpty()) {
            errorLogger.logError("Customer ID is required.");
            isValid = false;
        } else if (existingIds.contains(id)) {
            errorLogger.logError("Duplicate Customer ID: " + id);
            isValid = false;
        } else {
            existingIds.add(id);
        }

        if (name == null || name.isEmpty()) {
            errorLogger.logError("Customer Name cannot be empty. ID: " + id);
            isValid = false;
        }

        if (email == null || !isValidEmail(email)) {
            errorLogger.logError("Invalid Email format. ID: " + id);
            isValid = false;
        } else if (existingEmails.contains(email)) {
            errorLogger.logError("Duplicate Email: " + email);
            isValid = false;
        } else {
            existingEmails.add(email);
        }

        if (phoneNumber == null || !isValidPhoneNumber(phoneNumber)) {
            errorLogger.logError("Invalid PhoneNumber format. ID: " + id);
            isValid = false;
        } else if (existingPhones.contains(phoneNumber)) {
            errorLogger.logError("Duplicate PhoneNumber: " + phoneNumber);
            isValid = false;
        } else {
            existingPhones.add(phoneNumber);
        }

        return isValid;
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

}