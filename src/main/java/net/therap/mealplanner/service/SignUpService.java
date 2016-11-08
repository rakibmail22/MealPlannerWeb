package net.therap.mealplanner.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.stereotype.Service;

/**
 * @author bashir
 * @since 10/26/16
 */
@Service
public class SignUpService {

    final static Logger log = LogManager.getLogger(SimpleLogger.class);

    public boolean matchPassword(String pass, String verifyPass) {
        if (pass.equals(verifyPass)) {
            return true;
        } else {
            return false;
        }
    }
}
