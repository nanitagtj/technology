package com.onclass.tecnologia.infrastructure.driven.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("technology")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyEntity {
    @Id
    private Long id;
    private String name;
    private String description;
}
