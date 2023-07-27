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

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.auth.Image;
import org.apache.guacamole.net.auth.UserContext;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.rest.directory.DirectoryObjectTranslator;

/**
 * Translator which converts between Connection objects and APIConnection
 * objects.
 */
public class ImageObjectTranslator
        extends DirectoryObjectTranslator<Image, APIImage> {

    @Override
    public APIImage toExternalObject(Image object)
            throws GuacamoleException {
        return new APIImage(object);
    }

    @Override
    public Image toInternalObject(APIImage object) {
        return new APIImageWrapper(object);
    }

    @Override
    public void applyExternalChanges(Image existingObject,
            APIImage object) {

        existingObject.setAttributes(object.getAttributes());

    }

    @Override
    public void filterExternalObject(UserContext userContext,
            APIImage object) throws GuacamoleException {

        // Filter object attributes by defined schema
        object.setAttributes(filterAttributes(
                userContext.getConnectionAttributes(), object.getAttributes()));

    }

}
