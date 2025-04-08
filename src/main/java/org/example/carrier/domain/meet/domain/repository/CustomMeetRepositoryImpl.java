package org.example.carrier.domain.meet.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.carrier.domain.meet.domain.Meet;
import org.example.carrier.domain.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.example.carrier.domain.meet.domain.QMeet.meet;

@RequiredArgsConstructor
@Repository
public class CustomMeetRepositoryImpl implements CustomMeetRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Meet> findAllMeetsDesc(User user) {
        return queryFactory
                .selectFrom(meet)
                .where(meet.user.eq(user))
                .orderBy(meet.createdAt.desc())
                .fetch();
    }
}
