package org.imooc.service.impl;

import org.imooc.bean.Ad;
import org.imooc.dao.AdDao;
import org.imooc.dto.AdDto;
import org.imooc.service.AdService;
import org.imooc.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    AdDao adDao;

    @Override
    public List<Ad> adList() {
        List<Ad> adList = adDao.selectAdList();
        return adList;
    }

    @Override
    public void remove(Long id) {
        adDao.remove(id);
    }

    public List<Ad> search(Ad ad){
        return adDao.search(ad);
    }

    @Override
    public Ad selectOne(Long id) {
        return adDao.selectOne(id);
    }

    @Override
    public Integer saveAd(Ad ad) {
        return adDao.saveAd(ad);
    }

    public Integer add(Ad ad){
        return adDao.add(ad);
    }
}
