package com.project.foodBorad.coment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.DataNotFoundException;
import com.project.api.CookRecipe;
import com.project.createCookRecipe.CreateCookRecipe;
import com.project.member.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComentService {

	private final ComentRepository comentRepository;
	
	public void create(Member member,CookRecipe cookRecipe, String content) {
        Coment coment =new Coment();
        coment.setMemId(member);
        coment.setContent(content);
        coment.setCreateDate(LocalDateTime.now());  //now함수가아닌 newDate()
        coment.setCookRecipe(cookRecipe);
        this.comentRepository.save(coment);
    }
	public void createC(Member member,CreateCookRecipe createCookRecipe, String content) {
        Coment coment =new Coment();
        coment.setMemId(member);
        coment.setContent(content);
        coment.setCreateDate(LocalDateTime.now());  //now함수가아닌 newDate()
        coment.setCreateCookRecipe(createCookRecipe);
        this.comentRepository.save(coment);
    }
	public Coment getComent(Integer comentNo) {
        Optional<Coment> coment = this.comentRepository.findById(comentNo);
        if (coment.isPresent()) {
            return coment.get();
        } else {
            throw new DataNotFoundException("coment not found");
        }
    }
    public String getRcpSeq(Integer comentNo) {
    	Coment coment= this.comentRepository.findByComentNo(comentNo);
    	
    	String rcqSeq = coment.getCookRecipe().getRcpSeq();
    	
    	return rcqSeq;
    }
    public Long getRcpSeqC(Integer comentNo) {
    	Coment coment= this.comentRepository.findByComentNo(comentNo);
    	
    	Long rcqSeq = coment.getCreateCookRecipe().getRcpSeq();
    	
    	return rcqSeq;
    }
    public void modify(Integer comentNo, String content) {
    	Coment coment = this.comentRepository.findByComentNo(comentNo);
    	coment.setContent(content);
    	coment.setCreateDate(LocalDateTime.now());
        this.comentRepository.save(coment);
    }
    
    public void delete(Coment coment) {
    	
        this.comentRepository.delete(coment);
    }
    
    public int getMemberCount(Member member) {
    	List<Coment> memberCountList = this.comentRepository.findByMemId(member);
    	
    	return memberCountList.size();  	
    	
    }

}
