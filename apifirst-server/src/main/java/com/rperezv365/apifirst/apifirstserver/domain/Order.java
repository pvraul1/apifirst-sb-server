package com.rperezv365.apifirst.apifirstserver.domain;


import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

/**
 * Order
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 12/01/2025 - 11:10
 * @since 1.17
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_header") // order is a reserved word in SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "CHAR(36)", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    private Customer customer;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus = OrderStatusEnum.NEW;

    private String shipmentInfo;

    @Builder.Default
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();

    @CreationTimestamp
    private OffsetDateTime dateCreated;

    @UpdateTimestamp
    private OffsetDateTime dateUpdated;

}
