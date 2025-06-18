package com.artemyakkonen.client.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RabbitMessageDTO implements Serializable {
    private String uuid;
    private LocalDateTime timestamp;
    private String body;
}
