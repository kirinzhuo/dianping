package org.imooc.service;

import org.imooc.bean.Ad;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AdService {
    public List<Ad> adList();
    public void remove(Long id);
    public List<Ad> search(Ad ad);
    Ad selectOne(Long id);
    Integer saveAd(Ad ad);
    Integer add(Ad ad);
}
