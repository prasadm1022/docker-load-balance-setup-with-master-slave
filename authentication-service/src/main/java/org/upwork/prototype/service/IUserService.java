/*
 * Copyright 2022 Prasad Madusanka Basnayaka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.upwork.prototype.service;

import org.upwork.prototype.domain.dto.RoleDTO;
import org.upwork.prototype.domain.dto.UserDTO;
import org.upwork.prototype.domain.response.Response;
import org.upwork.prototype.domain.response.ResponseError;

/**
 * User Service
 *
 * @author prasadm
 * @since 14 June 2022
 */

public interface IUserService
{
    Response<UserDTO> searchUser( String username ) throws ResponseError;

    Response<RoleDTO> searchRole( String roleName ) throws ResponseError;

    Response<UserDTO> saveUser( UserDTO opUser ) throws ResponseError;

    Response<RoleDTO> saveRole( RoleDTO role ) throws ResponseError;

    Response<Void> addRoleToUser( String username, String roleName ) throws ResponseError;
}
