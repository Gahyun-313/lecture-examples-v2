package com.growmighty.lectures.firstday.tangledmonolith.payment.presentation.dto;

import com.growmighty.lectures.firstday.tangledmonolith.payment.application.dto.PaymentInfo;
import com.growmighty.lectures.firstday.tangledmonolith.payment.domain.PaymentStatus;

import java.math.BigDecimal;

public record PaymentResponse(Long id, BigDecimal amount, PaymentStatus status) {

    public static PaymentResponse from(PaymentInfo info) {
        return new PaymentResponse(info.id(), info.amount(), info.status());
    }
}