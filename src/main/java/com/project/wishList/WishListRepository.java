package com.project.wishList;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.member.Member;

public interface WishListRepository extends JpaRepository<WishList, Integer>{

	List<WishList> findByMember(Member member);
	WishList findByMemberAndRcpSeq(Member member, Integer rcpSeq);
	WishList findByWishListNo(Integer wishListNo);
	Page <WishList> findAll(Pageable pageable);
	Page <WishList> findByMember(Member member , Pageable pageable);
}
