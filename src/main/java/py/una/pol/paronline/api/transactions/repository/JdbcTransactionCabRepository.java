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
import py.una.pol.paronline.commons.domain.entity.transactions.Transaction;
import py.una.pol.paronline.commons.utils.DataBaseUtil;

/**
 *
 * @author dlopez
 */
public class JdbcTransactionCabRepository implements TransactionCabRepository<Transaction, Integer>{

    private Connection connection;
    private PreparedStatement pstmt;

    public JdbcTransactionCabRepository() {
    }

    public JdbcTransactionCabRepository(Connection connection, PreparedStatement pstmt) {
        this.connection = connection;
        this.pstmt = pstmt;
    }

    @Override
    public void add(Transaction entity) {
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("INSERT INTO transacciones_cab(fecha, id_cliente, total, direccion_envio, id_medio_pago, nro_tarjeta, estado) VALUES (?, ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, entity.getNombre());
            pstmt.setInt(2, entity.getClient());
            pstmt.setBigDecimal(3, entity.getTotal());
            pstmt.setString(4, entity.getAddress());
            pstmt.setInt(5, entity.getPaymentMethod());
            pstmt.setString(6, entity.getCardNumber());
            pstmt.setString(7, entity.getState());
            

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
                Logger.getLogger(JdbcTransactionCabRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    @Override
    public void remove(Integer id) {
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("DELETE FROM transacciones_cab WHERE id_transaccion = ?");

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
                Logger.getLogger(JdbcTransactionCabRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    @Override
    public void update(Transaction entity) {
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("UPDATE transacciones_cab SET fecha=SYSDATE, id_cliente=?, total=?, direccion_envio=?, id_medio_pago=?, nro_tarjeta=?, estado='P' WHERE id_transaccion=?");

            pstmt.setInt(1, entity.getClient());
            pstmt.setBigDecimal(2, entity.getTotal());
            pstmt.setString(3, entity.getAddress());
            pstmt.setInt(4, entity.getPaymentMethod());
            pstmt.setString(5, entity.getCardNumber());
            pstmt.setString(6, entity.getCardNumber());

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
                Logger.getLogger(JdbcTransactionCabRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    
    }

    @Override
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity<Integer> get(Integer id) {
        Entity retValue = null;
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM transacciones_cab WHERE id_transaccion = ?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new Transaction(rs.getInt("id_transaccion"), "", null, rs.getInt("id_cliente"), rs.getBigDecimal("total"), rs.getString("direccion_envio"), rs.getInt("id_medio_pago"), rs.getString("nro_tarjeta"), rs.getString("estado"));
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
                Logger.getLogger(JdbcTransactionCabRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

    @Override
    public Collection<Transaction> getAll() {
        Collection<Transaction> retValue = new ArrayList();
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM transacciones_cab");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Transaction(rs.getInt("id_transaccion"), "", null, rs.getInt("id_cliente"), rs.getBigDecimal("total"), rs.getString("direccion_envio"), rs.getInt("id_medio_pago"), rs.getString("nro_tarjeta"), rs.getString("estado")));
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
                Logger.getLogger(JdbcTransactionCabRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }
    
    
    
}
