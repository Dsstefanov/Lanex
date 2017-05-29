package ValidatorLayer;

import java.util.HashMap;

/**
 * Created by Admin on 5/10/2017.
 * Contains only the error messages
 */
public class SavedErrors {
    private HashMap<String, String> errors = new HashMap<>();
    private static SavedErrors instance;

    /**
     *
     * @singleton pattern
     */
    public static SavedErrors getInstance(){
        if (instance == null) {
            instance = new SavedErrors();
        }
        return instance;
    }

    private SavedErrors(){
        setErrors();
    }

    /**
     * Method that set the default error messages and store them as a hashmap
     */
    private void setErrors(){
        errors.put("WRONG_NAME", "The name must contains first and last name in the format: \'Mike Tyson\', first name and last name must consist of only letters with length at least 4 letters and a single free space between them!");
        errors.put("WRONG_ADDRESS","Address must consist of street and number of the street in format: \'Boulevarden 101\', street must contains only letters with length at least 4 and the number must be positive!");
        errors.put("WRONG_EMAIL","Email address must contains only digits, letters, _ and - example: \'user@mail.com\'!");
        errors.put("WRONG_PHONE","Phone number must contains only digits and/or + in format: \'+45112233\'!");
        errors.put("WRONG_CITY","City must contains only letters and to be at least 3 letters long!");
        errors.put("WRONG_CVR", "The CVR must consist of exactly 8 digits and cannot be null value or string!");
        errors.put("WRONG_WORK_ID", "The work ID must consist exactly 9 digits!");
        errors.put("WRONG_CONTRACTOR", "There is no such contractor in the system!");
        errors.put("WRONG_OBJECT_SIZE", "Size must consist of positive numbers only");
        errors.put("WRONG_TYPE", "Type number must be between 1-5");
        errors.put("WRONG_QUANTITIES", "Quantities must be only numbers");
        errors.put("WRONG_OBJECT_HEIGHT", "Height must consist of positive numbers only");
        errors.put("WRONG_OBJECT_WIDTH", "Width must consist of positive numbers only");
        errors.put("WRONG_OBJECT_LENGTH", "Length must consist of positive numbers only");
        errors.put("WRONG_BARCODE", "Product barcode can consist only of numbers");
        errors.put("EMPTY_PRODUCT", "There is no such product in the system!");
        errors.put("WRONG_CURRENT_QUANTITY", "Current quantity must consist of numbers only");
        errors.put("WRONG_MIN_QUANTITY", "Minimum quantity must consist of numbers only");
        errors.put("WRONG_MAX_QUANTITY", "Maximum quantity must consist of numbers only");
        errors.put("WAREHOUSE_NOT_FOUND", "Warehouse with that ID does not exist");
        errors.put("NUMBER_EXCEPTION", "Enter a valid number");
        errors.put("ID_EMPTY", "Select first a warehouse to modify");
        errors.put("WRONG_PRODUCT_NAME", "Invalid product name!");
    }
    public HashMap<String, String> getErrors(){
        return errors;
    }
}
