package com.javaweb.model.security;

import com.javaweb.exception.ApplicationException;
import com.javaweb.i18n.ErrorMessageKeys;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Andrii Chernysh on 12-Feb-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class BCryptEncryptor implements Securable {
    private static final int WORKLOAD = 12;
    private static final String BCRYPT_PREFIX = "$2a$";
    private static final String INVALID_HASH_PROVIDED_FOR_COMPARISON_LOG_MSG =
            "Invalid hash provided for comparison";

    @Override
    public String encryptPassword(String password) {
        String salt = BCrypt.gensalt(WORKLOAD);
        return BCrypt.hashpw(password,salt);
    }

    @Override
    public boolean checkPassword(String password, String hash) {
        if(null == hash || !hash.startsWith(BCRYPT_PREFIX))
            throw new ApplicationException()
                    .addLogMessage(INVALID_HASH_PROVIDED_FOR_COMPARISON_LOG_MSG)
                    .addMessage(ErrorMessageKeys.ERROR_ILLEGAL_PASSWORD_DECRYPTED);

        boolean isCorrect = BCrypt.checkpw(password,hash);
        return isCorrect;
    }
}
