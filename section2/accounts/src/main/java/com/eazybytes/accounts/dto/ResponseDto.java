package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold successful responses")
public class ResponseDto {

    @Schema(name = "statusCode", description = "Holds the response code")
    private String statusCode;

    @Schema(name = "statusMsg", description = "Holds the response message")
    private String statusMsg;

}
