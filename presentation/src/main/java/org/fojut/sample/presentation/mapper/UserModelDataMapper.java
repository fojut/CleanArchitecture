package org.fojut.sample.presentation.mapper;

import org.fojut.sample.data.entity.UserEntity;
import org.fojut.sample.presentation.internal.di.scope.PerActivity;
import org.fojut.sample.presentation.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Mapper class used to transform {@link org.fojut.sample.data.entity.UserEntity} (in the domain layer) to
 * {@link UserModel} in the presentation layer.
 */
@PerActivity
public class UserModelDataMapper {

    @Inject
    public UserModelDataMapper() {
    }

    /**
     * Transform a {@link UserEntity} into an {@link UserModel}.
     *
     * @param userEntity Object to be transformed.
     * @return {@link UserModel}.
     */
    public UserModel transform(UserEntity userEntity){
        if (userEntity == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setPassword(userEntity.getPassword());
        return userModel;
    }

    /**
     * Transform a Collection of {@link UserEntity} into a Collection of {@link UserModel}.
     *
     * @param usersCollection Objects to be transformed.
     * @return List of {@link UserModel}.
     */
    public List<UserModel> transform(List<UserEntity> usersCollection) {
        List<UserModel> userModelsCollection;

        if (usersCollection != null && !usersCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>();
            for (UserEntity userEntity : usersCollection) {
                userModelsCollection.add(transform(userEntity));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}
