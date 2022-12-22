package com.project.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.foodBorad.FoodIngredientsService;
import com.project.member.Member;
import com.project.member.grade.GradeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class ApiController {
	 	@Autowired
	    private CookRecipeRepository cookRecipeRepository;
	 	@Autowired
	 	private FoodIngredientsService foodIngredientsService;
	 	@Autowired
	 	private GradeService gradeService;
	 	
	 	
	   @GetMapping("/api")
	   public String index(){
	       return "api/index";
	   }

	   @PostMapping("/api")
	   public String load_save(Model model){
	       String result = "";
	     log.info("1단계");
	       try {
	    	   
	         
	           URL url = new URL("http://openapi.foodsafetykorea.go.kr/api/954a72f180374d2da12f/COOKRCP01/json/1/1000");
//	           URL url = new URL("http://openapi.foodsafetykorea.go.kr/api/954a72f180374d2da12f/COOKRCP01/json/1001/1057");
	           BufferedReader bf;
	           bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
	           result = bf.readLine();
	           JSONParser jsonParser = new JSONParser();
	           JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
	           JSONObject CookRecipe01 = (JSONObject)jsonObject.get("COOKRCP01");
	           String totalCount=(String)CookRecipe01.get("total_count");
	           JSONObject subResult = (JSONObject)CookRecipe01.get("RESULT");	         
	           JSONArray infoArr = (JSONArray) CookRecipe01.get("row");
	           log.info("2단계");
	           for(int i=0;i<infoArr.size();i++){
	               JSONObject tmp = (JSONObject)infoArr.get(i);
	               CookRecipe infoObj=new CookRecipe();
	               infoObj.setRcpSeq((String)tmp.get("RCP_SEQ"));
	               infoObj.setRcpNm	((String)tmp.get("RCP_NM"));
	               infoObj.setRcpWay2((String)tmp.get("RCP_WAY2"));
	               infoObj.setRcpPat2((String)tmp.get("RCP_PAT2"));
	               infoObj.setInfoWgt((String)tmp.get("INFO_WGT"));
	               infoObj.setInfoEng((String)tmp.get("INFO_ENG"));
	               infoObj.setInfoCar((String)tmp.get("INFO_CAR"));
	               infoObj.setInfoPro((String)tmp.get("INFO_PRO"));
	               infoObj.setInfoFat((String)tmp.get("INFO_FAT"));
	               infoObj.setInfoNa((String)tmp.get("INFO_NA"));
	               infoObj.setHashTag((String)tmp.get("HASH_TAG"));
	               infoObj.setAttFileNoMain((String)tmp.get("ATT_FILE_NO_MAIN"));
	               infoObj.setAttFileNoMk((String)tmp.get("ATT_FILE_NO_MK"));
	               infoObj.setRcpPartsDtls((String)tmp.get("RCP_PARTS_DTLS"));
	               infoObj.setManual01((String)tmp.get("MANUAL01"));
	               infoObj.setManualImg01((String)tmp.get("MANUAL_IMG01"));
	               infoObj.setManual02((String)tmp.get("MANUAL02"));
	               infoObj.setManualImg02((String)tmp.get("MANUAL_IMG02"));
	               infoObj.setManual03((String)tmp.get("MANUAL03"));
	               infoObj.setManualImg03((String)tmp.get("MANUAL_IMG03"));
	               infoObj.setManual04((String)tmp.get("MANUAL04"));
	               infoObj.setManualImg04((String)tmp.get("MANUAL_IMG04"));
	               infoObj.setManual05((String)tmp.get("MANUAL05"));
	               infoObj.setManualImg05((String)tmp.get("MANUAL_IMG05"));
	               infoObj.setManual06((String)tmp.get("MANUAL06"));
	               infoObj.setManualImg06((String)tmp.get("MANUAL_IMG06"));
	               infoObj.setManual07((String)tmp.get("MANUAL07"));
	               infoObj.setManualImg07((String)tmp.get("MANUAL_IMG07"));
	               infoObj.setManual08((String)tmp.get("MANUAL08"));
	               infoObj.setManualImg08((String)tmp.get("MANUAL_IMG08"));
	               infoObj.setManual09((String)tmp.get("MANUAL09"));
	               infoObj.setManualImg09((String)tmp.get("MANUAL_IMG09"));
	               infoObj.setManual10((String)tmp.get("MANUAL10"));
	               infoObj.setManualImg10((String)tmp.get("MANUAL_IMG10"));
	               infoObj.setManual11((String)tmp.get("MANUAL11"));
	               infoObj.setManualImg11((String)tmp.get("MANUAL_IMG11"));
	               infoObj.setManual12((String)tmp.get("MANUAL12"));
	               infoObj.setManualImg12((String)tmp.get("MANUAL_IMG12"));
	               infoObj.setManual13((String)tmp.get("MANUAL13"));
	               infoObj.setManualImg13((String)tmp.get("MANUAL_IMG13"));
	               infoObj.setManual14((String)tmp.get("MANUAL14"));
	               infoObj.setManualImg14((String)tmp.get("MANUAL_IMG14"));
	               infoObj.setManual15((String)tmp.get("MANUAL15"));
	               infoObj.setManualImg15((String)tmp.get("MANUAL_IMG15"));
	               infoObj.setManual16((String)tmp.get("MANUAL16"));
	               infoObj.setManualImg16((String)tmp.get("MANUAL_IMG16"));
	               infoObj.setManual17((String)tmp.get("MANUAL17"));
	               infoObj.setManualImg17((String)tmp.get("MANUAL_IMG17"));
	               infoObj.setManual18((String)tmp.get("MANUAL18"));
	               infoObj.setManualImg18((String)tmp.get("MANUAL_IMG18"));
	               infoObj.setManual19((String)tmp.get("MANUAL19"));
	               infoObj.setManualImg19((String)tmp.get("ANUAL_IMG19"));
	               infoObj.setManual20((String)tmp.get("MANUAL20"));
	               infoObj.setManualImg20((String)tmp.get("MANUAL_IMG20"));
	               cookRecipeRepository.save(infoObj);
	               log.info("넣는중");
	           }

	      
	       
	       log.info("cookRecipe 입력 성공");
	       
	       foodIngredientsService.setFoodIngredients();
	       log.info("식재료 입력 완료");
	       
	       gradeService.setGrade();
	       log.info("등급 입력 완료");
	       
	       
	       }catch(Exception e) {
	           e.printStackTrace();   
	          
	       }
	       return "redirect:/main";
	   }
	
}
