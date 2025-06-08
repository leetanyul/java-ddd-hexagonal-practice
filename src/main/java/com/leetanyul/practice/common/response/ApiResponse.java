// com.leetanyul.practice.common.response.ApiResponse.java

package com.leetanyul.practice.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

    @JsonProperty("code")
    private final String code;

    @JsonProperty("success")
    private final boolean success;

    @JsonProperty("data")
    private final T data;

    private ApiResponse(ResponseCode code, boolean success, T data) {
        this.code = code.name();
        this.success = success;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseCode.SUCCESS, true, data);
    }

    public static <T> ApiResponse<T> fail(ResponseCode code, T data) {
        return new ApiResponse<>(code, false, data);
    }
}
