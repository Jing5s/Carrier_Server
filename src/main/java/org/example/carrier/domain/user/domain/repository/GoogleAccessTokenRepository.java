package org.example.carrier.domain.user.domain.repository;

import org.example.carrier.domain.user.domain.GoogleAccessToken;
import org.example.carrier.domain.user.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoogleAccessTokenRepository extends CrudRepository<GoogleAccessToken, String> {
}
