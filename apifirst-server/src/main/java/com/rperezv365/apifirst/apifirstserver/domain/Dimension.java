package com.rperezv365.apifirst.apifirstserver.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Dimension {

    @NotNull
    @Min(1) @Max(999)
    private Integer length;

    @NotNull
    @Min(1) @Max(999)
    private Integer width;

    @NotNull
    @Min(1) @Max(999)
    private Integer height;

}
