package app.taxifinderapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.taxifinderapi.dto.UserResponseDto;
import app.taxifinderapi.model.Address;
import app.taxifinderapi.model.Town;
import app.taxifinderapi.model.User;
import app.taxifinderapi.repository.AddressRepository;
import app.taxifinderapi.repository.PushTokenRepository;
import app.taxifinderapi.repository.TownRepository;
import app.taxifinderapi.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PushTokenRepository pushTokenRepository;

    @Autowired
    TownRepository townRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    QuestionService questionService;

    // @Transactional
    // public UserResponseDto addUser(UserRequest userRequest) {
    //     User addedUser = userRepository
    //             .save(new User(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword()));

    //     PushToken pushToken = new PushToken();
    //     ArrayList<PushToken> pushTokens = new ArrayList<>();

    //     pushToken.setPush_token_value(userRequest.getPushToken());
    //     pushToken.setUser(addedUser);
    //     pushTokenRepository.save(pushToken);

    //     pushTokens.add(pushToken);

    //     UserResponseDto userResponseDto = new UserResponseDto();
    //     userResponseDto.setUser_id(addedUser.getUser_id());
    //     userResponseDto.setName(addedUser.getName());
    //     userResponseDto.setEmail(addedUser.getEmail());
    //     userResponseDto.setPushToken(pushTokens);
    //     return userResponseDto;
    // }

    public List<UserResponseDto> findUsersByTown(String townName) {
        String townNameFormatted = questionService.word(townName);
        System.out.println(townNameFormatted);
        Town town = townRepository.findByName(townNameFormatted);
        List<Address> addresses = addressRepository.findByTown(town);
        ArrayList<User> users = new ArrayList<>();

        for (Address address : addresses) {
            users.add(address.getUser());
        }

        ArrayList<UserResponseDto> userResponseDtos = new ArrayList<>();

        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            System.out.println(user.getName());
            userResponseDto.setUser_id(user.getUser_id());
            userResponseDto.setName(user.getName());
            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setPushToken(user.getPushTokens());
            userResponseDtos.add(userResponseDto);
        }
        
        return userResponseDtos;
    }
}
