package org.bootcamp.javazoo.service.interfaces;
import org.bootcamp.javazoo.dto.SellerDto;
import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;

import java.util.List;

public interface IUserService {
    FollowersListDto getFollowedList(Integer userId);
}
