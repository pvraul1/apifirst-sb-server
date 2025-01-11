package com.rperezv365.apifirst.apifirstserver.domain;

import jakarta.persistence.Entity;
import lombok.*;

/**
 * Dimension
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 11/01/2025 - 17:20
 * @since 1.17
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Dimension {

    private Integer length;
    private Integer width;
    private Integer height;

}
