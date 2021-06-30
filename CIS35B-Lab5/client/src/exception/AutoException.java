package exception;

import model.Automobile;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class AutoException extends Exception {
    // Error number enumeration.
    public enum ErrorNumber {
        INVALID_BASE_PRICE, MISSING_OPTIONSET_NAME, MISSING_OPTIONSET_DATA, MISSING_OPTION_NAME, MISSING_OPTION_PRICE, MISSING_FILE_NAME
    }

    // Error messages corresponding to the above error numbers.
    private String[] strArray = { "Automobile base price is invalid",
            "OptionSet name is missing", "OptionSet data is missing",
            "Option name is missing", "Option price is invalid",
            "File name is missing" };

    private ErrorNumber errorNumber;

    // default constructor
    public AutoException() {
        super();
        printProblem();
    }

    // parameterized constructor
    public AutoException(ErrorNumber errNo) {
        super();
        this.errorNumber = errNo;
        printProblem();
    }

    public AutoException(int errNum) {
        super();
        this.errorNumber = ErrorNumber.values()[errNum];
        printProblem();
    }

    public ErrorNumber getErrorNumber() {
        return errorNumber;
    }

    public void setErrorno(ErrorNumber errNo) {
        this.errorNumber = errNo;
    }

    public void printProblem() {
        System.out.println("AutoException Error No: " + this.errorNumber
                + ", Error Message: " + strArray[errorNumber.ordinal()]);
    }

    // logToFile() appends an error log to the given file.
    public void logToFile(String logFile) {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        try {
            // Write output to disk
            FileOutputStream file = new FileOutputStream(logFile, true);
            BufferedOutputStream buff = new BufferedOutputStream(file);
            DataOutputStream data = new DataOutputStream(buff);
            data.writeChars("Timestamp: " + ts + " Error No: " + errorNumber.ordinal()
                    + " Error Message: " + strArray[errorNumber.ordinal()] + "\n");
            data.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }

    // fix() creates an object of Fix1to6 to fix the current error. Returns the
    // fixed Automobile object.
    public Automobile fix() {
        Automobile autoObj = null;
        Fix1to6 f1 = new Fix1to6();

        switch (errorNumber) {
            case INVALID_BASE_PRICE:
                return f1.invalidFixBasePrice();

            case MISSING_OPTIONSET_NAME:
                return f1.missingFixOptionSetName();

            case MISSING_OPTIONSET_DATA:
                return f1.missingOptionSetData();

            case MISSING_OPTION_NAME:
                return f1.missingOptionName();

            case MISSING_OPTION_PRICE:
                return f1.missingOptionPrice();

            case MISSING_FILE_NAME:
                return f1.missingFileName();

            default:
                System.out.println("Unexpected error");
                System.exit(0);
        }
        return null;
    }
}
