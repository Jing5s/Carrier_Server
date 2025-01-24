package org.example.carrier.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "refreshToken")
public class Token {
    @Id
    private String email;

    @Indexed
    private String refreshToken;

    private String authAccessToken;

    @Builder
    public Token(String email, String refreshToken, String authAccessToken) {
        this.email = email;
        this.refreshToken = refreshToken;
        this.authAccessToken = authAccessToken;
    }
}
