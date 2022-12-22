package com.project.wishList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.project.api.CookRecipe;
import com.project.createCookRecipe.CreateCookRecipe;
import com.project.member.Member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class WishList {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WISH_LIST_SEQ")
    @SequenceGenerator(sequenceName = "wish_list_seq", allocationSize = 1, name = "WISH_LIST_SEQ")
    private Integer wishListNo;
	
	
	@OneToOne
	@JoinColumn(name="memId")
	private Member member;
	
	@Column(length=19)
	private Integer rcpSeq;
}
