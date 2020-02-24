package org.imooc.dao;

import org.imooc.bean.Ad;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AdDao {
    List<Ad> selectAdList();
    void remove(Long id);
    List<Ad> search(Ad ad);
    Ad selectOne(Long id);
    Integer saveAd(Ad ad);
    Integer add(Ad ad);
}