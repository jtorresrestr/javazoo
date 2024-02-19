package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.response.MessageDto;
import org.bootcamp.javazoo.entity.User;
import org.bootcamp.javazoo.exception.BadRequestException;
import org.bootcamp.javazoo.repository.interfaces.IUserRepository;
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
    private final IUserRepository userRepository;

    public SellerServiceImpl(ISellerRepository sellerRepository, IUserRepository userRepository) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
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
    public MessageDto addFollow(Integer userId, Integer userToFollowId) {
        if (userId.equals(userToFollowId)) {
            throw new BadRequestException("A user cannot follow themselves.");
        }
        User user = userRepository.getById(userId);
        Seller seller = sellerRepository.findById(userToFollowId);
        if(user == null) {
            throw new NotFoundException("User not found");
        }
        if (seller == null) {
            throw new NotFoundException("Seller not found");
        }

        boolean alreadyFollowing = user.getFollowed().stream().anyMatch(s -> s.getId().equals(seller.getId()));

        if (alreadyFollowing) {
            throw new BadRequestException("The user is already following the seller.");
        }

        sellerRepository.addFollow(user, seller);
//        userRepository.addFollowed(user, seller);

        return new MessageDto("Ok");

    }

}
