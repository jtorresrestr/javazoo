package org.bootcamp.javazoo.service.interfaces;

import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.entity.Seller;

public interface ISellerService {
    FollowersListDto getFollowersListService(Integer userId);

    Seller getById(int sellerId);
}
