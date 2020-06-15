package com.example.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name = "Warning")
public class Warning {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 告警编号
	@Column
	private String classification;// 告警分类
	@Column
	private String townshipname;// 乡镇名
	@Column
	private String villagename;// 村名
	@Column
	private String sitename;// 站点名
	@Column
	private String level; // 告警级别
	@Column
	private String content;// 告警内容
	@Column
	private String effectiveness;// 是否有效
	@Column
	private String deal;// 是否处理
	@Column
	@JSONField(format="dd-MM-yyyy HH:mm:ss")
	private Timestamp time;// 告警时间
	
	public Warning() {}
	
	public Warning(String classification,
			String townshipname,String villagename,String sitename,
			String effectiveness) {
		this.classification = classification;
		this.townshipname = townshipname;
		this.villagename = villagename;
		this.sitename = sitename;
		this.effectiveness = effectiveness;
		
	}
	
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getTownshipname() {
		return townshipname;
	}
	public void setTownshipname(String townshipname) {
		this.townshipname = townshipname;
	}
	public String getVillagename() {
		return villagename;
	}
	public void setVillagename(String villagename) {
		this.villagename = villagename;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEffectiveness() {
		return effectiveness;
	}
	public void setEffectiveness(String effectiveness) {
		this.effectiveness = effectiveness;
	}
	public String getDeal() {
		return deal;
	}
	public void setDeal(String deal) {
		this.deal = deal;
	}
	


}
