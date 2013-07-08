/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.muzimaregistration.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.muzimaregistration.api.RegistrationDataService;
import org.openmrs.module.muzimaregistration.api.model.RegistrationData;
import org.openmrs.module.muzimaregistration.web.utils.WebConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: Write brief description about the class here.
 */
@Controller
@RequestMapping(value = "module/muzimaregistration/registrations.json")
public class RegistrationsController {

        @RequestMapping(method = RequestMethod.GET)
        @ResponseBody
        public Map<String, Object> getNotificationsFor(final @RequestParam(value = "pageNumber") Integer pageNumber,
                                                       final @RequestParam(value = "pageSize") Integer pageSize) {
            Map<String, Object> response = new HashMap<String, Object>();
            RegistrationDataService service = Context.getService(RegistrationDataService.class);

            Integer pages = service.countRegistrationData();

            List<Object> objects = new ArrayList<Object>();
            for (RegistrationData notificationData : service.getRegistrationData(pageNumber, pageSize)) {
                objects.add(WebConverter.convertRegistrationData(notificationData));
            }
            response.put("pages", pages);
            response.put("objects", objects);
            return response;
        }
}
