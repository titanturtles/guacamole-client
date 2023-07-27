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

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;  

import org.apache.guacamole.GuacamoleClientException;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.GuacamoleSecurityException;
import org.apache.guacamole.GuacamoleUnsupportedException;
import org.apache.guacamole.net.auth.ActivityRecordSet;
import org.apache.guacamole.net.auth.Image;
import org.apache.guacamole.net.auth.Directory;
import org.apache.guacamole.net.auth.Permissions;
import org.apache.guacamole.rest.directory.DirectoryView;
import org.apache.guacamole.net.auth.SharingProfile;
import org.apache.guacamole.net.auth.UserContext;
import org.apache.guacamole.net.auth.permission.ObjectPermission;
import org.apache.guacamole.net.auth.permission.ObjectPermissionSet;
import org.apache.guacamole.net.auth.permission.SystemPermission;
import org.apache.guacamole.net.auth.permission.SystemPermissionSet;
import org.apache.guacamole.net.auth.simple.SimpleActivityRecordSet;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.rest.directory.DirectoryObjectResource;
import org.apache.guacamole.rest.directory.DirectoryObjectTranslator;
import org.apache.guacamole.rest.directory.DirectoryResource;
import org.apache.guacamole.rest.directory.DirectoryResourceFactory;
import org.apache.guacamole.rest.history.ConnectionHistoryResource;
import org.apache.guacamole.rest.sharingprofile.APISharingProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A REST resource which abstracts the operations available on an existing
 * Connection.
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageResource extends DirectoryObjectResource<Image, APIImage> {

    /**
     * Logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(ImageResource.class);
    
    /**
     * The UserContext associated with the Directory which contains the
     * Connection exposed by this resource.
     */
    private final UserContext userContext;

    /**
     * The Connection object represented by this ConnectionResource.
     */
    private final Image image;



    @AssistedInject
    public ImageResource(@Assisted UserContext userContext,
            @Assisted Directory<Image> directory,
            @Assisted Image image,
            DirectoryObjectTranslator<Image, APIImage> translator) {
        super(userContext, directory, image, translator);
        this.userContext = userContext;
        this.image = image;
    }

    @POST
    @Path("/imageuploads")
    @Consumes(MediaType.MULTIPART_FORM_DATA)  
    public Response imageUpload(
            @FormDataParam("file") InputStream uploadedInputStream,  
            @FormDataParam("file") FormDataContentDisposition fileDetail) {  

            String fileLocation = "e://" + fileDetail.getFileName();  
                    //saving file  
            try {  
                FileOutputStream out = new FileOutputStream(new File(fileLocation));  
                int read = 0;  
                byte[] bytes = new byte[1024];  
                out = new FileOutputStream(new File(fileLocation));  
                while ((read = uploadedInputStream.read(bytes)) != -1) {  
                    out.write(bytes, 0, read);  
                }  
                out.flush();  
                out.close();  
            } catch (IOException e) {e.printStackTrace();}  
            String output = "File successfully uploaded to : " + fileLocation;  
            return Response.status(200).entity(output).build();  

    }



}
