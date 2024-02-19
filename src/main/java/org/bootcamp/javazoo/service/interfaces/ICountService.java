package org.bootcamp.javazoo.service.interfaces;

import org.bootcamp.javazoo.dto.response.CountFollowersDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;

public interface ICountService {
    CountFollowersDto getFollowersListCount(Integer userId);
}
