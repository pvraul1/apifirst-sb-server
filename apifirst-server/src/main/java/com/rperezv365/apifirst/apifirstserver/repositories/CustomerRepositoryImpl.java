package com.rperezv365.apifirst.apifirstserver.repositories;

import com.rperezv365.apifirst.model.AddressDto;
import com.rperezv365.apifirst.model.CustomerDto;
import com.rperezv365.apifirst.model.PaymentMethodDto;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepositoryImpl
 * <p>
 * Created by IntelliJ, Spring Framework Guru.
 *
 * @author architecture - pvraul
 * @version 07/01/2025 - 09:03
 * @since 1.17
 */
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final Map<UUID, CustomerDto> entityMap = new HashMap<>();

    @SuppressWarnings("unchecked")
	@Override
    public <S extends CustomerDto> S save(S entity) {
        UUID id = UUID.randomUUID();

        CustomerDto.CustomerDtoBuilder builder1 = CustomerDto.builder();

        builder1.id(id);

        if (entity.getBillToAddress() != null) {
            builder1.billToAddress(AddressDto.builder()
                    .id(UUID.randomUUID())
                    .addressLine1(entity.getBillToAddress().getAddressLine1())
                    .addressLine2(entity.getBillToAddress().getAddressLine2())
                    .city(entity.getBillToAddress().getCity())
                    .state(entity.getBillToAddress().getState())
                    .zip(entity.getBillToAddress().getZip())
                    .dateCreated(OffsetDateTime.now())
                    .dateUpdated(OffsetDateTime.now())
                    .build());
        }

        if (entity.getShipToAddress() != null) {
            builder1.shipToAddress(AddressDto.builder()
                    .id(UUID.randomUUID())
                    .addressLine1(entity.getShipToAddress().getAddressLine1())
                    .addressLine2(entity.getShipToAddress().getAddressLine2())
                    .city(entity.getShipToAddress().getCity())
                    .state(entity.getShipToAddress().getState())
                    .zip(entity.getShipToAddress().getZip())
                    .dateCreated(OffsetDateTime.now())
                    .dateUpdated(OffsetDateTime.now())
                    .build());
        }

        if (entity.getPaymentMethods() != null && !entity.getPaymentMethods().isEmpty()) {
            builder1.paymentMethods(entity.getPaymentMethods()
                    .stream()
                    .map(paymentMethod -> PaymentMethodDto.builder()
                            .id(UUID.randomUUID())
                            .displayName(paymentMethod.getDisplayName())
                            .cardNumber(paymentMethod.getCardNumber())
                            .expiryMonth(paymentMethod.getExpiryMonth())
                            .expiryYear(paymentMethod.getExpiryYear())
                            .cvv(paymentMethod.getCvv())
                            .dateCreated(OffsetDateTime.now())
                            .dateUpdated(OffsetDateTime.now())
                            .build())
                    .collect(Collectors.toList()));
        }

        CustomerDto customer = builder1.email(entity.getEmail())
                .name(entity.getName())
                .phone(entity.getPhone())
                .dateCreated(OffsetDateTime.now())
                .dateUpdated(OffsetDateTime.now())
                .build();

        entityMap.put(id, customer);

        return (S) customer;
    }

    @Override
    public <S extends CustomerDto> Iterable<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDto> findById(UUID uuid) {
        return Optional.of(entityMap.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return entityMap.get(uuid) != null;
    }

    @Override
    public Iterable<CustomerDto> findAll() {
        return entityMap.values();
    }

    @Override
    public Iterable<CustomerDto> findAllById(Iterable<UUID> uuids) {
        return StreamSupport.stream(uuids.spliterator(), false)
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return entityMap.size();
    }

    @Override
    public void deleteById(UUID uuid) {
        entityMap.remove(uuid);
    }

    @Override
    public void delete(CustomerDto entity) {
        entityMap.remove(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {
        uuids.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends CustomerDto> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        entityMap.clear();
    }

}
