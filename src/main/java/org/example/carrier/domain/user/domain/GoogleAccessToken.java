package org.example.carrier.domain.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "accessToken", timeToLive = 3599000L)
public class GoogleAccessToken {
    @Id
    private String email;

    @Indexed
    private String accessToken;

    public GoogleAccessToken(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }
}
