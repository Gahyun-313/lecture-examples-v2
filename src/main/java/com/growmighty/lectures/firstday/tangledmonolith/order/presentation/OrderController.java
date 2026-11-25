package com.growmighty.lectures.firstday.tangledmonolith.order.presentation;

import com.growmighty.lectures.firstday.tangledmonolith.common.response.ApiResponse;
import com.growmighty.lectures.firstday.tangledmonolith.order.application.OrderService;
import com.growmighty.lectures.firstday.tangledmonolith.order.application.dto.OrderConsistencyView;
import com.growmighty.lectures.firstday.tangledmonolith.order.application.dto.OrderResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ApiResponse<List<OrderResult>> getOrders() {
        return ApiResponse.ok(orderService.getOrders());
    }

    @PostMapping
    public ApiResponse<OrderResult> placeOrder(@RequestBody OrderCreationRequest request) {
        return ApiResponse.ok(orderService.placeOrder(request.userId(), request.requests()));
    }

    @GetMapping("/{orderId}/inspect")
    public ApiResponse<OrderConsistencyView> inspectOrder(@PathVariable Long orderId) {
        return ApiResponse.ok(orderService.inspectOrder(orderId));
    }

    @PatchMapping("/{orderId}/orderItems/{orderItemId}/price")
    public ApiResponse<Void> changeOrderItemPrice(@PathVariable Long orderId, @PathVariable Long orderItemId, @RequestParam BigDecimal price) {
        orderService.changeItemPrice(orderId, orderItemId, price);
        return ApiResponse.ok();
    }

    @PatchMapping("/{orderId}/orderItems/{orderItemId}/quantity")
    public ApiResponse<Void> changeOrderItemQuantity(@PathVariable Long orderId, @PathVariable Long orderItemId, @RequestParam int quantity) {
        orderService.changeItemQuantity(orderId, orderItemId, quantity);
        return ApiResponse.ok();
    }

    public record OrderCreationRequest(@NonNull Long userId, @NonNull List<OrderItemRequest> requests) {

    }

    public record OrderItemRequest(@NonNull Long productId, @NonNull Integer quantity) {

    }
}
