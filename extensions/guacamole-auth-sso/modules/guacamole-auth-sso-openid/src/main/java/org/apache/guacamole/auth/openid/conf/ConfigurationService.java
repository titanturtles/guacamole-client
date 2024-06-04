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

package org.apache.guacamole.auth.openid.conf;

import com.google.inject.Inject;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.environment.Environment;
import org.apache.guacamole.properties.IntegerGuacamoleProperty;
import org.apache.guacamole.properties.StringGuacamoleProperty;
import org.apache.guacamole.properties.StringListProperty;
import org.apache.guacamole.properties.URIGuacamoleProperty;

/**
 * Service for retrieving configuration information regarding the OpenID
 * service.
 */
public class ConfigurationService {

    /**
     * The default claim type to use to retrieve an authenticated user's
     * username.
     */
    private static final String DEFAULT_USERNAME_CLAIM_TYPE = "email";

    /**
     * The default claim type to use to retrieve an authenticated user's
     * groups.
     */
    private static final String DEFAULT_GROUPS_CLAIM_TYPE = "groups";

    /**
     * The default JWT claims list to map to tokens.
     */
    private static final List<String> DEFAULT_ATTRIBUTES_CLAIM_TYPE = Collections.emptyList();

    /**
     * The default space-separated list of OpenID scopes to request.
     */
    private static final String DEFAULT_SCOPE = "openid email profile";

    /**
     * The default amount of clock skew tolerated for timestamp comparisons
     * between the Guacamole server and OpenID service clocks, in seconds.
     */
    private static final int DEFAULT_ALLOWED_CLOCK_SKEW = 30;

    /**
     * The default maximum amount of time that an OpenID token should remain
     * valid, in minutes.
     */
    private static final int DEFAULT_MAX_TOKEN_VALIDITY = 300;

    /**
     * The default maximum amount of time that a nonce generated by the
     * Guacamole server should remain valid, in minutes.
     */
    private static final int DEFAULT_MAX_NONCE_VALIDITY = 10;

    /**
     * The authorization endpoint (URI) of the OpenID service.
     */
    private static final URIGuacamoleProperty OPENID_AUTHORIZATION_ENDPOINT =
            new URIGuacamoleProperty() {

        @Override
        public String getName() { return "openid-authorization-endpoint"; }

    };

    /**
     * The endpoint (URI) of the JWKS service which defines how received ID
     * tokens (JWTs) shall be validated.
     */
    private static final URIGuacamoleProperty OPENID_JWKS_ENDPOINT =
            new URIGuacamoleProperty() {

        @Override
        public String getName() { return "openid-jwks-endpoint"; }

    };

    /**
     * The issuer to expect for all received ID tokens.
     */
    private static final StringGuacamoleProperty OPENID_ISSUER =
            new StringGuacamoleProperty() {

        @Override
        public String getName() { return "openid-issuer"; }

    };

    /**
     * The claim type which contains the authenticated user's username within
     * any valid JWT.
     */
    private static final StringGuacamoleProperty OPENID_USERNAME_CLAIM_TYPE =
            new StringGuacamoleProperty() {

        @Override
        public String getName() { return "openid-username-claim-type"; }

    };

    /**
     * The claim type which contains the authenticated user's groups within
     * any valid JWT.
     */
    private static final StringGuacamoleProperty OPENID_GROUPS_CLAIM_TYPE =
            new StringGuacamoleProperty() {

        @Override
        public String getName() { return "openid-groups-claim-type"; }

    };

    /**
     * The claims within any valid JWT that should be mapped to
     * the authenticated user's tokens, as configured with guacamole.properties.
     */
    private static final StringListProperty OPENID_ATTRIBUTES_CLAIM_TYPE =
            new StringListProperty() {
                @Override
                public String getName() { return "openid-attributes-claim-type"; }
            };

    /**
     * The space-separated list of OpenID scopes to request.
     */
    private static final StringGuacamoleProperty OPENID_SCOPE =
            new StringGuacamoleProperty() {

        @Override
        public String getName() { return "openid-scope"; }

    };

