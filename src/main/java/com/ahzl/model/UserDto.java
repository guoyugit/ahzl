package com.ahzl.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {
 @NotNull(message = "用户名不能为空")
 private String userName;
 @NotNull(message = "密码不能为空")
 @Length(min=8,message = "密码长度不能少于8")
 private String password;
}
