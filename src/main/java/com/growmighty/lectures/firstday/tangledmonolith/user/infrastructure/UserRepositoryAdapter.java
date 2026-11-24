package com.growmighty.lectures.firstday.tangledmonolith.user.infrastructure;

import com.growmighty.lectures.firstday.tangledmonolith.user.domain.User;
import com.growmighty.lectures.firstday.tangledmonolith.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public User save(final User user) {
        return jpaRepository.save(user);
    }
}
