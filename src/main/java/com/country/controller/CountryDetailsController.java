package com.country.controller;

import com.country.connector.CountryConnector;
import com.country.model.AuditDTO;
import com.country.service.AuditService;
import com.country.utils.CountryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CountryDetailsController {

	@Autowired
	private AuditService auditService;

	@RequestMapping({ "/country/{name}" }) /*input name of country*/
	public ResponseEntity<?> firstPage(@PathVariable String name) {
		Map countryDetails = CountryConnector.getCountryDetails(name);

		AuditDTO audit = CountryUtils.captureAuditOnly(countryDetails);

		auditService.saveAudit(audit);

		String jsonData = CountryUtils.getJsonData(countryDetails);

		return ResponseEntity.ok(jsonData);
	}

	@RequestMapping(value = "/audit", method = RequestMethod.GET)
	public ResponseEntity<?> audit(@RequestBody AuditDTO audit) {
		return ResponseEntity.ok(auditService.auditByUserId(audit));
	}
}