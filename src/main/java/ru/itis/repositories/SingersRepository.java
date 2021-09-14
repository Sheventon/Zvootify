package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Singer;

public interface SingersRepository extends JpaRepository<Singer, Long> {
}
