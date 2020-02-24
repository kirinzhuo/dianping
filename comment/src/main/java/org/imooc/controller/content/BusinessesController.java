package org.imooc.controller.content;

import org.apache.ibatis.annotations.Delete;
import org.imooc.bean.Business;
import org.imooc.constant.DicTypeConst;
import org.imooc.constant.PageCodeEnum;
import org.imooc.dto.BusinessDto;
import org.imooc.service.BusinessService;
import org.imooc.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/businesses")
public class BusinessesController {
    @Autowired
    BusinessService businessService;
    @RequestMapping(method = RequestMethod.GET)
    public String init(HttpServletRequest request) {
        Business business = new Business();
        String title = null;
        if(request.getParameter("title") != null){
             title = request.getParameter("title");
             business.setTitle(title);
        }else{
            business.setTitle(null);
        }
        List<Business> businesses = businessService.queryBusinessAll(business);
        request.setAttribute("list",businesses);
        return "/content/businessList";
    }
    @RequestMapping("addPage")
    public String addPage() {
        return "/content/businessAdd";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id")Long id){
        businessService.delete(id);
        return "redirect:/businesses";
    }
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String modifyInit(@PathVariable("id")Long id,HttpServletRequest request){
        Business business = businessService.queryOne(id);
        business.setImgFileName(null);
        request.setAttribute("modifyObj",business);
        return "/content/businessModify";
    }

    /**
     * value = "/{title}/{subtitle}/{city}/{category}/{img}/{price}/{distance}/{desc}",
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveBusinesses(HttpServletRequest request){
        String title = request.getParameter("title");
        String subtitle = request.getParameter("subtitle");
        String city = request.getParameter("city");
        String category = request.getParameter("category");
        String img = null;
        String priceStr = request.getParameter("price");
        String distanceStr = request.getParameter("distance");
        String desc = request.getParameter("desc");

        Double price = new Double(priceStr);
        Integer distance = new Integer(distanceStr);
        Business business = new Business();
        business.setTitle(title);
        business.setCategory(category);
        business.setCity(city);
        business.setSubtitle(subtitle);
        business.setPrice(price);
        business.setDistance(distance);
        business.setDesc(desc);
        business.setImgFileName(img);
        businessService.saveBusiness(business);

        System.out.println("ceshikirin");
        return "redirect:/businesses";
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public String updateBusinesses(@PathVariable("id")Long id, HttpServletRequest request){

        String title = request.getParameter("title");
        String subtitle = request.getParameter("subtitle");
        String city = request.getParameter("city");
        String category = request.getParameter("category");
        String img = null;
        String priceStr = request.getParameter("price");
        String distanceStr = request.getParameter("distance");
        String desc = request.getParameter("desc");



        Double price = new Double(priceStr);
        Integer distance = new Integer(distanceStr);
        Business business = new Business();
        business.setId(id);
        business.setTitle(title);
        business.setCategory(category);
        business.setCity(city);
        business.setSubtitle(subtitle);
        business.setPrice(price);
        business.setDistance(distance);
        business.setDesc(desc);
        business.setImgFileName(img);
        businessService.updateBusinesses(business);

        return "redirect:/businesses";
    }


}