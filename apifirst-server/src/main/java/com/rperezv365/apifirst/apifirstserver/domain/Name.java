package com.rperezv365.apifirst.apifirstserver.domain;

import jakarta.persistence.Entity;
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
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Name {

    private String prefix;
    private String firstName;
    private String lastName;
    private String suffix;

}
