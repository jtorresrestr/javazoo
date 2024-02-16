package org.bootcamp.javazoo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bootcamp.javazoo.dto.UserDto;

import java.util.List;

@AllArgsConstructor
@Data
public class FollowersListDto {
    private Integer user_id;
    private String user_name;
    private List<UserDto> followers;
}
