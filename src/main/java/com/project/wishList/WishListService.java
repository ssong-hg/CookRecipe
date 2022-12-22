package com.project.wishList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.DataNotFoundException;
import com.project.api.CookRecipe;
import com.project.api.CookRecipeRepository;
import com.project.createCookRecipe.CreateCookRecipeRepository;
import com.project.member.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WishListService {
	
	@Autowired
	WishListRepository wishListRepository;
	@Autowired
	CookRecipeRepository cookRecipeRepository;
	@Autowired
	CreateCookRecipeRepository createCookRecipeRepository;
	
	
	
	public List getWishList(Member member) {
		List<WishList> wishList = this.wishListRepository.findByMember(member); 
				return wishList;
	}
	public void delete(WishList wishList) {
		this.wishListRepository.delete(wishList);
	}
	public WishList getWishList(Integer wishListNo) {
		Optional<WishList> wishList = this.wishListRepository.findById(wishListNo);
		if(wishList.isPresent()) {
			return wishList.get();
		}else {
			throw new DataNotFoundException("wishList not found");
		}
	}
	
	
	public void setWishLisForCookRecipe(Member member, Integer rcpSeq){
		WishList wl = new WishList();
		
		
		wl.setMember(member);
		
		wl.setRcpSeq(rcpSeq);
		log.info("222"+wl.getRcpSeq());
		wishListRepository.save(wl);
		
	}
	
	
	public void setWishLisForCreateCookRecipe(Member member, Integer rcpSeq){
		WishList wl = new WishList();
		
		
		wl.setMember(member);
		wl.setRcpSeq(rcpSeq);
		wishListRepository.save(wl);
		
	}
	
	public WishList getWishList(Member member, Integer rcpSeq) {
		WishList wl = this.wishListRepository.findByMemberAndRcpSeq(member, rcpSeq);
		
		return wl;
	}
	public Page<WishList> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("readCount"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.wishListRepository.findAll(pageable);
	}
	public Page<WishList> getMyWishList(Member member, int page){
		List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("readCount"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.wishListRepository.findByMember(member, pageable);
	}
	
	
	
}
