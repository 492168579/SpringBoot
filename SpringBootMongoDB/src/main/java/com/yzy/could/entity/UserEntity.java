package com.yzy.could.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection="user")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    @Id
    private Long id;
    private String userName;
    private String passWord;


}
