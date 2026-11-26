package com.growmighty.lectures.firstday.order.presentation;

import com.growmighty.lectures.firstday.common.response.ApiResponse;
import com.growmighty.lectures.firstday.order.application.OrderApiService;
import com.growmighty.lectures.firstday.order.presentation.dto.ChangeOrderItemPriceRequest;
import com.growmighty.lectures.firstday.order.presentation.dto.ChangeOrderItemQuantityRequest;
import com.growmighty.lectures.firstday.order.presentation.dto.OrderConsistencyResponse;
import com.growmighty.lectures.firstday.order.presentation.dto.OrderResponse;
import com.growmighty.lectures.firstday.order.presentation.dto.PlaceOrderFromCartRequest;
import com.growmighty.lectures.firstday.order.presentation.dto.PlaceOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderApiService orderService;

    @GetMapping
    public ApiResponse<List<OrderResponse>> getOrders() {
        List<OrderResponse> responses = orderService.getOrders().stream()
                .map(OrderResponse::from)
                .toList();
        return ApiResponse.ok(responses);
    }

    @PostMapping
    public ApiResponse<OrderResponse> placeOrder(@RequestBody PlaceOrderRequest request) {
        return ApiResponse.ok(OrderResponse.from(orderService.placeOrder(request.toCommand())));
    }

    @PostMapping("/{orderId}/cancel")
    public ApiResponse<OrderResponse> cancelOrder(@PathVariable Long orderId) {
        return ApiResponse.ok(OrderResponse.from(orderService.cancelOrder(orderId)));
    }

    @GetMapping("/{orderId}/inspect")
    public ApiResponse<OrderConsistencyResponse> inspectOrder(@PathVariable Long orderId) {
        return ApiResponse.ok(OrderConsistencyResponse.from(orderService.inspectOrder(orderId)));
    }

    @PatchMapping("/{orderId}/orderItems/{orderItemId}/price")
    public ApiResponse<Void> changeOrderItemPrice(@PathVariable Long orderId, @PathVariable Long orderItemId, @RequestBody ChangeOrderItemPriceRequest request) {
        orderService.changeItemPrice(orderId, orderItemId, request.price());
        return ApiResponse.ok();
    }

    @PatchMapping("/{orderId}/orderItems/{orderItemId}/quantity")
    public ApiResponse<Void> changeOrderItemQuantity(@PathVariable Long orderId, @PathVariable Long orderItemId, @RequestBody ChangeOrderItemQuantityRequest request) {
        orderService.changeItemQuantity(orderId, orderItemId, request.quantity());
        return ApiResponse.ok();
    }
}
