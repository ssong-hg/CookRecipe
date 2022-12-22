package com.project.api;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.project.foodBorad.coment.Coment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class CookRecipe {
	@Id
	private	String	rcpSeq	;
	@Column(length=10)
	@ColumnDefault("0")
	private Integer readCount;
	
	@OneToMany(mappedBy = "cookRecipe",
			cascade = CascadeType.REMOVE)
	private List<Coment> comentList;
	
	@ColumnDefault("0")
	private double StarScoreAvg;
	
	
	private LocalDateTime createDate;
	
	@Column(length=2000)
	private	String	rcpNm	;
	@Column(length=2000)
	private	String	rcpWay2	;
	@Column(length=2000)
	private	String	rcpPat2	;
	@Column(length=2000)
	private	String	infoWgt	;
	@Column(length=2000)
	private	String	infoEng	;
	@Column(length=2000)
	private	String	infoCar	;
	@Column(length=2000)
	private	String	infoPro	;
	@Column(length=2000)
	private	String	infoFat	;
	@Column(length=2000)
	private	String	infoNa	;
	@Column(length=2000)
	private	String	hashTag	;
	@Column(length=2000)
	private	String	attFileNoMain	;
	@Column(length=2000)
	private	String	attFileNoMk	;
	@Column(length=2000)
	private	String	rcpPartsDtls	;
	@Column(length=2000)
	private	String	manual01	;
	@Column(length=2000)
	private	String	manualImg01	;
	@Column(length=2000)
	private	String	manual02	;
	@Column(length=2000)
	private	String	manualImg02	;
	@Column(length=2000)
	private	String	manual03	;
	@Column(length=2000)
	private	String	manualImg03	;
	@Column(length=2000)
	private	String	manual04	;
	@Column(length=2000)
	private	String	manualImg04	;
	@Column(length=2000)
	private	String	manual05	;
	@Column(length=2000)
	private	String	manualImg05	;
	@Column(length=2000)
	private	String	manual06	;
	@Column(length=2000)
	private	String	manualImg06	;
	@Column(length=2000)
	private	String	manual07	;
	@Column(length=2000)
	private	String	manualImg07	;
	@Column(length=2000)
	private	String	manual08	;
	@Column(length=2000)
	private	String	manualImg08	;
	@Column(length=2000)
	private	String	manual09	;
	@Column(length=2000)
	private	String	manualImg09	;
	@Column(length=2000)
	private	String	manual10	;
	@Column(length=2000)
	private	String	manualImg10	;
	@Column(length=2000)
	private	String	manual11	;
	@Column(length=2000)
	private	String	manualImg11	;
	@Column(length=2000)
	private	String	manual12	;
	@Column(length=2000)
	private	String	manualImg12	;
	@Column(length=2000)
	private	String	manual13	;
	@Column(length=2000)
	private	String	manualImg13	;
	@Column(length=2000)
	private	String	manual14	;
	@Column(length=2000)
	private	String	manualImg14	;
	@Column(length=2000)
	private	String	manual15	;
	@Column(length=2000)
	private	String	manualImg15	;
	@Column(length=2000)
	private	String	manual16	;
	@Column(length=2000)
	private	String	manualImg16	;
	@Column(length=2000)
	private	String	manual17	;
	@Column(length=2000)
	private	String	manualImg17	;
	@Column(length=2000)
	private	String	manual18	;
	@Column(length=2000)
	private	String	manualImg18	;
	@Column(length=2000)
	private	String	manual19	;
	@Column(length=2000)
	private	String	manualImg19	;
	@Column(length=2000)
	private	String	manual20	;
	@Column(length=2000)
	private	String	manualImg20	;
	

}
