/*
 *
 * ****************************************************************************
 *  * Copyright (C) 2019 Testsigma Technologies Inc.
 *  * All rights reserved.
 *  ****************************************************************************
 *
 */

package com.testsigma.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testsigma.dto.ElementDTO;
import com.testsigma.dto.TestCaseDTO;
import com.testsigma.exception.ResourceNotFoundException;
import com.testsigma.exception.TestsigmaDatabaseException;
import com.testsigma.exception.TestsigmaException;
import com.testsigma.mapper.ElementMapper;
import com.testsigma.model.*;
import com.testsigma.service.*;
import com.testsigma.specification.ElementSpecificationsBuilder;
import com.testsigma.specification.SearchCriteria;
import com.testsigma.specification.SearchOperation;
import com.testsigma.web.request.ElementRequest;
import com.testsigma.web.request.ElementScreenNameRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping(path = "/reports")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReportsController {
    private final ReportsService reportsService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> show(@PathVariable("id") Long id) throws TestsigmaException,Exception {
        JSONArray responseObject = reportsService.getReport(id);
        List<Map<String,Object>> entities = new ArrayList<Map<String,Object>>();
        for (int i=0;i<responseObject.length();i++) {
            Map<String, Object> result = new ObjectMapper().readValue(responseObject.getJSONObject(i).toString(), new TypeReference<Map<String, Object>>(){});
//            JSONObject entity = responseObject.getJSONObject(i);
            entities.add(result);
        }

        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

}