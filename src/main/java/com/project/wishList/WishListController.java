package com.project.wishList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.api.CookRecipe;
import com.project.api.CookRecipeService;
import com.project.createCookRecipe.CreateCookRecipe;
import com.project.createCookRecipe.CreateCookRecipeService;
import com.project.member.Member;
import com.project.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WishListController {
	
	@Autowired
	WishListService wishListService;
	@Autowired
	WishListRepository wishListRepository;
	@Autowired
	MemberService memverService;
	@Autowired
	CookRecipeService cookRecipeService;
	@Autowired
	CreateCookRecipeService createCookRecipeService;
	
	@GetMapping("/wishlist")
	@ResponseBody
	public String WishList (HttpSession session, Model model, @RequestParam("rcpSeq")String rcpSeq) {
		Member member =(Member) session.getAttribute("member");
		int rcpSeqNo = Integer.parseInt(rcpSeq);
		log.info("############ajax 진입 성공 " );
		
		if(this.wishListService.getWishList(member, rcpSeqNo) != null) {
			log.info("############ :"+this.wishListService.getWishList(member, rcpSeqNo).getWishListNo());
			return "notNull";
		}		
		if(rcpSeqNo<10000) {
			log.info("############ ajax 진입 성공 1" );
			Integer cookRecipe =rcpSeqNo;
			wishListService.setWishLisForCookRecipe(member, cookRecipe);
			return "cookRecipe";
		}else if(rcpSeqNo>10000){
			log.info("############ ajax 진입 성공 2" );
			Integer createCookRecipe = rcpSeqNo;
			wishListService.setWishLisForCreateCookRecipe(member, createCookRecipe);
			return "createCookRecipe";
		}else {
			return "notNull";
		}
		
	}
	
	@GetMapping("/mywishlist")
	public String MyWishList(HttpSession session, Model model, 
			@RequestParam(value="page", defaultValue="0") int page,  
			@PageableDefault(size=10, sort="readCount", direction = Sort.Direction.DESC)Pageable pageable) {
		Member member =(Member) session.getAttribute("member");
		
		List<WishList> wlList = wishListService.getWishList(member);
		
		List<String> rcpSeqList = new ArrayList<String>();
		for(int i=0; i<wlList.size();i++) {
			if(wlList.get(i).getRcpSeq()<10000) {
				rcpSeqList.add(Integer.toString(wlList.get(i).getRcpSeq()));
			}			
		}
		
		List<CookRecipe> cookRecipe = new ArrayList<CookRecipe>();
						
		for(int i=0; i<rcpSeqList.size();i++) {
			cookRecipe.add(cookRecipeService.getCookRecipeForRcpSeq(rcpSeqList.get(i)));
		}
		
		
		Collections.sort(cookRecipe, new Comparator<CookRecipe>() {

			@Override
			public int compare(CookRecipe o1, CookRecipe o2) {
				return o2.getReadCount() - o1.getReadCount();   //desc
				// return o1.getReadCount() - o2.getReadCount();   //asc
			}
        	
		});
		final int start=(int)pageable.getOffset();
    	final int end = Math.min((start + pageable.getPageSize()), cookRecipe.size());
    	final Page<CookRecipe> paging = new PageImpl<>(cookRecipe.subList(start, end), pageable, cookRecipe.size());
		
		
		log.info("######## rcpSeqList : "+rcpSeqList.size());
		log.info("######## originList : "+cookRecipe.size());
		
		model.addAttribute("pageable", pageable);
		model.addAttribute("paging", paging);
		model.addAttribute("session", session);
		
		
		return "myPage/wishList";
	}
	@GetMapping("/mycreatewishlist")
	public String MyCreateWishList(HttpSession session, Model model, 
			@RequestParam(value="page", defaultValue="0") int page,  
			@PageableDefault(size=10, sort="readCount", direction = Sort.Direction.DESC)Pageable pageable) {
		Member member =(Member) session.getAttribute("member");
		
		List<WishList> wlList = wishListService.getWishList(member);

		List<Long> intergerList = new ArrayList<Long>();
		for(int i=0; i<wlList.size();i++) {
			if(wlList.get(i).getRcpSeq()>=10000) {
				intergerList.add(Long.valueOf(wlList.get(i).getRcpSeq()));
			}			
		}
		
 
		List<CreateCookRecipe> createCookRecipe = new ArrayList<CreateCookRecipe>();
		
		for(int i=0; i<intergerList.size();i++) {
			createCookRecipe.add(createCookRecipeService.getCreateCookRecipeForRcpSeq(intergerList.get(i)));
		}
		
		Collections.sort(createCookRecipe, new Comparator<CreateCookRecipe>() {

			@Override
			public int compare(CreateCookRecipe o1, CreateCookRecipe o2) {
				return o2.getReadCount() - o1.getReadCount();   //desc
				// return o1.getReadCount() - o2.getReadCount();   //asc
			}
        	
		});
		final int start=(int)pageable.getOffset();
    	final int end = Math.min((start + pageable.getPageSize()), createCookRecipe.size());
    	final Page<CreateCookRecipe> paging = new PageImpl<>(createCookRecipe.subList(start, end), pageable, createCookRecipe.size());
		
		
		
		log.info("######## rcpSeqList : "+intergerList.size());
		log.info("######## originList : "+createCookRecipe.size());
		
		model.addAttribute("pageable", pageable);
		model.addAttribute("paging", paging);
		model.addAttribute("session", session);
		
		
		return "myPage/createWishList";
	}
	
	@GetMapping("/mywishlistdelete/{rcpSeq}")
	public String DeleteWishList(HttpSession session, Model model, @PathVariable("rcpSeq") String rcpSeq) {
		
		log.info("#####################rcpSeq" + rcpSeq);
		model.addAttribute("session", session);
		Member member =(Member) session.getAttribute("member");
		Integer wRcpseq = Integer.parseInt(rcpSeq);
		WishList wishList = wishListService.getWishList(member, wRcpseq);	

		this.wishListService.delete(wishList);
		return "redirect:/mywishlist";
	}
	
}
