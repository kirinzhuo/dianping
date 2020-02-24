package org.imooc.controller.content;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.imooc.bean.Ad;
import org.imooc.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/ad")
public class AdController {
    @Autowired
    AdService adService;
    @RequestMapping
    public String init(Ad ad , HttpServletRequest request) {
        List<Ad> adList = adService.adList();
        request.setAttribute("list",adList);
        System.out.println("adlistAfter");
        return "/content/adList";
    }
    @RequestMapping("addInit")
    public String addInit(){
        return "/content/adAdd";
    }
    @RequestMapping("search")
    public String Search(Ad ad ,HttpServletRequest request){
        String title = null;
        title = request.getParameter("title");
        ad.setTitle(title);
       // System.out.println(title);
        List<Ad> adList = adService.search(ad);
        request.setAttribute("list",adList);
        return  "/content/adList";
    }
    @RequestMapping("remove")
    public String remove(HttpServletRequest request){
        String str = request.getParameter("id");
        Long id = new Long(str);
        adService.remove(id);
        return "redirect:/ad";

    }
    @RequestMapping("modifyInit")
    public String ModifyInit(HttpServletRequest request){
        String str = request.getParameter("id");
        Long id = new Long(str);
        Ad ad = adService.selectOne(id);
        if(ad != null){
            request.setAttribute("modifyObj",ad);
        }
        System.out.println(ad);
        return "/content/adModify";
    }
    @RequestMapping("modify")
    public String  modify(HttpServletRequest request){
        String idStr = request.getParameter("id");
        String title = request.getParameter("title");
        String link = request.getParameter("link");
        String weightStr = request.getParameter("weight");
        Long weight = new Long(weightStr);
        Long id = new Long(idStr);
        Ad ad = new Ad();
        ad.setId(id);
        ad.setTitle(title);
        ad.setImgFileName(null);
        ad.setLink(link);
        ad.setWeight(weight);
        System.out.println(title);
        adService.saveAd(ad);
        return "redirect:/ad";
    }
    @RequestMapping("add")
    public String Add(HttpServletRequest request){
        String title = request.getParameter("title");
        String link = request.getParameter("link");
        String weightStr = request.getParameter("weight");
        Long weight = new Long(weightStr);
        Ad ad = new Ad();
        ad.setTitle(title);
        ad.setImgFileName(null);
        ad.setLink(link);
        ad.setWeight(weight);
        System.out.println(title);
        adService.add(ad);
        return "redirect:/ad";
    }

    @RequestMapping(value = "downExcel",method = RequestMethod.GET)
    public String downExcel(HttpServletRequest request, HttpServletResponse response)throws IOException
    {
        //创建一个SXSSFWorkbook
        Workbook workbook = new XSSFWorkbook();
        //创建一个sheet
        Sheet sheet = workbook.createSheet();
        List<Ad> adList = adService.adList();
        int i = 0;
        //创建一行
        Row row = sheet.createRow(i++);
        //创建row行的第一个单元格
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("序号");
        //创建row行的第二个单元格
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("标题");
        Cell cell3 = row.createCell(2);
        cell3.setCellValue("链接地址");
        for (Ad ad : adList) {
            Row row1 = sheet.createRow(i++);
            Cell cell = row1.createCell(0);
            cell.setCellValue(ad.getId());
            Cell cell4 = row1.createCell(1);
            cell4.setCellValue(ad.getTitle());
            Cell cell5 = row1.createCell(2);
            cell5.setCellValue(ad.getLink());
        }

          //  FileOutputStream out = new FileOutputStream("f:/adListPOI.xlsx");
            OutputStream out = response.getOutputStream();
            //设置响应头
            response.reset();
            //实现页面响应里提示下载进度以及页面自动保存下载地址功能
            response.setHeader( "Content-disposition" , "attachment;filename=adListPOI.xlsx");
            response.setContentType("application/msexcel");
            workbook.write(out);
            // 操作结束，关闭文件
            out.close();

        return "redirect:/ad";

        }




    }


