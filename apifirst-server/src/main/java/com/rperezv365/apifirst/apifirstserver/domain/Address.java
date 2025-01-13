package com.rperezv365.apifirst.apifirstserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

/**
 * Address
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 11/01/2025 - 16:44
 * @since 1.17
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "char(36)", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @Size(min = 3, max = 100)
    private String addressLine1;

    @Size(min = 3, max = 100)
    private String addressLine2;

    @NotNull
    @Size(min = 2, max = 100)
    private String city;

    @NotNull
    @Size(min = 2, max = 2)
    private String state;

    @NotNull
    @Size(min = 5, max = 10)
    private String zip;

    @CreationTimestamp
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    private OffsetDateTime dateUpdated;

}