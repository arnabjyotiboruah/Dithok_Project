package com.dithok.myCommerce.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="rules")
@EntityListeners(AuditingEntityListener.class)
public class RulesModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long rule_id;
	
	private String name;
	
	private String execSeq;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_RulesInPolicy"),name="policy_id", referencedColumnName = "policy_id")
	private PoliciesModel policy;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_RulesCreator"),name="creator_id", referencedColumnName = "id")
	private UserModel creator;
	
	private String triggerName;
	
	private String path;
	
	private String type;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	private boolean status;

	public long getRule_id() {
		return rule_id;
	}

	public void setRule_id(long rule_id) {
		this.rule_id = rule_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExecSeq() {
		return execSeq;
	}

	public void setExecSeq(String execSeq) {
		this.execSeq = execSeq;
	}

	public PoliciesModel getPolicy() {
		return policy;
	}

	public void setPolicy(PoliciesModel policy) {
		this.policy = policy;
	}
	
	

	

	public UserModel getCreator() {
		return creator;
	}

	public void setCreator(UserModel creator) {
		this.creator = creator;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public RulesModel(long rule_id, String name, String execSeq, PoliciesModel policy, UserModel creator,
			String triggerName, String path, String type, Date createdDate, Date modifiedDate, boolean status) {
		super();
		this.rule_id = rule_id;
		this.name = name;
		this.execSeq = execSeq;
		this.policy = policy;
		this.creator = creator;
		this.triggerName = triggerName;
		this.path = path;
		this.type = type;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
	}

	public RulesModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
	
	
	

}
