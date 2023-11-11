package main.java.com.internetbanking.internetbanking.repository;
import main.java.com.internetbanking.internetbanking.models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByEmail(String email);
}
