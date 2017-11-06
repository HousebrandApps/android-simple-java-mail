package com.housebrandapps.internalemailsender;

import android.databinding.BaseObservable;

import com.housebrandapps.simplemaillibrary.Mail;
import com.housebrandapps.simplemaillibrary.MailTask;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class EmailSenderViewModel extends BaseObservable {

    EmailSenderViewModel(MailTask task) {
        String fromEmailAddress = "mobile.cartrack@gmail.com";
        String fromEmailPassword = "0n3tr@cK";

        task.execute(
                new Mail.MailConfigBuilder()
                        .hostAddress("smtp.gmail.com")
                        .toEmailAddress("natie.klopper@cartrack.com")
                        .fromEmailAddress(fromEmailAddress)
                        .fromEmailPassword(fromEmailPassword)
                        .fromEmailSubject("Testing a mail thingy subject")
                        .fromEmailMessage("Testing this email sender thingy message!")
                        .serverPort("467")
                        .sessionAuthenticator(new GMailAuthenticator(fromEmailAddress, fromEmailPassword))
                        .createConfig()
        );
    }

    private class GMailAuthenticator extends Authenticator {
        private String user;
        private String pw;

        GMailAuthenticator(String username, String password) {
            super();
            this.user = username;
            this.pw = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pw);
        }
    }
}
