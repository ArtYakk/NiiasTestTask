package com.artemyakkonen.server.dto.rabbit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RabbitMessageDTO implements Serializable {
    private String uuid;
    private LocalDateTime timestamp;
    private String body;
}