    /**
     * The amount of clock skew tolerated for timestamp comparisons between the
     * Guacamole server and OpenID service clocks, in seconds.
     */
    private static final IntegerGuacamoleProperty OPENID_ALLOWED_CLOCK_SKEW =
            new IntegerGuacamoleProperty() {

        @Override
        public String getName() { return "openid-allowed-clock-skew"; }

    };

    /**
     * The maximum amount of time that an OpenID token should remain valid, in
     * minutes.
     */
    private static final IntegerGuacamoleProperty OPENID_MAX_TOKEN_VALIDITY =
            new IntegerGuacamoleProperty() {

        @Override
        public String getName() { return "openid-max-token-validity"; }

    };

    /**
     * The maximum amount of time that a nonce generated by the Guacamole server
     * should remain valid, in minutes. As each OpenID request has a unique
     * nonce value, this imposes an upper limit on the amount of time any
     * particular OpenID request can result in successful authentication within
     * Guacamole.
     */
    private static final IntegerGuacamoleProperty OPENID_MAX_NONCE_VALIDITY =
            new IntegerGuacamoleProperty() {

        @Override
        public String getName() { return "openid-max-nonce-validity"; }

    };

    /**
     * OpenID client ID which should be submitted to the OpenID service when
     * necessary. This value is typically provided by the OpenID service when
     * OpenID credentials are generated for your application.
     */
    private static final StringGuacamoleProperty OPENID_CLIENT_ID =
            new StringGuacamoleProperty() {

        @Override
        public String getName() { return "openid-client-id"; }

    };

    /**
     * The URI that the OpenID service should redirect to after the
     * authentication process is complete. This must be the full URL that a
     * user would enter into their browser to access Guacamole.
     */
    private static final URIGuacamoleProperty OPENID_REDIRECT_URI =
            new URIGuacamoleProperty() {

        @Override
        public String getName() { return "openid-redirect-uri"; }

    };

    /**
     * The Guacamole server environment.
     */
    @Inject
    private Environment environment;

    /**
     * Returns the authorization endpoint (URI) of the OpenID service as
     * configured with guacamole.properties.
     *
     * @return
     *     The authorization endpoint of the OpenID service, as configured with
     *     guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the authorization
     *     endpoint property is missing.
     */
    public URI getAuthorizationEndpoint() throws GuacamoleException {
        return environment.getRequiredProperty(OPENID_AUTHORIZATION_ENDPOINT);
    }

    /**
     * Returns the OpenID client ID which should be submitted to the OpenID
     * service when necessary, as configured with guacamole.properties. This
     * value is typically provided by the OpenID service when OpenID credentials
     * are generated for your application.
     *
     * @return
     *     The client ID to use when communicating with the OpenID service,
     *     as configured with guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the client ID
     *     property is missing.
     */
    public String getClientID() throws GuacamoleException {
        return environment.getRequiredProperty(OPENID_CLIENT_ID);
    }

    /**
     * Returns the URI that the OpenID service should redirect to after
     * the authentication process is complete, as configured with
     * guacamole.properties. This must be the full URL that a user would enter
     * into their browser to access Guacamole.
     *
     * @return
     *     The client secret to use when communicating with the OpenID service,
     *     as configured with guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the redirect URI
     *     property is missing.
     */
    public URI getRedirectURI() throws GuacamoleException {
        return environment.getRequiredProperty(OPENID_REDIRECT_URI);
    }

    /**
     * Returns the issuer to expect for all received ID tokens, as configured
     * with guacamole.properties.
     *
     * @return
     *     The issuer to expect for all received ID tokens, as configured with
     *     guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the issuer property
     *     is missing.
     */
    public String getIssuer() throws GuacamoleException {
        return environment.getRequiredProperty(OPENID_ISSUER);
    }

    /**
     * Returns the endpoint (URI) of the JWKS service which defines how
     * received ID tokens (JWTs) shall be validated, as configured with
     * guacamole.properties.
     *
     * @return
     *     The endpoint (URI) of the JWKS service which defines how received ID
     *     tokens (JWTs) shall be validated, as configured with
     *     guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the JWKS endpoint
     *     property is missing.
     */
    public URI getJWKSEndpoint() throws GuacamoleException {
        return environment.getRequiredProperty(OPENID_JWKS_ENDPOINT);
    }

