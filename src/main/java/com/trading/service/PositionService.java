package com.trading.service;

import com.trading.domain.SecurityPosition;

import java.util.List;

public interface PositionService {
    List<SecurityPosition> getAllCurrentPositions();
}
