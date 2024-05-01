package interview.com.cathayunited.entity;

import java.util.Date;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "currency")
@Builder
@NoArgsConstructor // Thêm constructor mặc định
@AllArgsConstructor
public class Currency {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 255)
	private String code;

	@Column(nullable = false, length = 255)
	private String name;

	@Column(name = "rate_float", nullable = false)
	private float rate_float;

	@Column(nullable = false, length = 255)
	private String description;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt = new Date();

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRate_float() {
		return rate_float;
	}

	public void setRate_float(float rate_float) {
		this.rate_float = rate_float;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

}
