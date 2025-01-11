package com.rperezv365.apifirst.apifirstserver.domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * PaymentMethod
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 11/01/2025 - 16:54
 * @since 1.17
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    private Customer customer;

    private String displayName;
    private Integer cardNumber;
    private Integer expiryMonth;
    private Integer expiryYear;
    private Integer cvv;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateUpdated;
}
