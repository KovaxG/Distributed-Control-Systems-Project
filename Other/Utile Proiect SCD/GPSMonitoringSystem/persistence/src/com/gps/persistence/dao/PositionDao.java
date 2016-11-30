package com.gps.persistence.dao;

import com.gps.persistence.dto.Position;

/**
 * @author radu.miron
 * @since 10/8/13
 */
public interface PositionDao {

    void savePosition(Position position) throws Exception;

}
