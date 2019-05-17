/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.transactions.service;

import py.una.pol.paronline.api.transactions.entity.Transaction;
import py.una.pol.paronline.commons.domain.entity.Entity;

/**
 *
 * @author dlopez
 */
public interface TransactionService {
    
    /**
     *
     * @param user
     * @throws Exception
     */
    public void add(Transaction user) throws Exception;

    /**
     *
     * @param user
     * @throws Exception
     */
    public void update(Transaction user) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Entity findById(Integer id) throws Exception;
}
