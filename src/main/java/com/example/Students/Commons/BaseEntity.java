package com.example.Students.Commons;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "uuid")
@SuperBuilder
public abstract class BaseEntity implements Serializable {
    @Id
    @SequenceGenerator(
            name = "id_sequence_generator",
            sequenceName = "id_sequence_generator",
            allocationSize = 1)
    Long id;

    @Column(name = "uuid",nullable = false,unique = true,length = 12)
    String uuid;
}
