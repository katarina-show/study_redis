package com.sjw.cache.dao;

import com.sjw.cache.entity.TCountDetail;

public interface TCountDetailDao {

    int insertVisitCount(TCountDetail tCountDetail);
    int getVisitCount();

}