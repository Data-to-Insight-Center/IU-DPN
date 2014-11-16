package org.seadva.registry.database.model.dao.vaRegistry.impl;

import org.apache.log4j.Logger;
import org.seadva.registry.database.common.DBConnectionPool;
import org.seadva.registry.database.common.ObjectPool;
import org.seadva.registry.database.model.dao.vaRegistry.FixityDao;
import org.seadva.registry.database.model.obj.vaRegistry.File;
import org.seadva.registry.database.model.obj.vaRegistry.Fixity;
import org.seadva.registry.database.model.obj.vaRegistry.FixityPK;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for table: Fixity.
 * @author autogenerated
 */

public class FixityDaoImpl implements FixityDao {

    private static Logger log = Logger.getLogger(BaseEntityDaoImpl.class);

    protected Connection getConnection() throws SQLException {
        return connectionPool.getEntry();
    }
    public FixityDaoImpl(){
        connectionPool = DBConnectionPool.getInstance();
    }


    protected ObjectPool<Connection> connectionPool = null;

    @Override
    public List<Fixity> getFixities(String fileId) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Fixity> fixityList = new ArrayList<Fixity>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement("Select * from fixity where entity_id=?");
            statement.setString(1, fileId);
            ResultSet resultSet = null;
            resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Fixity fixity = new Fixity();
                FixityPK pk = new FixityPK();
                File file = new File();
                file.setId(resultSet.getString("entity_id"));
                pk.setEntity(file);
                pk.setType(resultSet.getString("type"));
                fixity.setId(pk);
                fixity.setValuestr(resultSet.getString("valueStr"));
                fixityList.add(fixity);
            }


        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.warn("Unable to close statement", e);
                }
                statement = null;
            }
            connectionPool.releaseEntry(connection);
        }
        return fixityList;
    }


    @Override
    public boolean putFixities(List<Fixity> fixities){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            for(Fixity fixity:fixities){
                statement = connection.prepareStatement("INSERT INTO fixity values(?,?,?)");
                statement.setString(1, fixity.getId().getEntity().getId());
                statement.setString(2, fixity.getId().getType());
                statement.setString(3, fixity.getValuestr());
                statement.executeUpdate();
                statement.close();
            }

            log.debug("Done resetting unfinished raw notifications");
        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.warn("Unable to close statement", e);
                }
                statement = null;
            }
            connectionPool.releaseEntry(connection);

        }

        return true;
    }
}
