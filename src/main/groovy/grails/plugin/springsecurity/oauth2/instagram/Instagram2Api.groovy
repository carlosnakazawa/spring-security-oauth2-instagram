package grails.plugin.springsecurity.oauth2.instagram

import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor
import com.github.scribejava.core.model.Verb

class Instagram2Api extends DefaultApi20 {

    private static final String AUTHORIZATION_URL = "https://api.instagram.com/oauth/authorize/?client_id=%s&redirect_uri=%s&state=%s&grant_type=authorization_code&response_type=code";
    private static final String AUTHORIZATION_BASE_URL = "https://api.instagram.com/oauth/authorize/"
    private static final String SCOPED_AUTHORIZATION_URL = AUTHORIZATION_URL + "&scope=%s";

    @Override
    String getAccessTokenEndpoint() {
        return "https://api.instagram.com/oauth/access_token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZATION_BASE_URL
    }

}
