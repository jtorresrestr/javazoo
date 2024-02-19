package org.bootcamp.javazoo.service.impl;

import org.springframework.stereotype.Service;
import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.repository.interfaces.ISellerRepository;
import org.bootcamp.javazoo.service.interfaces.ISellerService;

import java.util.List;

@Service
public class SellerServiceImpl implements ISellerService {
    private final ISellerRepository sellerRepository;

    public SellerServiceImpl(ISellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    @Override
    public FollowersListDto getFollowersListService(Integer userId) {
        Seller seller = sellerRepository.findById(userId);
        if (seller == null) {
            throw new NotFoundException("Seller not found");
        }
        List<UserDto> followers = seller.getFollowers().stream()
                .map(UserDto::convertUserToUserDto)
                .toList();

        return new FollowersListDto(userId, seller.getName(), followers);
    }

    @Override
    public Seller getById(int sellerId){
        Seller seller = sellerRepository.findById(sellerId);
        if (seller == null) throw new NotFoundException("Seller not found");
        return seller;

    }
}
