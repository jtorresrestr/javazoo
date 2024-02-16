package org.bootcamp.javazoo.service.impl;

import org.bootcamp.javazoo.dto.SellerDto;
import org.bootcamp.javazoo.dto.UserDto;
import org.bootcamp.javazoo.dto.response.FollowersListDto;
import org.bootcamp.javazoo.entity.Seller;
import org.bootcamp.javazoo.entity.User;
import org.bootcamp.javazoo.exception.NotFoundException;
import org.bootcamp.javazoo.repository.interfaces.IUserRepository;
import org.bootcamp.javazoo.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public FollowersListDto getFollowedList(Integer userId) {
        User user =  userRepository.getById(userId);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        List<UserDto> sellers = user.getFollowed().stream()
                .map(UserDto::convertUserToUserDto)
                .toList();

        return new FollowersListDto(userId, user.getName(), sellers);
    }
}
