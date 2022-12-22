package com.project.foodBorad.coment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.member.Member;



public interface ComentRepository extends JpaRepository<Coment, Integer> {

	Coment findByComentNo(Integer comentNo);
	
	List<Coment> findByMemId(Member member);
}
