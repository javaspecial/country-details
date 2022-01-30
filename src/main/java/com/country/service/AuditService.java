package com.country.service;

import com.country.dao.AuditDao;
import com.country.model.AuditDTO;
import com.country.model.DAOAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {

    @Autowired
    private AuditDao auditDao;

    public List<DAOAudit> auditByUsername(AuditDTO audit) {
        List<DAOAudit> daoAudit = auditDao.findByUsername(audit.getUsername());
        return daoAudit;
    }

    public void saveAudit(AuditDTO auditDTO) {
        DAOAudit audit = new DAOAudit();
        audit.setUsername(auditDTO.getUsername());
        audit.setCode(auditDTO.getCode());
        audit.setName(auditDTO.getName());
        audit.setSymbol(auditDTO.getSymbol());
        audit.setExchangeRateIdr(auditDTO.getExchangeRateIdr());
        audit.setError(auditDTO.getError());
        auditDao.save(audit);
    }
}
