package com.lcwd.user.service.entities;

import com.lcwd.user.service.dtos.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "id")
    private String userId;

    private String name;
    private String email;
    private String about;

    @Transient  //for not to save in db table
    private List<Rating> ratings;
}
