package com.usersmanagement.users.application.ports.input;

import com.usersmanagement.users.domain.model.User;

public interface UserProviderUseCase {
    User getLoggedUser();

}
