package org.imooc.dao;

import org.imooc.bean.Business;

import java.util.List;

public interface BusinessDao {
    List<Business> queryBusinessAll(Business business);
    void delete(Long id);
    Business queryOne(Long id);
    Integer saveBusiness(Business business);
    Integer updateBusinesses(Business business);
}
