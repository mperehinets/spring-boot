package com.mper.springboot.dto;

import com.mper.springboot.dao.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BaseDto {
    private Long id;

    private LocalDateTime created;

    private LocalDateTime updated;

    private Status status;
}
