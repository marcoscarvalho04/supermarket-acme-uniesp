package br.com.fraud.service;

import br.com.fraud.request.FraudRequest;
import br.com.fraud.response.FraudResponse;

import java.util.List;

public interface FraudService {

    void saveFraud(FraudRequest fraudRequest);
    List<FraudResponse> getFraud(Integer pageNumber, Integer size, String sortBy);
    Boolean isFraud(Long customerId);
    FraudResponse getFraudByCpf(String cpf);
}