    /**
     * Returns the claim type which contains the authenticated user's username
     * within any valid JWT, as configured with guacamole.properties. By
     * default, this will be "email".
     *
     * @return
     *     The claim type which contains the authenticated user's username
     *     within any valid JWT, as configured with guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed.
     */
    public String getUsernameClaimType() throws GuacamoleException {
        return environment.getProperty(OPENID_USERNAME_CLAIM_TYPE, DEFAULT_USERNAME_CLAIM_TYPE);
    }

    /**
     * Returns the claim type which contains the authenticated user's groups
     * within any valid JWT, as configured with guacamole.properties. By
     * default, this will be "groups".
     *
     * @return
     *     The claim type which contains the authenticated user's groups
     *     within any valid JWT, as configured with guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed.
     */
    public String getGroupsClaimType() throws GuacamoleException {
        return environment.getProperty(OPENID_GROUPS_CLAIM_TYPE, DEFAULT_GROUPS_CLAIM_TYPE);
    }

    /**
     * Returns the claims list within any valid JWT that should be mapped to
     * the authenticated user's tokens, as configured with guacamole.properties.
     * Empty by default.
     *
     * @return
     *     The claims list within any valid JWT that should be mapped to
     *     the authenticated user's tokens, as configured with guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed.
     */
    public List<String> getAttributesClaimType() throws GuacamoleException {
        return environment.getProperty(OPENID_ATTRIBUTES_CLAIM_TYPE, DEFAULT_ATTRIBUTES_CLAIM_TYPE);
    }

    /**
     * Returns the space-separated list of OpenID scopes to request. By default,
     * this will be "openid email profile". The OpenID scopes determine the
     * information returned within the OpenID token, and thus affect what
     * values can be used as an authenticated user's username.
     *
     * @return
     *     The space-separated list of OpenID scopes to request when identifying
     *     a user.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed.
     */
    public String getScope() throws GuacamoleException {
        return environment.getProperty(OPENID_SCOPE, DEFAULT_SCOPE);
    }

    /**
     * Returns the amount of clock skew tolerated for timestamp comparisons
     * between the Guacamole server and OpenID service clocks, in seconds. Too
     * much clock skew will affect token expiration calculations, possibly
     * allowing old tokens to be used. By default, this will be 30.
     *
     * @return
     *     The amount of clock skew tolerated for timestamp comparisons, in
     *     seconds.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed.
     */
    public int getAllowedClockSkew() throws GuacamoleException {
        return environment.getProperty(OPENID_ALLOWED_CLOCK_SKEW, DEFAULT_ALLOWED_CLOCK_SKEW);
    }

    /**
     * Returns the maximum amount of time that an OpenID token should remain
     * valid, in minutes. A token received from an OpenID service which is
     * older than this amount of time will be rejected, even if it is otherwise
     * valid. By default, this will be 300 (5 hours).
     *
     * @return
     *     The maximum amount of time that an OpenID token should remain valid,
     *     in minutes.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed.
     */
    public int getMaxTokenValidity() throws GuacamoleException {
        return environment.getProperty(OPENID_MAX_TOKEN_VALIDITY, DEFAULT_MAX_TOKEN_VALIDITY);
    }

    /**
     * Returns the maximum amount of time that a nonce generated by the
     * Guacamole server should remain valid, in minutes. As each OpenID request
     * has a unique nonce value, this imposes an upper limit on the amount of
     * time any particular OpenID request can result in successful
     * authentication within Guacamole. By default, this will be 10.
     *
     * @return
     *     The maximum amount of time that a nonce generated by the Guacamole
     *     server should remain valid, in minutes.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed.
     */
    public int getMaxNonceValidity() throws GuacamoleException {
        return environment.getProperty(OPENID_MAX_NONCE_VALIDITY, DEFAULT_MAX_NONCE_VALIDITY);
    }

}
