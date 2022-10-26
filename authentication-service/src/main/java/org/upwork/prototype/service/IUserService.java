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
