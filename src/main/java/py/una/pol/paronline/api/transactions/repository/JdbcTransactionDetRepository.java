/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.transactions.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.domain.entity.transactions.TransactionDet;
import py.una.pol.paronline.commons.utils.DataBaseUtil;

/**
 *
 * @author dlopez
 */
public class JdbcTransactionDetRepository implements TransactionDetRepository<TransactionDet, Integer>{

    private Connection connection;
    private PreparedStatement pstmt;
    
    public JdbcTransactionDetRepository() {
    }

    public JdbcTransactionDetRepository(Connection connection, PreparedStatement pstmt) {
        this.connection = connection;
        this.pstmt = pstmt;
    }
    
    @Override
    public void add(TransactionDet entity) {
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("INSERT INTO transacciones_det(item, id_producto, cantidad, precio, sub_total) VALUES (?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, entity.getItem());
            pstmt.setInt(2, entity.getProduct());
            pstmt.setInt(3, entity.getQuantity());
            pstmt.setBigDecimal(4, entity.getPrice());
            pstmt.setBigDecimal(5, entity.getSubTotal());
            

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcTransactionDetRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    @Override
    public void remove(Integer id) {
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("DELETE FROM transacciones_det WHERE id_transaccion = ?");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcTransactionDetRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    @Override
    public void update(TransactionDet entity) {
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("UPDATE transacciones_cab SET fecha=SYSDATE, id_cliente=?, total=?, direccion_envio=?, id_medio_pago=?, nro_tarjeta=?, estado='P' WHERE id_transaccion=?");

            pstmt.setInt(1, entity.getItem());
            pstmt.setInt(2, entity.getProduct());
            pstmt.setInt(3, entity.getQuantity());
            pstmt.setBigDecimal(4, entity.getPrice());
            pstmt.setBigDecimal(5, entity.getSubTotal());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcTransactionDetRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    
    }

    @Override
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity<Integer> get(Integer id) {
        TransactionDet retValue = null;
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM transacciones_det WHERE id_transaccion = ?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new TransactionDet(rs.getInt("id_transaccion"), rs.getInt("item"), rs.getInt("id_producto"), rs.getInt("cantidad"), rs.getBigDecimal("precio"), rs.getBigDecimal("sub_total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcTransactionDetRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

    @Override
    public Collection<TransactionDet> getAll() {
        Collection<TransactionDet> retValue = new ArrayList();
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM transacciones_cab");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new TransactionDet(rs.getInt("id_transaccion"), rs.getInt("item"), rs.getInt("id_producto"), rs.getInt("cantidad"), rs.getBigDecimal("precio"), rs.getBigDecimal("sub_total")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcTransactionDetRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }
}
