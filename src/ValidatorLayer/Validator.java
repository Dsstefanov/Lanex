package ValidatorLayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 5/10/2017.
 * Validate all the given input with the appropriate regex
 */
public class Validator {

    public static String validateName(String firstLastName) {
        final String regex = "^[a-zA-Z]{4,}\\s[a-zA-Z]{4,}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(firstLastName);

        String result = SavedErrors.getInstance().getErrors().get("WRONG_NAME"); // get and save the specific error
        if (matcher.matches()) {
            return matcher.group(0); // take the value
        } else {
            throw new IllegalArgumentException(result);
        }
    }

    public static String validateAddress(String address) {
        final String regex = "^[a-zA-Z]{3,}\\s[0-9]+$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(address);

        String result = SavedErrors.getInstance().getErrors().get("WRONG_ADDRESS");
        if (matcher.matches()) {
            return matcher.group(0); // take the value
        } else {
            throw new IllegalArgumentException(result);
        }
    }

    public static String validateEmail(String email) {
        final String regex = "^[a-zA-Z0-9_-]+@[a-z]+\\.[a-z]{2,3}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(email);

        String result = SavedErrors.getInstance().getErrors().get("WRONG_EMAIL");
        if (matcher.matches()) {
            return matcher.group(0);// take the value
        } else {
            throw new IllegalArgumentException(result);
        }
    }

    public static String validatePhone(String phone) {
        final String regex = "^\\+[0-9]{8,}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(phone);

        String result = SavedErrors.getInstance().getErrors().get("WRONG_EMAIL");
        if (matcher.matches()) {
            return matcher.group(0); // take the value
        } else {
            throw new IllegalArgumentException(result);
        }
    }

    public static String validateCity(String city) {
        final String regex = "^[a-zA-Z]{3,}$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(city);

        String result = SavedErrors.getInstance().getErrors().get("WRONG_CITY");
        if (matcher.matches()) {
            return matcher.group(0);// take the value
        } else {
            throw new IllegalArgumentException(result);
        }
    }

    public static int validateCVR(int cvr) {
        String result = SavedErrors.getInstance().getErrors().get("WRONG_CVR");

        if (cvr <= 99999999 && cvr >= 10000000) {
            return cvr;// take the value
        } else {
            throw new IllegalArgumentException(result);
        }
    }

    public static int validateWorkId(int work_id) {
        String result = SavedErrors.getInstance().getErrors().get("WRONG_WORK_ID");

        if (work_id <= 999999999 && work_id >= 100000000) {
            return work_id;// take the value
        } else {
            throw new IllegalArgumentException(result);
        }
    }

    public static int validateObjectSize(int size) {
        String result = SavedErrors.getInstance().getErrors().get("WRONG_OBJECT_SIZE");
        if (size > 0) {
            return size;
        } else {
            throw new IllegalArgumentException(result);

        }
    }

    public static int validateType(int type) {
        String result = SavedErrors.getInstance().getErrors().get("WRONG_TYPE");
        if (type > 0 && type < 5) {
            return type;
        } else {
            throw new IllegalArgumentException(result);

        }
    }

    public static int validateQuantities(int quantity) {
        String result = SavedErrors.getInstance().getErrors().get("WRONG_QUANTITIES");
        if (quantity > 0) {
            return quantity;
        } else {
            throw new IllegalArgumentException(result);

        }

    }
}
