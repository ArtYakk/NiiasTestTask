package com.artemyakkonen.spring.boot.ttmicroservice2.dto;

import com.artemyakkonen.spring.boot.ttmicroservice2.entity.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponse {
    private Long id;
    private Long user_id;
    private LocalDateTime time;
    private ActivityType type;
}
