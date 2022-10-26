package org.upwork.prototype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.upwork.prototype.entity.OpCustomer;

/**
 * Customer Repository
 *
 * @author prasadm
 * @since 29 May 2022
 */

@Repository
public interface ICustomerRepository extends JpaRepository<OpCustomer,Long>, JpaSpecificationExecutor<OpCustomer>
{
}
