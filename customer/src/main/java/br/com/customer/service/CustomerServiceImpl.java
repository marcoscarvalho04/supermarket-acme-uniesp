package br.com.customer.service;

import br.com.clients.fraud.response.ClientFraudService;
import br.com.clients.fraud.response.ResponseFraud;
import br.com.clients.notification.request.NotificationPayload;
import br.com.customer.config.Constants;
import br.com.customer.config.ConvertUtils;
import br.com.customer.exception.CustomerFraudException;
import br.com.customer.model.CustomerEntity;
import br.com.customer.reponse.CustomerResponse;
import br.com.customer.repository.CustomerRepository;
import br.com.customer.request.CustomerRequest;
import br.com.rabbitmq.RabbitMQMessageProducer;
import feign.FeignException;
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
    private final ClientFraudService clientFraudService;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ConvertUtils convertUtils,
                               ClientFraudService clientFraudService,
                               RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
        this.customerRepository = customerRepository;
        this.convertUtils = convertUtils;
        this.clientFraudService = clientFraudService;
    }

    @Override
    public void saveCustomer(CustomerRequest customerRequest) {
        log.info("creating customer {}", customerRequest);

        CustomerEntity customerEntity = (CustomerEntity) this.convertUtils.convertRequestToEntity(customerRequest, CustomerEntity.class);
        try {
            ResponseFraud fraud;
            fraud = this.clientFraudService.getFraudByCpf(customerRequest.getCpf());
            if (fraud != null) {

                var notificationPayload = NotificationPayload.builder()
                        .customerCpf(fraud.getCpf())
                        .customerMail(customerEntity.getEmail())
                        .sender(customerEntity.getNome())
                        .message(fraud.getName())
                        .build();

                this.rabbitMQMessageProducer.publish(notificationPayload, Constants.EXCHANGE_TO_NOTIFICATION, Constants.ROUTING_KEY);
                throw new CustomerFraudException();
            }
        }catch (FeignException.NotFound e) {
            this.customerRepository.save(customerEntity);
        }







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
