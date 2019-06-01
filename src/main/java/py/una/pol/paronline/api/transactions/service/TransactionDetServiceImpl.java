/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.transactions.service;

import py.una.pol.paronline.api.transactions.repository.TransactionDetRepository;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.domain.entity.transactions.TransactionDet;
import py.una.pol.paronline.commons.domain.service.BaseService;

/**
 *
 * @author dlopez
 */
public class TransactionDetServiceImpl extends BaseService<TransactionDet, Integer> 
        implements TransactionDetService{
    
    private TransactionDetRepository<TransactionDet, Integer> detRepository;

    public TransactionDetServiceImpl(TransactionDetRepository<TransactionDet, Integer> repository) {
        super(repository);
        this.detRepository = repository;
    }

    @Override
    public void update(TransactionDet transaction) throws Exception {
        detRepository.update(transaction);
    }

    @Override
    public void delete(Integer id) throws Exception {
        detRepository.remove(id);
    }

    @Override
    public Entity findById(Integer id) throws Exception {
        return detRepository.get(id);
    }
    
}
