package org.imooc.service.impl;

import org.imooc.bean.Business;
import org.imooc.bean.Page;
import org.imooc.constant.CategoryConst;
import org.imooc.dao.BusinessDao;
import org.imooc.dao.DicDao;
import org.imooc.dto.BusinessDto;
import org.imooc.dto.BusinessListDto;
import org.imooc.service.BusinessService;
import org.imooc.util.CommonUtil;
import org.imooc.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    BusinessDao businessDao;
    @Override
    public List<Business> queryBusinessAll(Business business) {
        List<Business> businesses = businessDao.queryBusinessAll(business);
        System.out.println(business);
        System.out.println(businesses);
        return businesses;
    }
    public void delete(Long id){
         businessDao.delete(id);

    }
    public Business queryOne(Long id){
       return  businessDao.queryOne(id);
    }

    @Override
    public Integer saveBusiness(Business business) {
        return businessDao.saveBusiness(business);
    }

    @Override
    public Integer updateBusinesses(Business business) {
        return businessDao.updateBusinesses(business);
    }
}
