package org.adex.web.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, unique = true)
    private String projectSequence;
    @NotBlank(message = "Please include a summary")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date dueDate;

    @Column(updatable = false)
    private String projectIdentifier;

    private Date createdAt;
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "backlogId", updatable = false, nullable = false)
    @JsonIgnore
    @ToString.Exclude
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
