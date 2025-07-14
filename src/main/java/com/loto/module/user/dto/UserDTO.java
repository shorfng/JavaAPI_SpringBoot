package com.loto.module.user.dto;

import com.loto.module.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 文件名：UserDTO.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：DTO - 用户类<p>
 * 创建人：蓝田_Loto
 */

@Getter
@Setter
@Builder
public class UserDTO {
    private String userName;
    private List<User> userList;

    // 修改构造函数为 public
    public UserDTO(String userName, List<User> userList) {
        this.userName = userName;
        this.userList = userList;
    }
}
