package org.bootcamp.javazoo.service.interfaces;

import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.dto.response.MessageDto;

public interface ISellerService {
    FollowersListDto getFollowersListService(Integer userId);
    MessageDto addFollow(Integer userId, Integer userToFollowId);
}
