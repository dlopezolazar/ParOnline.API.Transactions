/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.transactions.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import py.una.pol.paronline.commons.domain.entity.BaseEntity;

/**
 *
 * @author dlopez
 */
@Data
public class Transaction extends BaseEntity<Integer>{
    
    private Date date;
//    private User client;
    private BigDecimal total;
    private String address;
    private Integer paymentMethod;
    private String cardNumber;
    private Integer state;
    
    
    public Transaction(Integer id, String nombre) {
        super(id, nombre);
    }
    
}
