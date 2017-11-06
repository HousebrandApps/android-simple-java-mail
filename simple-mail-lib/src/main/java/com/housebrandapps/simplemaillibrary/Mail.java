package com.housebrandapps.simplemaillibrary;

import android.support.annotation.NonNull;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;

public class Mail {
    private String fromEmailAddress;
    private String fromEmailPassword;
    private String fromEmailAttachmentPath;
    private String fromEmailSubject;
    private String fromEmailMessage;
    private String toEmailAddress;
    private Session session;

    private Mail(MailConfigBuilder config) {
        this.toEmailAddress = config.toEmailAddress;
        this.fromEmailAddress = config.fromEmailAddress;
        this.fromEmailMessage = config.fromEmailMessage;
        this.fromEmailSubject = config.fromEmailSubject;
        this.fromEmailPassword = config.fromEmailPassword;
        this.fromEmailAttachmentPath = config.fromEmailAttachmentPath;
        this.session = config.session;
    }

    public Session getSession() {
        return session;
    }

    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    public String getFromEmailPassword() {
        return fromEmailPassword;
    }

    public String getFromEmailAttachmentPath() {
        return fromEmailAttachmentPath;
    }

    public String getFromEmailSubject() {
        return fromEmailSubject;
    }

    public String getFromEmailMessage() {
        return fromEmailMessage;
    }

    public String getToEmailAddress() {
        return toEmailAddress;
    }

    public static class MailConfigBuilder {
        private String fromEmailAddress;
        private String fromEmailPassword;
        private String fromEmailAttachmentPath;
        private String fromEmailSubject;
        private String fromEmailMessage;
        private String toEmailAddress;

        private Session session;
        private Authenticator sessionAuthenticator;
        private Properties properties;

        public MailConfigBuilder() {
            properties = new Properties();
        }

        public MailConfigBuilder fromEmailAddress(String fromEmailAddress) {
            this.fromEmailAddress = fromEmailAddress;
            return this;
        }

        public MailConfigBuilder fromEmailPassword(String fromEmailPassword) {
            this.fromEmailPassword = fromEmailPassword;
            return this;
        }


        public MailConfigBuilder fromEmailSubject(@NonNull String fromEmailSubject) {
            this.fromEmailSubject = fromEmailSubject;
            return this;
        }

        public MailConfigBuilder fromEmailMessage(@NonNull String fromEmailMessage) {
            this.fromEmailMessage = fromEmailMessage;
            return this;
        }


        public MailConfigBuilder fromEmailAttachmentPath(@NonNull String fromEmailAttachmentPath) {
            this.fromEmailAttachmentPath = fromEmailAttachmentPath;
            return this;
        }

        public MailConfigBuilder sessionAuthenticator(Authenticator sessionAuthenticator) {
            this.sessionAuthenticator = sessionAuthenticator;
            return this;
        }

        public MailConfigBuilder toEmailAddress(String toEmailAddress) {
            this.toEmailAddress = toEmailAddress;
            return this;
        }

        public MailConfigBuilder hostAddress(String hostAddress) {
            properties.put("mail.smtp.host", hostAddress);
            return this;
        }

        public MailConfigBuilder serverPort(String serverPort) {
            properties.put("mail.smtp.port", serverPort);
            return this;
        }

        public MailConfigBuilder serverSocketFactoryPort(String serverSocketFactoryPort) {
            properties.put("mail.smtp.socketFactory.port", serverSocketFactoryPort);
            return this;
        }

        public MailConfigBuilder serverSocketFactoryClass(String serverSocketFactoryClass) {
            properties.put("mail.smtp.socketFactory.class", serverSocketFactoryClass);
            return this;
        }

        public MailConfigBuilder serverAuthSetting(boolean serverAuthSetting) {
            properties.put("mail.smtp.auth", serverAuthSetting);
            return this;
        }

        public Mail createConfig() {
            this.session = Session.getInstance(properties, sessionAuthenticator);
            return new Mail(this);
        }
    }
}
