package org.bootcamp.javazoo.service.impl;

import org.springframework.stereotype.Service;
import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.repository.interfaces.ISellerRepository;
import org.bootcamp.javazoo.service.interfaces.ISellerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements ISellerService {
    private final ISellerRepository sellerRepository;

    public SellerServiceImpl(ISellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    @Override
    public FollowersListDto getFollowersListService(Integer userId, String order) {
        Seller seller = sellerRepository.findById(userId);
        if (seller == null) {
            throw new NotFoundException("Seller not found");
        }
        List<UserDto> followers;
        if (order == null) {
            followers = seller.getFollowers().stream()
                    .map(UserDto::convertUserToUserDto)
                    .toList();
        } else if (order.equals("name_asc")) {
            followers = seller.getFollowers().stream()
                    .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                    .map(UserDto::convertUserToUserDto)
                    .toList();
        } else {
            followers = seller.getFollowers().stream()
                    .sorted((o1, o2) -> o2.getName().compareTo(o1.getName()))
                    .map(UserDto::convertUserToUserDto)
                    .toList();

        }

        return new FollowersListDto(userId, seller.getName(), followers);
    }
}
