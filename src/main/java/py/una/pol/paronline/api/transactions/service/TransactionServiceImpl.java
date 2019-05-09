/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.transactions.service;

import py.una.pol.paronline.api.transactions.entity.Transaction;
import py.una.pol.paronline.commons.domain.repository.Repository;
import py.una.pol.paronline.commons.domain.service.BaseService;

/**
 *
 * @author dlopez
 */
public class TransactionServiceImpl extends BaseService<Transaction, Integer>
    implements TransactionService{
    
    public TransactionServiceImpl(Repository<Transaction, Integer> repository) {
        super(repository);
    }
    
}
