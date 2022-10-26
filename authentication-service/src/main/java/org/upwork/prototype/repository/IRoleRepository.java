package org.upwork.prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.upwork.prototype.entity.OpRole;

/**
 * Role Repository
 *
 * @author prasadm
 * @since 14 June 2022
 */

public interface IRoleRepository extends JpaRepository<OpRole,Long>, JpaSpecificationExecutor<OpRole>
{
    OpRole findByName( String name );
}
