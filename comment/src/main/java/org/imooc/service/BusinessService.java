package org.imooc.service;


import org.imooc.bean.Business;

import java.util.List;

public interface BusinessService {
    List<Business> queryBusinessAll(Business business);
    void delete(Long id);
    Business queryOne(Long id);
    Integer saveBusiness(Business business);
    Integer updateBusinesses(Business business);
}


