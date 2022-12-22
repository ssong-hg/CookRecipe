package com.project.createCookRecipe;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.createCookRecipe.utill.MediaUtils;
import com.project.createCookRecipe.utill.UploadFileUtils;
import com.project.foodBorad.coment.ComentForm;
import com.project.member.Member;
import com.project.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/createrecipe")
public class CreateCookRecipeController {
	@Autowired
	CreateCookRecipeService createCookRecipeService;
	@Autowired 
	MemberService memberService;
	
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@GetMapping("/createcookrecipe")
	
	public String list(Model model) throws Exception {
		List<CreateCookRecipe> createCr = this.createCookRecipeService.getList();

		
		
		
		
		
		
		
		model.addAttribute("createCr", createCr);
		
		return "create/createRecipeList";
	}
	
	
	@GetMapping("/register")
	public String registerForm(Model model, CreateCookRecipe createCookRecipe) {
		CreateCookRecipe ccr = new CreateCookRecipe();
		
		
		String[] rcpWay = {"볶기", "굽기", "끓이기", "찌기", "튀기기", "기타"};
		List<String> rcpWay2 = new ArrayList<>(Arrays.asList(rcpWay)); // 배열을 LIST로 변환
		model.addAttribute("rcpWay2", rcpWay2); //요리방법
	
		String[] rcpPat = {"반찬", "국&찌개", "일품", "밥", "후식", "기타"};
		List<String> rcpPat2 = new ArrayList<>(Arrays.asList(rcpPat));
		model.addAttribute("rcpPat2", rcpPat2); //요리 분류
		
		
		
		model.addAttribute("ccr",ccr);
		
		

		return "create/createForm";
	}
	@GetMapping("/detail/{rcpSeq}")

	public String deatil(Model model, @PathVariable("rcpSeq")Long rcpSeq, HttpSession session, ComentForm comentForm) {
		CreateCookRecipe cr = this.createCookRecipeService.getCreateCookRecipeForRcpSeq(rcpSeq);
		
		
		cr.setReadCount(cr.getReadCount() + 1);
		createCookRecipeService.updataCreateCookRcp(cr);
		
		log.info("#########################:"+cr.getRcpNm());
		log.info("#########################:"+cr.getReadCount());
		
		model.addAttribute("createCookRecipe", cr);
		
		return "create/createdetail";
	}
	
	
	@PostMapping("/register")
	public String register(CreateCookRecipe createCookRecipe, Model model, HttpSession session
			) throws Exception {
		
		log.info("##################################getRcpPat2 " + createCookRecipe.getRcpPat2());
		log.info("##################################createCookRecipe.getMenualText() : " + createCookRecipe.getMenualText());
		
		String[] texts = createCookRecipe.getMenualText();
		log.info("##################################register " + texts);
		for (String text : texts) {
			log.info("register " + text);
		}

		
		
		
		
		String[] files = createCookRecipe.getFiles();
		log.info("##################################register " + files);
		for (String file : files) {
			log.info("register " + file);
		}

		Member member =(Member) session.getAttribute("member");
		
		this.createCookRecipeService.regist(member, createCookRecipe);

		model.addAttribute("msg", "등록이 완료되었습니다.");
		this.memberService.setMemberGrade(member);
		return "redirect:/createrecipe/createcookrecipe";
	}
	
	@ResponseBody
	@PostMapping(path = "/uploadAjax", produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		
		log.info("uploadPath: " + uploadPath);
		log.info("getOriginalFilename: " +  file.getOriginalFilename());
		log.info("getBytes: " + file.getBytes());
		String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		log.info("getOriginalFilename: " +  file.getOriginalFilename());
		log.info("getBytes: " + file.getBytes());
		return new ResponseEntity<String>(savedName, HttpStatus.CREATED);
	}
	
	@ResponseBody
	@GetMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		ResponseEntity<byte[]> entity = null;

		log.info("FILE NAME: " + fileName);

		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

			MediaType mediaType = MediaUtils.getMediaType(formatName);

			HttpHeaders headers = new HttpHeaders();

			File file = new File(uploadPath + fileName);

			if (mediaType != null) {
				headers.setContentType(mediaType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}

			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@GetMapping("/getAttach/{rcpSeq}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("rcpSeq") Long rcpSeq) throws Exception {
		log.info("getAttach rcpSeq: " + rcpSeq);

		return createCookRecipeService.getAttach(rcpSeq);
	}
	
}
