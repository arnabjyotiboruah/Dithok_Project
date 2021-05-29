package com.dithok.myCommerce.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

	@Entity
	@Table(name="user_role")
	@EntityListeners(AuditingEntityListener.class)
	public class UserRoleModel{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "user_id", referencedColumnName = "id")
		private UserModel user;

		@ManyToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "role_id", referencedColumnName = "id")
		private RoleModel role;
		
		
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


		public RoleModel getRole() {
			return role;
		}


		public void setRole(RoleModel role) {
			this.role = role;
		}


		/**
		 * @param id
		 * @param user
		 * @param role
		 */
		public UserRoleModel(long id, UserModel user, RoleModel role) {
			this.id = id;
			this.user = user;
			this.role = role;
		}


		/**
		 * 
		 */
		public UserRoleModel() {
			super();
			// TODO Auto-generated constructor stub
		}
	
	
}
