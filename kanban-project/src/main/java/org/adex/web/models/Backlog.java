package org.adex.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String projectIdentifier;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

}
