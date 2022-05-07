package br.com.customer.controller;

import br.com.customer.config.Constants;
import br.com.customer.config.ConvertUtils;
import br.com.customer.reponse.CustomerResponse;
import br.com.customer.request.CustomerRequest;
import br.com.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.CUSTOMER_API)
@Tag(name = "API to manager customs", description = "create and manager customers")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService, ConvertUtils convertUtils) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Create customers", description = "create new customer to fraud system")
    @ApiResponse(responseCode = "201", description = "customer sucessfully created")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequest customerRequest) {
        log.info("calling customer service to create: {}", customerRequest);
        this.customerService.saveCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @GetMapping
    @Operation(summary = "Get customers", description = "list all customers")
    @ApiResponse(responseCode = "200" , description = "customer list sucessfully")
    public ResponseEntity<List<CustomerResponse>> getCustomers(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ){

        log.info("calling customer service to list");
        List<CustomerResponse> allCostumers = this.customerService.getCustomers(pageNumber,pageSize,sortBy);
        if (allCostumers == null) {
            return ResponseEntity.status(404).build();
        }
        return new ResponseEntity<List<CustomerResponse>>(allCostumers,HttpStatus.OK);


    }



}
