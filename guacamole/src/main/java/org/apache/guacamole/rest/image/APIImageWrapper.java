/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.guacamole.rest.image;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.GuacamoleUnsupportedException;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.auth.Image;
import org.apache.guacamole.protocol.GuacamoleClientInformation;
import org.apache.guacamole.protocol.GuacamoleConfiguration;


public class APIImageWrapper implements Image {

    private final APIImage apiImage;

    public APIImageWrapper(APIImage apiImage) {
        this.apiImage = apiImage;
    }


    @Override
    public Map<String, String> getAttributes() {
        return apiImage.getAttributes();
    }

    @Override
    public void setAttributes(Map<String, String> attributes) {
        apiImage.setAttributes(attributes);
    }

    @Override
    public Date getLastActive() {
        return null;
    }

    @Override
    public void setIdentifier(String identifier) {
        apiImage.setIdentifier(identifier);
    }
    
    @Override
    public String getIdentifier() {
        return apiImage.getIdentifier();
    }
}
