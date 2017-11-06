package com.housebrandapps.simplemaillibrary;

import android.os.AsyncTask;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class MailTask extends AsyncTask<Mail, Void, Exception> {

    private MailListener listener;

    public MailTask(MailListener listener) {
        this.listener = listener;
    }

    private MailTask() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (listener != null)
            listener.startMail();
    }

    @Override
    protected Exception doInBackground(Mail... configs) {
        try {
            Mail config = configs[0];
            MimeMessage mm = new MimeMessage(config.getSession());
            
            if (config.getFromEmailAttachmentPath() != null) {
                Multipart multipart = new MimeMultipart();
                BodyPart messageBodyPart = new MimeBodyPart();
                File downloadDir = new File(config.getFromEmailAttachmentPath());
                DataSource source = new FileDataSource(downloadDir);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(downloadDir.getName());
                multipart.addBodyPart(messageBodyPart);
                mm.setContent(multipart);
            } else {
                String finalString = "";
                DataHandler handler = new DataHandler(new ByteArrayDataSource(finalString.getBytes(), "text/plain"));
                mm.setDataHandler(handler);
            }

            mm.setFrom(new InternetAddress(config.getFromEmailAddress()));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(config.getToEmailAddress()));
            mm.setSubject(config.getFromEmailSubject());
            mm.setText(config.getFromEmailMessage());

            Transport.send(mm);
        } catch (Exception e) {
            return e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Exception e) {
        super.onPostExecute(e);
        if (listener != null) {
            if (e == null) {
                listener.onEmailSuccess();
            } else {
                listener.onEmailFail(e);
            }
        }
    }
}
