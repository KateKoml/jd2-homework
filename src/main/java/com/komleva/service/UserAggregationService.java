package com.komleva.service;

import com.komleva.domain.User;

import java.util.List;
import java.util.Map;

public interface UserAggregationService {
    public Map<String, Map<String, String>> getUsersAndPhones();
}
