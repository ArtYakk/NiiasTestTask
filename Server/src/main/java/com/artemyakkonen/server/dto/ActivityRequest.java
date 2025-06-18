package com.artemyakkonen.server.dto;

import com.artemyakkonen.server.entity.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {
    private Long user_id;
    private LocalDateTime time;
    private ActivityType type;
}