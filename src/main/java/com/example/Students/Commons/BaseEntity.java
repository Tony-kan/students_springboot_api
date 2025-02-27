package com.example.Students.Commons;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "uuid")
@SuperBuilder
public abstract class BaseEntity implements Serializable {
//    @Id
//    @SequenceGenerator(
//            name = "id_sequence_generator",
//            sequenceName = "id_sequence_generator",
//            allocationSize = 1)
//    @GeneratedValue(generator = "id_sequence_generator")
//    Long id;
//
//    @Column(name = "uuid",nullable = false,unique = true,updatable = false,length = 12)
//    String uuid;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable = false,updatable = false,unique = true,length = 12)
    UUID id;
}
