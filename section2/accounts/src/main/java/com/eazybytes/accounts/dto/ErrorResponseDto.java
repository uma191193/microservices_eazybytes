package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema to hold unsuccessful responses")
public class ErrorResponseDto {

    @Schema(name = "apiPath", description = "Holds the path of API", example = "https://abcd.com/respectievePath")
    private String apiPath;

    @Schema(name = "errorCode", description = "Provides the status code", example = "500")
    private HttpStatus errorCode;

    @Schema(name = "errorMessage", description = "Holds the message of a particular error", example = "Error message")
    private String errorMessage;

    @Schema(name = "errorTime", description = "Used to track the time of the error event", example = "2025-04-23T11:41:00.000")
    private LocalDateTime errorTime;

}
