package com.country.controller;

import com.country.model.AuditDTO;
import com.country.service.AuditService;
import com.country.utils.CountryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class CountryDetailsController {

	@Autowired
	private AuditService auditService;

	@RequestMapping({ "/country/{name}" }) /*input name of country*/
	public ResponseEntity<?> firstPage(@PathVariable String name) {
//		Map countryDetails = CountryConnector.getCountryDetails(name);
		Map containerMap = new HashMap();
		containerMap.put("full_name", "BD");
		containerMap.put("population", "16 crore");
		containerMap.put("currencies", new HashMap());

		AuditDTO audit = CountryUtils.captureAuditOnly(containerMap);

		auditService.saveAudit(audit);

		String jsonData = CountryUtils.getJsonData(containerMap);

		return ResponseEntity.ok(jsonData);
	}

	@RequestMapping(value = "/audit", method = RequestMethod.GET)
	public ResponseEntity<?> audit(@RequestBody AuditDTO audit) {
		return ResponseEntity.ok(auditService.auditByUsername(audit));
	}
}