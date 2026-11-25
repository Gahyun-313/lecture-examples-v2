package com.growmighty.lectures.firstday.tangledmonolith.payment.presentation;

import com.growmighty.lectures.firstday.tangledmonolith.common.response.ApiResponse;
import com.growmighty.lectures.firstday.tangledmonolith.payment.application.PaymentService;
import com.growmighty.lectures.firstday.tangledmonolith.payment.application.dto.PaymentInfo;
import com.growmighty.lectures.firstday.tangledmonolith.payment.presentation.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{paymentId}")
    public ApiResponse<PaymentResponse> getPayment(@PathVariable Long paymentId) {
        // 서비스 호출
        PaymentInfo info = paymentService.getPayment(paymentId);
        // PaymentResponse 로 변환
        PaymentResponse response = PaymentResponse.from(info);
        // ApiResponse.ok()로 감싸서 반환
        return ApiResponse.ok(response);
    }
}
