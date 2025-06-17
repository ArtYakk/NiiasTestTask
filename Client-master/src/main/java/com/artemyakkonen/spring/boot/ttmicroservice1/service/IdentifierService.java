package com.artemyakkonen.spring.boot.ttmicroservice1.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class IdentifierService {
    private static final String ID_FILE = "/app/data/service-id.txt"; // Локальный файл для хранения ID
    private final String serviceId;

    public IdentifierService() {
        this.serviceId = loadOrGenerateId();
    }

    private String loadOrGenerateId() {
        File file = new File(ID_FILE);
        if (file.exists()) {
            try {
                return Files.readString(file.toPath()).trim();
            } catch (IOException e) {
                throw new RuntimeException("Error when reading file", e);
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

