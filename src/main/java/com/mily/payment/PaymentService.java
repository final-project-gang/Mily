package com.mily.payment;

import com.mily.base.rsData.RsData;
import com.mily.user.MilyUser;
import com.mily.user.MilyUserRepository;
import com.mily.user.MilyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final MilyUserService milyUserService;
    private final PaymentRepository paymentRepository;
    private final MilyUserRepository milyUserRepository;

    public RsData<Payment> doPayment (String orderId, MilyUser milyUser, Long amount) {
        LocalDateTime now = LocalDateTime.now();

        Payment payment = Payment.builder()
                .orderId(orderId)
                .orderDate(now)
                .orderName("200")
                .customerName(milyUser)
                .amount(amount)
                .build();

        payment = paymentRepository.save(payment);
        return RsData.of("S-1", "결제 성공", payment);
    }
}