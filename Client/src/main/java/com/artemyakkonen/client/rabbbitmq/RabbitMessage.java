package com.artemyakkonen.client.rabbbitmq;

import lombok.*;

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
