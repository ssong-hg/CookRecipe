package com.project.foodBorad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.api.CookRecipe;
import com.project.api.CookRecipeService;
import com.project.foodBorad.coment.ComentForm;
import com.project.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FoodBoradController {

	@Autowired
	MemberService memverService;
	
	@Autowired
	CookRecipeService cookRecipeService;	
	
	@Autowired
	FoodIngredientsService foodIngredientsService;
	
	// 검색
	@GetMapping("/search")
	public String search(@RequestParam String search, Model model, @RequestParam(value="page", defaultValue="0") int page) {
		// 검색조건에서 공백(" ")이 있는경우
		if(search.contains(" ")==true) {
			String[] searchArray = search.split(" "); // 공백으로 구분 후 배열 세팅
			Page<CookRecipe> paging = cookRecipeService.getRcpPartsDtlsList(searchArray[0], page); // 0번째 STring 세팅
	        // 나머지 String 세팅(복수건 세팅가능)
			for(int i=1; i<searchArray.length;i++) {
				paging.and((cookRecipeService.getRcpPartsDtlsList(searchArray[i], page))); 
	            model.addAttribute("search", search);
	            model.addAttribute("paging", paging);
	            log.info("@@@@@@@@@@@@검색결과 :"+paging.getSize());
	            
	            return"search/search";
	            
			}
		}else {
			Page<CookRecipe> paging = cookRecipeService.getRcpPartsDtlsList(search, page);
	    	model.addAttribute("search", search);
	    	model.addAttribute("paging", paging);
	    	log.info("@@@@@@@@@@@@검색결과 :"+paging.getSize());
	    	
	    	return"search/search";
	    }
		
		return"실패";
	}

	
	@GetMapping("/categorymain")
	public String category(@RequestParam String search, Model model) {
		
		if(search == "rcpWay2") {
			String[] aStr = {"볶기", "굽기", "끓이기", "찌기", "튀기기", "기타"};
			model.addAttribute("search", search);

			return"search/category";
		} else if(search == "rcpPat2") {
			String[] aStr = {"반찬", "국&찌개", "일품", "밥", "후식", "기타"};
			model.addAttribute("search", search);

			return"search/category";
		} else {
			
			String[] aStr = {"육류", "해물", "채소", "유제품/달걀", "가공식품", "쌀", "밀가루", "건어물", "버섯", "과일", "콩/견과", "곡류", "기타"};
			
			model.addAttribute("search", search);

			return"search/category";
		}
	   
	}
	
	@GetMapping("/category")
	public String postCategory(@RequestParam String category, Model model, @RequestParam(value="page", defaultValue="0") int page) {
		
		List<String> rcpWay2 = new ArrayList<>(Arrays.asList("볶기", "굽기", "끓이기", "찌기", "튀기기", "기타"));
		List<String> rcpPat2 = new ArrayList<>(Arrays.asList("반찬", "국&찌개", "일품", "밥", "후식", "기타"));	
		List<String> rcpPartsDtls = new ArrayList<>(Arrays.asList
				("육류", "해물", "채소", "유제품/달걀", "가공식품", "쌀", "밀가루", "건어물", "버섯", "과일", "콩/견과", "곡류", "기타"));
		
		if(rcpWay2.contains(category)) {
			String search = "rcpWay2";
			Page<CookRecipe> paging = cookRecipeService.getRcpWay2List(category, page);
			model.addAttribute("paging", paging);
			model.addAttribute("search", search);
			model.addAttribute("category", category);
			log.info("사이즈 : "+ paging.getSize());
			return "search/category3";
		}else if(rcpPat2.contains(category)) {
			String search = "rcpPat2";
			Page<CookRecipe> paging = cookRecipeService.getRcpPat2List(category, page);
			model.addAttribute("paging", paging);
			model.addAttribute("search", search);
			model.addAttribute("category", category);
			return "search/category3";
		}else {
			String search = "rcpPartsDtls";
			model.addAttribute("search", search);
			
			return "search/category2";
		}
		
		
		
	}
	
	@GetMapping("/detail/{rcpSeq}")
	
	public String deatil(Model model, @PathVariable("rcpSeq")String rcpSeq, HttpSession session,ComentForm comentForm) {
		CookRecipe cr = this.cookRecipeService.getCookRecipeForRcpSeq(rcpSeq);
		
		if(cr.getReadCount() == null) {
			cr.setReadCount(0);
		}
		
		cr.setReadCount(cr.getReadCount() + 1);
		cookRecipeService.updataCookRcp(cr);
		
		log.info("#########################:"+cr.getRcpNm());
		log.info("#########################:"+cr.getReadCount());
		
		model.addAttribute("cookRecipe", cr);
		
		return "search/searchDetail";
	}
	
	@GetMapping("/category2")
	public String category2(@RequestParam String category, Model model, @RequestParam(value="page", defaultValue="0") int page,  @PageableDefault(size=10, sort="readCount", direction = Sort.Direction.DESC)Pageable pageable) {

		List<String> fIList = this.foodIngredientsService.getFoodIngredientsForIngredientsName(category);
		List<CookRecipe> originList = cookRecipeService.getCookRecipeForRcpPartsDtlsContaining(category);
		
		originList = this.cookRecipeService.getCookRecipeForRcpPartsDtlsContaining(fIList.get(1).trim());// 0번째 STring 세팅
		log.info("*******************************fIList.get(0) :"+fIList.get(0).trim());
        // 나머지 String 세팅(복수건 세팅가능)
        for(int i=1; i<fIList.size();i++) {
        	originList.addAll(cookRecipeService.getCookRecipeForRcpPartsDtlsContaining(fIList.get(i).trim()));
        	log.info("*******************************fIList.get(i) :"+fIList.get(i).trim());
        }
        
        log.info("*******************************originList :"+originList.size());
        log.info("*******************************1 :");
        
        HashSet<CookRecipe> distinctData = new HashSet<CookRecipe>(originList);//추출한 List
         
        List<CookRecipe> cookRecipe = new ArrayList<CookRecipe>(distinctData);
        
        Collections.sort(cookRecipe, new Comparator<CookRecipe>() {

			@Override
			public int compare(CookRecipe o1, CookRecipe o2) {
				return o2.getReadCount() - o1.getReadCount();   //desc
				// return o1.getReadCount() - o2.getReadCount();   //asc
			}
        	
		});// rcpSeq로 인한 정렬
        // cookRecipe.sort(Comparator)
        log.info("*******************************222222222 :");
    	
    	log.info("*******************************cookRecipe.get(1) :"+cookRecipe.get(1).getRcpNm()+cookRecipe.get(1).getReadCount());
    	final int start=(int)pageable.getOffset();
    	final int end = Math.min((start + pageable.getPageSize()), cookRecipe.size());
    	final Page<CookRecipe> paging = new PageImpl<>(cookRecipe.subList(start, end), pageable, cookRecipe.size());
 
        
    	String search = "rcpPartsDtls";
		model.addAttribute("cookRecipe", cookRecipe);
		model.addAttribute("category", category);
		model.addAttribute("pageable", pageable);
		model.addAttribute("paging", paging);
		
		model.addAttribute("search", search);
		log.info("*******************************cookRecipe :"+cookRecipe.size());
		
	   return"search/category2";
	}

	
	
}
