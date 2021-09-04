package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubScribeDto {
    private BigInteger id;
    private String username;
    private String profileImageUrl;
    private BigInteger subscribeState;
    private BigInteger equalUserState;
}
