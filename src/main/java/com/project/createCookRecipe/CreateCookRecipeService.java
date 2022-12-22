package com.project.createCookRecipe;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.project.member.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CreateCookRecipeService {

	@Autowired
	CreateCookRecipeRepository createCookRecipeRepository;
	
	public void regist(Member member,CreateCookRecipe ccr) throws Exception{
		CreateCookRecipe createCookRcp = new CreateCookRecipe();
		log.info("############################테스트###############################1 ");
		createCookRcp.setMember(member); //작성자 set
		log.info("############################테스트###############################2 ");
		createCookRcp.setRcpNm(ccr.getRcpNm()); //요리 이름 set
		createCookRcp.setRcpPartsDtls(ccr.getRcpPartsDtls()); //재료 상세 set
		createCookRcp.setRcpWay2(ccr.getRcpWay2());
		createCookRcp.setRcpPat2(ccr.getRcpPat2());
		
		
		createCookRcp.setReadCount(0); // readCount 0으로 세팅
		
		createCookRcp.setCreateDate(LocalDateTime.now()); // 작성 시간
		log.info("############################테스트###############################3 :" +ccr.getMenualTexts());

		String[] menualTexts = ccr.getMenualText();
		
		if(menualTexts == null) {
			return;
		}
		for (String text : menualTexts) {
			MenualText menualText = new MenualText();
			menualText.setMenualText(text);
			log.info("################createCookRcp.getRcpSeq() :"+createCookRcp.getRcpSeq());
			createCookRcp.addMenualText(menualText);
			
		}
		
		String[] menualFiles = ccr.getFiles();
		
		if(menualFiles == null) {
			return;
		}
		for (String fileName : menualFiles) {
			Menual menual = new Menual();
			menual.setFullName(fileName);
			
			createCookRcp.addMenual(menual);
		}
			
		this.createCookRecipeRepository.save(createCookRcp);
	}
	
	public CreateCookRecipe getCreateCookRecipe(Long rcpSeq) throws Exception{
		
		return this.getCreateCookRecipe(rcpSeq);
	}
		
	public List<CreateCookRecipe> getList() throws Exception{
		return this.createCookRecipeRepository.findAll(Sort.by(Direction.DESC, "rcpSeq"));
	}
	
	public List<String> getAttach(Long rcpSeq) throws Exception {
		CreateCookRecipe creatCr =  this.getCreateCookRecipe(rcpSeq);
		
		List<Menual> itemFiles = creatCr.getMenuals();
		
		List<String> attachList = new ArrayList<String>(); //파일 이름 추출
		for(Menual menual : itemFiles) {
			attachList.add(menual.getFullName());
		}
		
		return attachList;
	}
	
	public CreateCookRecipe getCreateCookRecipeForRcpSeq(Long rcpSeq) {
		CreateCookRecipe cr = this.createCookRecipeRepository.findByRcpSeq(rcpSeq);
		
		return cr;
	}
	
	public void updataCreateCookRcp(CreateCookRecipe cookRecipe) {
		createCookRecipeRepository.save(cookRecipe);
		
	}
	
	public int getMemberCount(Member member) {
		List<CreateCookRecipe> memberCountList = this.createCookRecipeRepository.findByMember(member);
		int memberCount = memberCountList.size();
		return memberCount;
		
	}
	
	public List<CreateCookRecipe> getCreateCookRecipeListForMember(Member member){
		List<CreateCookRecipe> ccrList = this.createCookRecipeRepository.findByMember(member);
		log.info("########## ccrList :"+ccrList);
		return ccrList;
	}
	
}
