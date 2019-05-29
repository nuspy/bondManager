package io.bounds4all.bondsmanager.business_logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SendConfirmationEmail {

    Logger logger = LoggerFactory.getLogger(SendConfirmationEmail.class);

    public boolean sendEmail(String email){
        logger.info("Email sent for confirmation");
        return confirmationReceived(email);
    }

    public boolean confirmationReceived (String email){
        logger.info("Confirmation received");
        //toDo: Change the status on Client Record
        return true;
    }

}
