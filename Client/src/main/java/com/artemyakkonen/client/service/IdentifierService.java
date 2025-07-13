package com.artemyakkonen.client.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;


@Slf4j
@Service
public class IdentifierService {
    private final String ID_FILE;
    @Getter
    private final String serviceId;

    public IdentifierService(@Value("${spring.identifier.path}") String ID_FILE) throws IOException {
        this.ID_FILE = ID_FILE;
        this.serviceId = loadOrGenerateId();
    }

    private String loadOrGenerateId() throws IOException {
        File file = new File(ID_FILE);
        if (file.exists()) {
            try {
                return Files.readString(file.toPath()).trim();
            } catch (IOException e) {
                log.error("Ошибка чтения файла, путь: {}", ID_FILE);
                throw new IOException("Ошибка чтения файла, путь: " + ID_FILE, e);
            }
        }
        String newId = UUID.randomUUID().toString();
        try {
            Files.writeString(file.toPath(), newId);
        } catch (IOException e) {
            log.error("Ошибка записи файла, путь: {}", ID_FILE);
            throw new RuntimeException("Ошибка записи файла, путь: " + ID_FILE, e);
        }
        return newId;
    }
}

