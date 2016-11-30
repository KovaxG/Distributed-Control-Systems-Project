package com.gps.service.position.impl;

import com.gps.persistence.dao.PositionDao;
import com.gps.service.position.PositionService;
import com.gps.service.util.JsonToObjectConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author radu.miron
 * @since 10/8/13
 */
@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void savePosition(String jsonPosition) throws Exception {
        positionDao.savePosition(JsonToObjectConverters.convertJsonToPosition(jsonPosition));
    }
}
