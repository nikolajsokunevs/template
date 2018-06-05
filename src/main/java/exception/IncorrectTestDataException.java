package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncorrectTestDataException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(IncorrectTestDataException.class);

    public IncorrectTestDataException() {
        logger.error("Incorrect test data exists, please check");
    }

    public IncorrectTestDataException(String message)
    {
        super(message);
        logger.error(message);
    }
}
