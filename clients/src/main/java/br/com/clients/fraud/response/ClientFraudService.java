package br.com.clients.fraud.response;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fraud", url = "${clients.fraud.url}")
public interface ClientFraudService {

    @GetMapping(value = "/customer/{customerId}", consumes = "application/json")
    InternalResponseFraud isFraud(@PathVariable("customerId") Long costumerId);

    @GetMapping(value="/findByCpf/{cpf}")
    ResponseFraud getFraudByCpf(@PathVariable("cpf") String cpf);




}
