package com.growmighty.lectures.firstday.tangledmonolith.payment.application;

import com.growmighty.lectures.firstday.tangledmonolith.payment.application.dto.PaymentInfo;
import com.growmighty.lectures.firstday.tangledmonolith.payment.domain.Payment;
import com.growmighty.lectures.firstday.tangledmonolith.payment.domain.PaymentRepository;
import com.growmighty.lectures.firstday.tangledmonolith.payment.domain.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public Payment pay(BigDecimal totalAmount) {
        Payment payment = Payment.ready(totalAmount);
        payment.changeStatus(PaymentStatus.PAID);
        paymentRepository.save(payment);
        return payment;
    }

    @Transactional(readOnly = true)
    public PaymentInfo getPayment(Long paymentId) {
        // paymentRepository 에서 paymentId로 찾는다.
        Payment payment = paymentRepository.findById(paymentId)
                // 없으면 예외 던진다.
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 결제ID입니다. : " + paymentId));

        // PaymentInfo 로 변환 및 반환
        return PaymentInfo.from(payment);
    }
}
