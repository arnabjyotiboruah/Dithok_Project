package com.dithok.myCommerce.model;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

import java.util.Date;


//Entity model to store the order related data
@Entity
@Table(name="Email")
@EntityListeners(AuditingEntityListener.class)
public class EmailModel 

{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id",  referencedColumnName = "id")
	private UserModel user;
	

//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "order_id", referencedColumnName = "id", nullable = true)
//	private OrderModel order_id;
	
	@JoinColumn(name= "email")
	private String email;

	@JoinColumn(name= "email")
	private String body;

	
	@JoinColumn(name= "email")
	private String subject;
    
	@Column(columnDefinition = "varchar(30) default 'not sent'")
	private String email_status;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "order_date", referencedColumnName = "date_order_paced", nullable = true)
//    private OrderModel date_order_paced;
	
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
    	
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public String getEmail_Status() {
		return email_status;
	}

	public void setEmail_Status(String email_status) {
		this.email_status = email_status;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
    }
    
//    public OrderModel getOrder_id() {
//        return this.order_id;
//    }
//
//    public void setOrder_id(OrderModel order_id) {
//        this.order_id = order_id;
//	}
//	
//	
//
//    public OrderModel getDate_order_paced() {
//        return this.date_order_paced;
//    }
//
//    public void setDate_order_paced(OrderModel date_order_paced) {
//        this.date_order_paced = date_order_paced;
//    }
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

//	public EmailModel(OrderModel order_id) {
//        this.order_id = order_id;
//	}

	public EmailModel(UserModel user)
	{
		this.user = user;
	}

	public EmailModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	






	
	
}
	
	
