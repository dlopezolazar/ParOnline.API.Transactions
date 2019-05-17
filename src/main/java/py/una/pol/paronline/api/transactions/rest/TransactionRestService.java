/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.transactions.rest;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import py.una.pol.paronline.api.transactions.entity.Transaction;
import py.una.pol.paronline.api.transactions.repository.JdbcTransactionRepository;
import py.una.pol.paronline.api.transactions.service.TransactionServiceImpl;

/**
 *
 * @author dlopez
 */
@Path("/v1/transactions")
public class TransactionRestService {
    
    private final TransactionServiceImpl transactionService = new TransactionServiceImpl(new JdbcTransactionRepository());

    @GET
    @Path("/")
    @Produces("application/json")
    public ArrayList<Transaction> getTransactions() {
        ArrayList<Transaction> transactions = (ArrayList) transactionService.getAll();
        return transactions;
    }
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Transaction getTransaction(@PathParam("id") Integer id) {
        Transaction entity = null;
        try {
            entity = (Transaction) transactionService.findById(id);
        } catch (Exception ex) {
            Logger.getLogger(TransactionRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }
    
}