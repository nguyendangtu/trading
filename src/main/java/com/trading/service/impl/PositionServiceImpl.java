package com.trading.service.impl;

import com.trading.domain.SecurityPosition;
import com.trading.repository.SecurityPositionRepository;
import com.trading.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("positionService")
public class PositionServiceImpl implements PositionService {

    @Autowired
    private SecurityPositionRepository positionRepository;

    @Override
    public List<SecurityPosition> getAllCurrentPositions() {
        return positionRepository.findAll(Sort.by(Sort.Direction.ASC, "account")
                                              .and(Sort.by(Sort.Direction.DESC, "instrument")));
    }
}
