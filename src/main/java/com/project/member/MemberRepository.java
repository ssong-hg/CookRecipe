package com.project.member;

import java.sql.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.member.grade.Grade;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	Optional<Member> findByMemId(String memId);
	Optional<Member> findByNickName(String nickName);
	Optional<Member> findByGrade(Grade grade );
	boolean existsByNickName(String nickName);
	
	
	Optional<Member> findByMemIdAndMemPw(String memId, String memPw);
	
	//Find Id
	Optional<Member> findByNickNameAndBirth(String nickName, Date birth);
	//Find Pw
	Optional<Member> findByNickNameAndMemId(String nickName, String memId);
	
}
