package br.com.fraud.service;

import br.com.fraud.config.ConvertUtils;
import br.com.fraud.model.FraudEntity;
import br.com.fraud.repository.FraudRepository;
import br.com.fraud.request.FraudRequest;
import br.com.fraud.response.FraudResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudServiceImpl implements FraudService{

    private FraudRepository fraudRepository;
    private ConvertUtils convertUtils;

    public FraudServiceImpl(FraudRepository fraudRepository, ConvertUtils convertUtils) {
        this.convertUtils = convertUtils;
        this.fraudRepository = fraudRepository;
    }


    @Override
    public void saveFraud(FraudRequest fraudRequest) {

        FraudEntity entity = (FraudEntity)  this.convertUtils.convertRequestToEntity(fraudRequest, FraudEntity.class);
        this.fraudRepository.save(entity);

    }

    @Override
    public List<FraudResponse> getFraud(Integer pageNumber, Integer size, String sortBy) {

        Pageable paging = PageRequest.of(pageNumber,size, Sort.by(sortBy));

        Page<FraudEntity> pagedResult = this.fraudRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return this.convertUtils.convertToListResponse(pagedResult.getContent(), FraudResponse.class);
        }
        return null;
    }

    @Override
    public Boolean isFraud(Long customerId) {

        FraudEntity fraudEntity = this.fraudRepository.findByCostumerId(customerId);
        if (fraudEntity != null && fraudEntity.getIsFraud()) {
            return true;
        }

        return false;
    }

    @Override
    public FraudResponse getFraudByCpf(String cpf) {
        FraudEntity foundCustomer = fraudRepository.findByCpf(cpf);

        if (foundCustomer == null) {
            return null;
        }

        FraudResponse response = (FraudResponse) this.convertUtils.convertEntityToRequest(foundCustomer, FraudResponse.class);
        return response;
    }
}
