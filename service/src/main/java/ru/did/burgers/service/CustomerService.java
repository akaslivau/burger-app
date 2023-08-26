package ru.did.burgers.service;

import static ru.did.burgers.resources.ServiceConstants.NO_CONTENT;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.did.burgers.CustomerApiDelegate;
import ru.did.burgers.TCustomer;
import ru.did.burgers.TCustomerCreate;
import ru.did.burgers.domain.model.Customer;
import ru.did.burgers.domain.repository.CustomerJpaRepository;

@Service
@RequiredArgsConstructor
@Primary
public class CustomerService implements CustomerApiDelegate {

  private final CustomerJpaRepository repo;

  @Override
  public ResponseEntity<Void> createCustomer(TCustomerCreate dto) {
    Customer entity = new Customer();
    entity.setName(dto.getName());
    entity.setEmail(dto.getEmail());
    repo.save(entity);
    return NO_CONTENT;
  }

  @Override
  public ResponseEntity<List<TCustomer>> findAllCustomers() {
    List<TCustomer> customers = repo.findAll().stream()
        .map(this::toDto).collect(Collectors.toList());

    return ResponseEntity.ok(customers);
  }

  private TCustomer toDto(Customer customer) {
    TCustomer dto = new TCustomer();
    dto.setId(customer.getId());
    dto.setName(customer.getName());
    dto.setEmail(customer.getEmail());
    return dto;
  }

}