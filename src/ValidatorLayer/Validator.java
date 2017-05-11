package ValidatorLayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 5/10/2017.
 */
public class Validator {

    SavedErrors errors = SavedErrors.getInstance();


    public boolean validateName(String firstLastName) {
        final String regex = "^[a-zA-Z]{4,}\\s[a-zA-Z]{4,}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(firstLastName);

        String result = errors.getErrors().get("WRONG_NAME");
        if (matcher.matches()) {
            return true;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public  boolean validateAddress(String address) {
        final String regex = "^[a-zA-Z]{3,}\\s[0-9]+$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(address);

        String result = errors.getErrors().get("WRONG_ADDRESS");
        if (matcher.matches()) {
            return true;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public  boolean validateEmail(String email) {
        final String regex = "^[a-zA-Z0-9_-]+@[a-z]+\\.[a-z]{2,3}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(email);

        String result = errors.getErrors().get("WRONG_EMAIL");
        if (matcher.matches()) {
            return true;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public boolean validatePhone(String phone) {
        final String regex = "^\\+[0-9]{8,}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(phone);

        String result = errors.getErrors().get("WRONG_EMAIL");
        if (matcher.matches()) {
            return true;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public boolean validateCity(String city) {
        final String regex = "^[a-zA-Z]{3,}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(city);

        String result = errors.getErrors().get("WRONG_CITY");
        if (matcher.matches()) {
            return true;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public boolean validateCVR(int cvr) {
        String result = errors.getErrors().get("WRONG_CVR");

        if (cvr <= 99999999 && cvr >= 10000000) {
            return true;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
}
