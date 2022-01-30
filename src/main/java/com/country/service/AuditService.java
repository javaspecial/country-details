package com.country.service;

import com.country.dao.AuditDao;
import com.country.model.AuditDTO;
import com.country.model.DAOAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class AuditService {

    @Autowired
    private AuditDao auditDao;

    public List<DAOAudit> auditByUserId(AuditDTO audit) {
        List<DAOAudit> daoAudit = auditDao.findByUserId(audit.getUserId());
        return daoAudit;
    }

    public void saveAudit(AuditDTO auditDTO) {
        Long userId = auditDTO.getUserId();

        if (userId != null) {
            DAOAudit audit = new DAOAudit();
            audit.setUserId(userId);
            audit.setCode(auditDTO.getCode());
            audit.setName(auditDTO.getName());
            audit.setSymbol(auditDTO.getSymbol());
            audit.setExchangeRateIdr(auditDTO.getExchangeRateIdr());
            auditDao.save(audit);
        }
    }
}
