package com.VooTreeVeeVuu.services;

import com.VooTreeVeeVuu.dto.EmailReceiptDTO;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOTP(String email, String otp) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setFrom("dhuyclone2001@gmail.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("OTP VERIFICATION CODE");
            message.setText("Your OTP code: " + otp);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmailReceipt(EmailReceiptDTO dto) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("vootreeveevuu@gmail.com");
            helper.setTo(dto.getCusEmail());
            helper.setSubject("[VooTreeVeeVuu] BOOKING INFORMATION");
            String emailContent =
                    " <div style=\" height: 50px; background-color: blue; justify-content: center; align-items: center; display: flex; color: white;\" >" +
                            "<h1>Booking Information</h1></div>" +
                            "<div><div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Hotel Name:</strong></p>" +
                            "<p>" + dto.getHotelName() + "</p></div>" +
                            "<div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Room Type:</strong></p><p>" + dto.getRoomType() + "</p></div>" +
                            " <div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Check In Time:</strong></p><p>" + dto.getCheckInTime() + "</p></div>" +
                            "<div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Check Out Time:</strong></p><p>" + dto.getCheckOutTime() + "</p></div>" +
                            " <div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            " <p style=\"width: 30%\"><strong>Guest(s)/Room(s):</strong></p><p>" + dto.getNum_of_guests() + " Guest(s)/ " + dto.getNum_of_rooms() + " Room(s)" + "</p></div>" +
                            "<div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Hotel Phone:</strong></p><p>" + dto.getHotelPhoneNum() + "</p></div>" +
                            " <div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Hotel Address:</strong></p><p>" + dto.getAddress() + "</p></div>" +
                            " <div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            " <p style=\"width: 30%\"><strong>From - To:</strong></p>" +
                            " <p style=\"font-weight: bold; color: red\">" + dto.getCheckInDate() + " - " + dto.getCheckOutDate() + "</p></div></div>";
            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEmailToOwner(EmailReceiptDTO dto) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("vootreeveevuu@gmail.com");
            helper.setTo(dto.getOwnerEmail());
            helper.setSubject("[VooTreeVeeVuu] BOOKING INFORMATION");
            String emailContent =
                    " <div style=\" height: 50px; background-color: blue; justify-content: center; align-items: center; display: flex; color: white;\" >" +
                            "<h1>Booking Information</h1></div>" +
                            "<div><div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Hotel Name:</strong></p>" +
                            "<p>" + dto.getHotelName() + "</p></div>" +
                            "<div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Room Type:</strong></p><p>" + dto.getRoomType() + "</p></div>" +
                            " <div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Hotel Address:</strong></p><p>" + dto.getAddress() + "</p></div>" +
                            " <div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            " <p style=\"width: 30%\"><strong>Guest(s)/Room(s):</strong></p><p>" + dto.getNum_of_guests() + " Guest(s)/ " + dto.getNum_of_rooms() + " Room(s)" + "</p></div>" +
                            "<div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            " <p style=\"width: 30%\"><strong>From - To:</strong></p>" +
                            " <p style=\"font-weight: bold; color: red\">" + dto.getCheckInDate() + " - " + dto.getCheckOutDate() + "</p></div>" +
                            " <hr style=\"margin-top: 20px\" />" +
                            " <div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Customer:</strong></p><p>" + dto.getCusFullName() + "</p></div>" +
                            " <div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Customer Phone:</strong></p><p>" + dto.getCusPhoneNum() + "</p></div>" +
                            " <div style=\"display: flex; padding: 5px; margin-bottom: -25px\">" +
                            "<p style=\"width: 30%\"><strong>Customer Email:</strong></p><p>" + dto.getCusEmail() + "</p></div>";
            helper.setText(emailContent, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
