package com.artemyakkonen.server.repository;

import com.artemyakkonen.server.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUser_Id(Long id); // Запросить информацию о сообщениях полученных от конкретного пользователя
    //void deleteById(Long id); // Удалить выбранное сообщение из базы данных // DEFAULT
}
