package com.rperezv365.apifirst.apifirstserver.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

/**
 * OrderLine
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 12/01/2025 - 11:17
 * @since 1.17
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "char(36)", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne
    private Order order;

    @NotNull
    @ManyToOne
    private Product product;

    @NotNull
    @Min(1) @Max(10000)
    private Integer orderQuantity;

    @Min(1) @Max(10000)
    private Integer shipQuantity;

    @CreationTimestamp
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    private OffsetDateTime dateUpdated;

}