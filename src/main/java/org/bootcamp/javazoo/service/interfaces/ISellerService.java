package org.bootcamp.javazoo.service.interfaces;

import org.bootcamp.javazoo.dto.response.FollowersListDto;

public interface ISellerService {
    FollowersListDto getFollowersListService(Integer userId);
}