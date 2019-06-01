/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.transactions.service;

import py.una.pol.paronline.api.transactions.repository.TransactionCabRepository;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.domain.entity.transactions.Transaction;
import py.una.pol.paronline.commons.domain.service.BaseService;

/**
 *
 * @author dlopez
 */
public class TransactionCabServiceImpl extends BaseService<Transaction, Integer>
    implements TransactionCabService{
    
    private TransactionCabRepository<Transaction, Integer> cabRepository;
    
    public TransactionCabServiceImpl(TransactionCabRepository<Transaction, Integer> repository) {
        super(repository);
        this.cabRepository = repository;
    }

    @Override
    public void update(Transaction transaction) throws Exception {
        cabRepository.update(transaction);
    }

    @Override
    public void delete(Integer id) throws Exception {
        cabRepository.remove(id);
    }

    @Override
    public Entity findById(Integer id) throws Exception {
        return cabRepository.get(id);
    }
    
}
