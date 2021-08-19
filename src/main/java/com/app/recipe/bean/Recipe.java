package com.app.recipe.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = Access.WRITE_ONLY)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String category;

    @NotEmpty
    @ElementCollection
    @Size(min = 1, message = "Provide at least 2 ingredients")
    private List<String> ingredients;

    @NotEmpty
    @ElementCollection
    @Size(min = 1, message = "Provide at least 3 directions")
    private List<String> directions;

    @JsonProperty("date")
    @LastModifiedDate
    private LocalDateTime dateTime = LocalDateTime.now();

    @LastModifiedBy
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ADDED_BY")
    private User user;
}
