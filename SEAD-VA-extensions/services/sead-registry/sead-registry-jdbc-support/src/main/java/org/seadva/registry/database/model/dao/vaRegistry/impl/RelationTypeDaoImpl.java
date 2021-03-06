package org.seadva.registry.database.model.dao.vaRegistry.impl;

import org.seadva.registry.database.common.DBConnectionPool;
import org.seadva.registry.database.common.ObjectPool;
import org.seadva.registry.database.model.dao.vaRegistry.RelationTypeDao;
import org.seadva.registry.database.model.obj.vaRegistry.RelationType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * DAO for table: RelationType.
 * @author autogenerated
 */

public class RelationTypeDaoImpl implements RelationTypeDao {

    protected Connection getConnection() throws SQLException {
        return connectionPool.getEntry();
    }
    public RelationTypeDaoImpl(){
        connectionPool = DBConnectionPool.getInstance();
    }


    protected ObjectPool<Connection> connectionPool = null;

    @Override
    public RelationType getRelationType(String relationName) {
        RelationType relationType = new RelationType();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();

            statement = connection.prepareStatement("Select * from relation_type where relation_element=?");
            statement.setString(1, relationName);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                relationType.setId(resultSet.getString("relation_type_id"));
                relationType.setRelationElement(resultSet.getString("relation_element"));
                relationType.setRelationSchema(resultSet.getString("relation_schema"));
                break;
            }


        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //  log.warn("Unable to close statement", e);
                }
                statement = null;
            }
            connectionPool.releaseEntry(connection);

        }
        return relationType;
    }
}

