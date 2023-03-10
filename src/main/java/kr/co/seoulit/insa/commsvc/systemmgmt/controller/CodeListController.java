package kr.co.seoulit.insa.commsvc.systemmgmt.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kr.co.seoulit.insa.commsvc.systemmgmt.service.SystemMgmtService;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.CodeTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.DetailCodeTO;
import kr.co.seoulit.insa.commsvc.systemmgmt.to.AppointmentTO;


@RequestMapping("/systemmgmt/*")
@RestController
public class CodeListController {

   @Autowired
   private SystemMgmtService systemMgmtService;

   ModelMap map = null;

   @GetMapping("codelist")
   public ModelMap detailCodelist(@RequestParam("code") String code, HttpServletResponse response) {
      System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@메롱");
      map = new ModelMap();
      try {
         ArrayList<DetailCodeTO> detailCodeList = systemMgmtService.findDetailCodeList(code);

         map.put("detailCodeList", detailCodeList);
         map.put("errorMsg", "success");
         map.put("errorCode", 0);

      } catch (Exception dae) {
         map.clear();
         map.put("errorCode", -1);
         map.put("errorMsg", dae.getMessage());
      }
      return map;
   }

   @GetMapping("code/rest")
   public ModelMap detailCodelistRest(@RequestParam("code1") String code1, @RequestParam("code2") String code2,
		   @RequestParam("code3") String code3, HttpServletResponse response) {

      map = new ModelMap();
      try {

         ArrayList<DetailCodeTO> detailCodeList = systemMgmtService.findDetailCodeListRest(code1, code2, code3);
         map.put("detailCodeList", detailCodeList);
         map.put("errorMsg", "success");
         map.put("errorCode", 0);

      } catch (Exception dae) {
         map.clear();
         map.put("errorCode", -1);
         map.put("errorMsg", dae.getMessage());
      }
      return map;
   }


   @GetMapping("codelist/all")
   public ModelMap codelist(HttpServletRequest request, HttpServletResponse response) {
      map = new ModelMap();
      try {
         ArrayList<CodeTO> codeList = systemMgmtService.findCodeList();
         map.put("codeList", codeList);
         map.put("errorMsg", "success");
         map.put("errorCode", 0);

      } catch (DataAccessException dae) {
         map.clear();
         map.put("errorCode", -1);
         map.put("errorMsg", dae.getMessage());

      }
      return map;
   }


   // 발령구분 ANAME 가져와서 코드창에 뿌리기
   @GetMapping("codelist/appointment")
   public ModelMap findAppname(HttpServletRequest request, HttpServletResponse response) {
      map = new ModelMap();
      System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@발령구분");
      try {
         ArrayList<AppointmentTO> appointmentto = systemMgmtService.findAppList();
         System.out.println(appointmentto.get(0).getAppointmentName()); // 호봉승급
         map.put("appName", appointmentto);
         map.put("errorMsg", "success");
         map.put("errorCode", 0);
      } catch (DataAccessException dae) {
         map.clear();
         map.put("errorCode", -1);
         map.put("errorMsg", dae.getMessage());

      }
      return map;
   }

}