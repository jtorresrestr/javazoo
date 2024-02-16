package org.bootcamp.javazoo.service.interfaces;
import org.bootcamp.javazoo.dto.SellerDto;
import org.bootcamp.javazoo.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<SellerDto> getFollowedList(int userId);
}
