package com.dev.fitbooking.service.impl;

import com.dev.fitbooking.dao.FitnessRoomDao;
import com.dev.fitbooking.model.FitnessRoom;
import com.dev.fitbooking.service.FitnessRoomService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessRoomServiceImpl implements FitnessRoomService {
    private final FitnessRoomDao fitnessRoomDao;

    @Autowired
    public FitnessRoomServiceImpl(FitnessRoomDao fitnessRoomDao) {
        this.fitnessRoomDao = fitnessRoomDao;
    }

    @Override
    public void add(FitnessRoom fitnessRoom) {
        fitnessRoomDao.add(fitnessRoom);
    }

    @Override
    public FitnessRoom get(Long id) {
        return fitnessRoomDao.get(id).orElseThrow(()
                -> new RuntimeException("Can not get fitness room with id: " + id));
    }

    @Override
    public List<FitnessRoom> getAll() {
        return fitnessRoomDao.getAll();
    }
}
