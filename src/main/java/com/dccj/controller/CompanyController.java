package com.dccj.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dccj.entity.CarStation;
import com.dccj.filter.PageBean;
import com.dccj.service.CarStationService;
import com.dccj.uitl.JsonResult;
import com.dccj.uitl.PageObject;
import com.dccj.entity.CompanyEntity;
import com.dccj.entity.CompanyPamEntity;
import com.dccj.entity.ExamineForcomEntity;
import com.dccj.service.CompanyService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyService companyService;
    @Resource
    private CarStationService carStationService;

    @RequestMapping("companyUI")
    @RequiresPermissions("company:view")
    public String comUI() {
        return "company/company_list";
    }
    /**
     * 跳转到编辑页面
     */
    @RequestMapping("editCompanyUI")
    public String editCompanyUI() {
        return "company/company_edit";
    }

    @RequestMapping("/findPageObjects")
    @ResponseBody
    public RespEntity findPageObjects(@RequestParam(value = "name",defaultValue = "") String name, @RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "pageSize") Integer pageSize) {
        RespEntity respEntity = new RespEntity();
        if (page != null && pageSize != null)
            PageHelper.startPage(page, pageSize);
        List<CarStation> list = carStationService.selectAllByNameLike(name);
        respEntity.setData(new PageBean<CarStation>(list));
        return respEntity;
    }

    /**
     * 根据id查询信息，用于回显
     */
    @RequestMapping("queryStation")
    @ResponseBody
    public RespEntity queryStation() {
        RespEntity respEntity = new RespEntity();
        respEntity.putListData(carStationService.selectAllByNameLike(""));
        return respEntity;
    }

    @RequestMapping("doSaveCompany")
    @ResponseBody
    public JsonResult doSaveCompany(CarStation entity) {
        carStationService.insertSelective(entity);
        return new JsonResult();
    }

    @RequestMapping("doUpdateCompany")
    @ResponseBody
    public JsonResult doUpdateCompany(CarStation project) {
        carStationService.updateByPrimaryKeySelective(project);
        return new JsonResult();
    }

    @RequestMapping("deleteCompany")
    @ResponseBody
    public JsonResult deleteCompany(Integer cid) {
        carStationService.deleteByPrimaryKey(cid);
        return new JsonResult();
    }

    @RequestMapping("/findCompanyRecord")
    @ResponseBody
    public Map<String, Object> findCompanyRecord(@ModelAttribute ExamineForcomEntity entity) {
        int page = entity.getPage();
        int total = entity.getTotal();
        int rows = entity.getRows();
        PageObject pageObject = new PageObject();
        pageObject.setPageCurrent(page);
        pageObject.setRowCount(total);
        pageObject.setPageSize(rows);
        Map<String, Object> map = companyService.findCompanyRecord(entity, pageObject);
        return map;
    }

    @RequestMapping("saveCompanyExobject")
    @ResponseBody
    public JsonResult saveCompanyExobject(ExamineForcomEntity entity) {
        companyService.saveCompanyExobject(entity);
        return new JsonResult();
    }

    @RequestMapping("findrecordCompanyById")
    @ResponseBody
    public JsonResult findrecordCompanyById(Integer eid) {
        Map<String, Object> map = companyService.findrecordCompanyById(eid);
        return new JsonResult(map);
    }

    @RequestMapping("/findComtnumberPar")
    @ResponseBody
    public CompanyPamEntity findComtnumberPar() {
        CompanyPamEntity entity = companyService.findPamforCom();
        return entity;
    }

}
