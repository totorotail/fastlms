package com.zerobase.fastlms.member.dto;

import com.zerobase.fastlms.member.entity.LoginHistory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Builder
@Data
public class LoginHistoryDto {
    private Long id;
    private String userId;
    private LocalDateTime loginDt;
    private String clientIp;
    private String userAgent;


    public static LoginHistoryDto of(LoginHistory loginHistory) {
        return LoginHistoryDto.builder()
                .id(loginHistory.getId())
                .userId(loginHistory.getUserId())
                .loginDt(loginHistory.getLoginDt())
                .clientIp(loginHistory.getClientIp())
                .userAgent(loginHistory.getUserAgent())
                .build();
    }

    public static List<LoginHistoryDto> of(List<LoginHistory> loginHistories) {
        if (loginHistories == null) {
            return null;
        }

        List<LoginHistoryDto> logInHistoryList = new ArrayList<>();
        for (LoginHistory x : loginHistories) {
            logInHistoryList.add(LoginHistoryDto.of(x));
        }
        return logInHistoryList;
    }
}
