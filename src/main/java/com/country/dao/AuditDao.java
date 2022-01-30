package com.country.dao;

import com.country.model.DAOAudit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditDao extends CrudRepository<DAOAudit, Integer> {

    List<DAOAudit> findByUsername(String username);

}
