package br.com.customer.service;

import br.com.customer.model.CustomerEntity;
import br.com.customer.reponse.CustomerResponse;
import br.com.customer.request.CustomerRequest;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerRequest customerRequest);
    List<CustomerResponse> getCustomers(Integer pageNumber, Integer size, String sortBy);
}
