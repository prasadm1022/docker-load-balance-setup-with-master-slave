package org.upwork.prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.upwork.prototype.entity.OpUser;

/**
 * User Repository
 *
 * @author prasadm
 * @since 14 June 2022
 */

public interface IUserRepository extends JpaRepository<OpUser,Long>, JpaSpecificationExecutor<OpUser>
{
    OpUser findByUsername( String username );
}
