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

/**
 * Service for operating on users via the REST API.
 */
angular.module('rest').factory('imagesPoolService', ['$injector',
        function imagesPoolService($injector) {

    // Required services
    var requestService        = $injector.get('requestService');
    var authenticationService = $injector.get('authenticationService');
    var cacheService          = $injector.get('cacheService');

    // Get required types
    var UserPasswordUpdate = $injector.get("UserPasswordUpdate");
            
    var service = {};
    
    // /**
    //  * Makes a request to the REST API to get the list of users,
    //  * returning a promise that provides an array of @link{User} objects if
    //  * successful.
    //  * 
    //  * @param {String} dataSource
    //  *     The unique identifier of the data source containing the users to be
    //  *     retrieved. This identifier corresponds to an AuthenticationProvider
    //  *     within the Guacamole web application.
    //  *
    //  * @param {String[]} [permissionTypes]
    //  *     The set of permissions to filter with. A user must have one or more
    //  *     of these permissions for a user to appear in the result. 
    //  *     If null, no filtering will be performed. Valid values are listed
    //  *     within PermissionSet.ObjectType.
    //  *                          
    //  * @returns {Promise.<Object.<String, User>>}
    //  *     A promise which will resolve with a map of @link{User} objects
    //  *     where each key is the identifier (username) of the corresponding
    //  *     user.
    //  */
    // service.getUsers = function getUsers(dataSource, permissionTypes) {

    //     // Add permission filter if specified
    //     var httpParameters = {};
    //     if (permissionTypes)
    //         httpParameters.permission = permissionTypes;

    //     // Retrieve users
    //     return authenticationService.request({
    //         cache   : cacheService.users,
    //         method  : 'GET',
    //         url     : 'api/session/data/' + encodeURIComponent(dataSource) + '/users',
    //         params  : httpParameters
    //     });

    // };

    /**
     * Makes a request to the REST API to get the user having the given
     * username, returning a promise that provides the corresponding
     * @link{User} if successful.
     *
     * @param {String} dataSource
     *     The unique identifier of the data source containing the user to be
     *     retrieved. This identifier corresponds to an AuthenticationProvider
     *     within the Guacamole web application.
     *
     * @param {String} username
     *     The username of the user to retrieve.
     * 
     * @returns {Promise.<User>}
     *     A promise which will resolve with a @link{User} upon success.
     */
    service.getPoolImage = function getPoolImage(dataSource, username) {

        // Retrieve user
        return authenticationService.request({
            cache   : cacheService.users,
            method  : 'GET',
            url     : 'api/session/data/poolimage'
        });

    };
    
    service.uploadPoolImage = function uploadpoolimage(file, user) {

        // Create user
        return authenticationService.request({
            method  : 'POST',
            url     : 'api/session/data/uploadpoolimage',
            data    : file
        })

        // Clear the cache
        .then(function uploaded(){
            // cacheService.users.removeAll();
        });

    };
    
    return service;

}]);
