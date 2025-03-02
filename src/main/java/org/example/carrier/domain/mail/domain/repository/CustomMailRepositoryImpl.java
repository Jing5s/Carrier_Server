package org.example.carrier.domain.mail.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.mail.domain.Mail;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.example.carrier.domain.mail.domain.QMail.mail;

@RequiredArgsConstructor
@Repository
public class CustomMailRepositoryImpl implements CustomMailRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Mail> findAllByUserOrderByDate(User user) {
        return queryFactory
                .select(mail)
                .from(mail)
                .leftJoin(mail.labels).fetchJoin()
                .where(mail.user.eq(user))
                .orderBy(mail.date.desc())
                .fetch();
    }

    @Override
    public Long findMaxHistoryId(User user) {
        return queryFactory
                .select(mail.historyId.max())
                .from(mail)
                .where(mail.user.eq(user))
                .fetchOne();
    }
}
