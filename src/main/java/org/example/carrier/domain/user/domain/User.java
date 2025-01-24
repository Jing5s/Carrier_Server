package org.example.carrier.domain.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_user")
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 15, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String picture;

    @Column(name = "refresh_token", nullable = false)
    private String googleRefreshToken;

    @Builder
    public User(String email, String nickname, String picture, String googleRefreshToken) {
        this.email = email;
        this.nickname = nickname;
        this.picture = picture;
        this.googleRefreshToken = googleRefreshToken;
    }
}
