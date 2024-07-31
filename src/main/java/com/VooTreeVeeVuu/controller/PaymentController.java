package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.config.VNPayConfig;
import com.VooTreeVeeVuu.domain.entity.Booking;
import com.VooTreeVeeVuu.domain.entity.User;
import com.VooTreeVeeVuu.domain.repository.UserRepository;
import com.VooTreeVeeVuu.services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public Map<String, String> createPayment(@RequestParam String amount,
                                             @RequestParam(required = false) String bankCode,
                                             @RequestParam(required = false) String language,
                                             @RequestParam Long bookingId,
                                             @RequestParam Long userId,
                                             HttpServletRequest req) throws Exception {
        return paymentService.createPayment(amount, bankCode, language, bookingId, userId, req);
    }

    @PostMapping("/save-payment")
    public Map<String, String> createPayment(@RequestParam String amount,
                                             @RequestParam Long bookingId,
                                             @RequestParam Long userId,
                                             HttpServletRequest req) throws Exception {
        Booking booking = paymentService.updateBookingStatus(bookingId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        long amountValue = Integer.parseInt(amount) * 100L;
        Date createDate = new Date(); // or fetch from your specific logic

        paymentService.savePayment(vnp_TxnRef, amountValue, createDate, booking, user);

        // Build and return response as needed
        // Example response map
        Map<String, String> response = Map.of("code", "00", "message", "success");
        return response;
    }
}
