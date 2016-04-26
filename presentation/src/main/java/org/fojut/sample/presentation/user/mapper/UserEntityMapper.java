package org.fojut.sample.presentation.user.mapper;

import org.fojut.sample.data.user.dto.UserDto;
import org.fojut.sample.presentation.base.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.user.model.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link UserDto} (in the domain layer) to
 * {@link UserEntity} in the presentation layer.
 */
@PerActivity
public class UserEntityMapper {

    @Inject
    public UserEntityMapper() {
    }

    /**
     * Transform a {@link UserDto} into an {@link UserEntity}.
     *
     * @param userDto Object to be transformed.
     * @return {@link UserEntity}.
     */
    public UserEntity transform(UserDto userDto){
        if (userDto == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        return userEntity;
    }

    /**
     * Transform a Collection of {@link UserDto} into a Collection of {@link UserEntity}.
     *
     * @param usersCollection Objects to be transformed.
     * @return List of {@link UserEntity}.
     */
    public List<UserEntity> transform(List<UserDto> usersCollection) {
        List<UserEntity> userModelsCollection;

        if (usersCollection != null && !usersCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>();
            for (UserDto userDto : usersCollection) {
                userModelsCollection.add(transform(userDto));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}
