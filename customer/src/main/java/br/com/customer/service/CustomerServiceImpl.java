package br.com.customer.service;

import br.com.customer.config.ConvertUtils;
import br.com.customer.model.CustomerEntity;
import br.com.customer.reponse.CustomerResponse;
import br.com.customer.repository.CustomerRepository;
import br.com.customer.request.CustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ConvertUtils convertUtils;

    public CustomerServiceImpl(CustomerRepository customerRepository, ConvertUtils convertUtils) {
        this.customerRepository = customerRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    public void saveCustomer(CustomerRequest customerRequest) {
        log.info("creating customer {}", customerRequest);
        CustomerEntity customerEntity = (CustomerEntity) this.convertUtils.convertRequestToEntity(customerRequest, CustomerEntity.class);
        this.customerRepository.save(customerEntity);
    }

    @Override
    public List<CustomerResponse> getCustomers(Integer pageNumber, Integer size, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber,size, Sort.by(sortBy));

        Page<CustomerEntity> pagedResult = this.customerRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return this.convertUtils.convertToListResponse(pagedResult.getContent(), CustomerResponse.class);
        }
        return null;
    }
}
