package com.growmighty.lectures.firstday.tangledmonolith.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

// 생성 후 바뀌지 않는 응답용 불변 데이터이므로 record로 선언.
@JsonInclude(JsonInclude.Include.NON_NULL)  // 성공 시 error 필드를 숨김.
public record ApiResponse<T>(
        // 성공 여부 / 성공 시 데이터 / 실패 시 에러
        boolean success, T data, ApiError error
) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(true, null, null);
    }

    public static ApiResponse<Void> fail(ApiError error) {
        return new ApiResponse<>(false, null, error);
    }

    public record ApiError(String code, String message) {
    }
}
