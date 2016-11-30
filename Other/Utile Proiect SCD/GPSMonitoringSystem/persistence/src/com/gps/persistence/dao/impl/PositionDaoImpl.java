package com.gps.persistence.dao.impl;

import com.gps.persistence.dao.PositionDao;
import com.gps.persistence.dto.Position;
import com.gps.persistence.util.PersistenceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author radu.miron
 * @since 10/8/13
 */
@Repository
public class PositionDaoImpl implements PositionDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public PositionDaoImpl(){
        System.out.println("PDI created!");
    }

    @Override
    public void savePosition(Position position) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + PersistenceConstants.POSITION_TABLE);
        sql.append(" ( " + PersistenceConstants.USER_ID + ", " + PersistenceConstants.LATITUDE + ", ");
        sql.append(PersistenceConstants.LONGITUDE + ", " + PersistenceConstants.DATE + ") ");
        sql.append("VALUES (:userId, :latitude, :longitude, :date);");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", position.getUserId());
        params.put("latitude", position.getLatitude());
        params.put("longitude", position.getLongitude());
        params.put("date", position.getDate());

        try{
            jdbcTemplate.update(sql.toString(), params);
        } catch (Exception e){
            throw e;
        }
    }
}
