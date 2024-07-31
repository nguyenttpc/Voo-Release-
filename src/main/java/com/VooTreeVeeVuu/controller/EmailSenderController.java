package com.VooTreeVeeVuu.controller;


import com.VooTreeVeeVuu.dto.EmailReceiptDTO;
import com.VooTreeVeeVuu.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/email-sender")
public class EmailSenderController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-receipt")
    public void sendReceipt(@RequestBody EmailReceiptDTO emailReceiptDTO) {
        emailService.sendEmailReceipt(emailReceiptDTO);
    }

    @PostMapping("/send-info")
    public void sendInfo(@RequestBody EmailReceiptDTO emailReceiptDTO) {
        emailService.sendEmailToOwner(emailReceiptDTO);
    }
}
