package ValidatorLayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 5/10/2017.
 * Validate all the give input with the appropriate regex
 */
public class Validator {

    SavedErrors errors = SavedErrors.getInstance();


    public String validateName(String firstLastName) {
        final String regex = "^[a-zA-Z]{4,}\\s[a-zA-Z]{4,}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(firstLastName);

        String result = errors.getErrors().get("WRONG_NAME");
        if (matcher.matches()) {
            return null;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public  String validateAddress(String address) {
        final String regex = "^[a-zA-Z]{3,}\\s[0-9]+$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(address);

        String result = errors.getErrors().get("WRONG_ADDRESS");
        if (matcher.matches()) {
            return null;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public  String validateEmail(String email) {
        final String regex = "^[a-zA-Z0-9_-]+@[a-z]+\\.[a-z]{2,3}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(email);

        String result = errors.getErrors().get("WRONG_EMAIL");
        if (matcher.matches()) {
            return null;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public String validatePhone(String phone) {
        final String regex = "^\\+[0-9]{8,}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(phone);

        String result = errors.getErrors().get("WRONG_EMAIL");
        if (matcher.matches()) {
            return null;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public String validateCity(String city) {
        final String regex = "^[a-zA-Z]{3,}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(city);

        String result = errors.getErrors().get("WRONG_CITY");
        if (matcher.matches()) {
            return null;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
    public int validateCVR(int cvr) {
        String result = errors.getErrors().get("WRONG_CVR");

        if (cvr <= 99999999 && cvr >= 10000000) {
            return 0;
        }
        else {
            throw new IllegalArgumentException(result);
        }
    }
}
