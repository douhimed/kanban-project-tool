package org.adex.web.models;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Project name is required")
	private String name;
	
	@NotBlank(message="Project identifier is required")
	@Size(min=4, max=5, message="Please use 4 to 5 characters")
	@Column(updatable=false, unique= true)
	private String projectIdentifier;
	
	@NotBlank(message="Project description is required")
	private String description;
	
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date endDate;
	
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date createdAt;
	
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date updatedAt;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")

	private Backlog backlog;
	
	
	@PrePersist
	protected void onCreate() {
		this.setCreatedAt(new Date()); 
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.setUpdatedAt(new Date());
	}

}
