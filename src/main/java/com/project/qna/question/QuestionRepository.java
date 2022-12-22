package com.project.qna.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.member.Member;



public interface QuestionRepository extends JpaRepository<Question, Integer> {

	Optional<Question> findByQuestionNo (Integer questionNo);
    Question findByTitle(String title);

    
	//Optional<Question> findByBoardNo (Integer boardNo);
	List<Question> findByBoardNo (Integer boardNo);
	
    Question findByTitleAndContent(String title, String content);
    List<Question> findByTitleLike(String title);
    
    //boardNo추가
    Page<Question> findByBoardNo(Integer boardNo,Pageable pageable);
    Page<Question> findByHideAndBoardNo(Integer hide,Pageable pageable, Integer boardNo);
    
    
    //Page<Question> findAll(Pageable pageable);
    Page<Question> findByHide(Integer hide,Pageable pageable);
	Page<Question> findByBoardNoAndMemId(Integer boardNo, Pageable pageable,Member memId);

    
}
