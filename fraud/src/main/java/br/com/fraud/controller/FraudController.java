package br.com.fraud.controller;


import br.com.fraud.config.Constants;
import br.com.fraud.request.FraudRequest;
import br.com.fraud.response.FraudResponse;
import br.com.fraud.service.FraudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.FRAUD_API)
@Tag(name = "API to manager customs", description = "create and manager customers")
@Slf4j
public class FraudController {


    private FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @GetMapping
    @Operation(summary = "Get frauds", description = "list all frauds")
    @ApiResponse(responseCode = "200" , description = "fraud list sucessfully")
    public ResponseEntity<List<FraudResponse>> getFrauds(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    )
    {
        List<FraudResponse> fraudResponse = this.fraudService.getFraud(pageNumber, pageSize, sortBy);
        if (fraudResponse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<List<FraudResponse>>(fraudResponse, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Fraud customers", description = "create new fraud ")
    @ApiResponse(responseCode = "201", description = "fraud sucessfully created")
    public ResponseEntity<?> createCustomer(@RequestBody FraudRequest fraudRequest) {
        log.info("calling customer service to create: {}", fraudRequest);
        this.fraudService.saveFraud(fraudRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Fraud by Customer", description = "find fraud by customer")
    @ApiResponse(responseCode = "200", description = "fraud found sucessfully")
    public ResponseEntity<Boolean> findByCustomer(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<Boolean>(fraudService.isFraud(customerId), HttpStatus.OK);
    }

    @GetMapping("/findByCpf/{cpf}")
    @Operation(summary = "find by cpf", description = "find fraud by cpf")
    @ApiResponse(responseCode = "200", description = "customer found sucessfully")
    public ResponseEntity findCustomerByCpf(@PathVariable("cpf") String cpf) {
        if (cpf == null || cpf.trim().isEmpty()|| cpf.trim().length() != 11 ) {
            return new ResponseEntity<String>("CPF must not be empty or have 11 positions", HttpStatus.BAD_REQUEST);
        }
        FraudResponse response = fraudService.getFraudByCpf(cpf);
        if (response == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return new ResponseEntity(response, HttpStatus.OK);

    }


}
