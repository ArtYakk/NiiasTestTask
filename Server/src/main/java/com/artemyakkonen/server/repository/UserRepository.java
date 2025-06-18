package com.artemyakkonen.server.repository;

import com.artemyakkonen.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //List<User> findAll(); //Запросить список пользователей зарегистрированных в системе // DEFAULT
    List<User> findByActivitiesIsNotEmpty(); // Запросить список пользователей активных пользователей в системе
   // void deleteById(Long id); //Удалить пользователя из системы // DEFAULT
   Optional<User> findByUuid(String uuid);
}
