/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.transactions.entity;

import java.math.BigDecimal;
import lombok.Data;
import py.una.pol.paronline.commons.domain.entity.BaseEntity;

/**
 *
 * @author dlopez
 */
@Data
public class TransactionDet extends BaseEntity<Integer> {
    
    private Transaction transaction;
    private Integer item;
//    private Product product;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
    
    public TransactionDet(Integer id, String nombre) {
        super(id, nombre);
    }
    
}
