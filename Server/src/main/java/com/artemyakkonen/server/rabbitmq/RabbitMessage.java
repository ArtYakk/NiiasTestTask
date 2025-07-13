package com.artemyakkonen.server.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RabbitMessage implements Serializable {
    private String uuid;
    private LocalDateTime timestamp;
    private String body;
}
