package com.artemyakkonen.client.service;

import com.artemyakkonen.client.util.AnsiColors;
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
    private final String serviceId;

    public IdentifierService(@Value("${spring.identifier.path}") String ID_FILE) {
        this.ID_FILE = ID_FILE;
        this.serviceId = loadOrGenerateId();
        log.info(AnsiColors.blackOnBlue("Путь к фалу с ID: " + ID_FILE));
    }

    private String loadOrGenerateId() {
        File file = new File(ID_FILE);
        if (file.exists()) {
            try {
                return Files.readString(file.toPath()).trim();
            } catch (IOException e) {
                log.info("Ошибка чтения файла, путь: {}", ID_FILE);
            }
        }
        String newId = UUID.randomUUID().toString();
        try {
            Files.writeString(file.toPath(), newId);
        } catch (IOException e) {
            throw new RuntimeException("Error when saving file", e);
        }
        return newId;
    }

    public String getServiceId() {
        return serviceId;
    }
}

