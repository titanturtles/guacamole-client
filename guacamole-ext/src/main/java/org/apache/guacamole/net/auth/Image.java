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

package org.apache.guacamole.net.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.GuacamoleUnsupportedException;

/**
 * An image in the images pool of the Guacamole web application.
 */
public interface Image extends Identifiable, Attributes {
    /**
     * All standard attribute names with semantics defined by the Guacamole web
     * application. Extensions may additionally define their own attributes
     * with completely arbitrary names and semantics, so long as those names do
     * not conflict with the names listed here. All standard attribute names
     * have a "guac-" prefix to avoid such conflicts.
     */
    public static class Attribute {

        public static String UPLOADED_FILENAME = "uploaded-filename";

        public static String FINAL_FILENAME = "final-filename";

        public static String README = "readme";

        public static String DOWNLOADS = "downlaods";

        public static String SCOREBOARD = "scoreboard";

        public static String WRITEUPS = "write-ups";

    }

    Date getLastActive();
    
}


