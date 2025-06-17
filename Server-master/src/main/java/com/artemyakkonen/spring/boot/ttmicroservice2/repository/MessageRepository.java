package com.artemyakkonen.spring.boot.ttmicroservice2.repository;

import com.artemyakkonen.spring.boot.ttmicroservice2.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUser_Id(Long id); // Запросить информацию о сообщениях полученных от конкретного пользователя
    //void deleteById(Long id); // Удалить выбранное сообщение из базы данных // DEFAULT
}
