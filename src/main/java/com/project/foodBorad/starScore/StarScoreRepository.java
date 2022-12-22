package com.project.foodBorad.starScore;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.member.Member;

public interface StarScoreRepository extends JpaRepository<StarScore, Integer>{
	
	List<StarScore> findByMember(Member member);
	StarScore findByRcpSeq(String rcpSeq);
	
	StarScore findByMemberAndRcpSeq(Member member, Integer rcpSeq);
	
	List<StarScore> findByRcpSeq(Integer rcpSeq);
}
