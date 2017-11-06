package com.housebrandapps.simplemaillibrary;

public interface MailListener {
    void startMail();
    void onEmailSuccess();
    void onEmailFail(Exception e);
}
