package com.rperezv365.apifirst.apifirstserver.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Name
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 11/01/2025 - 16:37
 * @since 1.17
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Name {

    @Size(max = 25)
    private String prefix;

    @NotNull
    @Size(min = 2, max = 100)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 100)
    private String lastName;

    @Size(max = 25)
    private String suffix;

}
