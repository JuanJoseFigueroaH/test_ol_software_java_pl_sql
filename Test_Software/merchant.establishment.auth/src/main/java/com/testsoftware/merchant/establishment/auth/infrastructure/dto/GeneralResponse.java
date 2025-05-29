package com.testsoftware.merchant.establishment.auth.infrastructure.dto;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralResponse<T> {
    private boolean success;
    private String timestamp;
    private String responseCode;
    private String message;
    private T data;

    public static <T> GeneralResponse<T> success(T data, String message) {
        return GeneralResponse.<T>builder()
                .success(true)
                .timestamp(LocalDate.now() + " " + LocalTime.now())
                .responseCode("00")
                .message(message)
                .data(data)
                .build();
    }

    public static <T> GeneralResponse<T> error(String code, String message) {
        return GeneralResponse.<T>builder()
                .success(false)
                .timestamp(LocalDate.now() + " " + LocalTime.now())
                .responseCode(code)
                .message(message)
                .build();
    }
}
