package grails.plugin.springsecurity.oauth2.instagram

import com.github.scribejava.core.builder.api.DefaultApi20

class Instagram2Api extends DefaultApi20 {

    private static final String AUTHORIZATION_URL = "https://api.instagram.com/oauth/authorize/?client_id=%s&redirect_uri=%s&state=%s&grant_type=authorization_code&response_type=code";
    private static final String AUTHORIZATION_BASE_URL = "https://api.instagram.com/oauth/authorize/"
    private static final String SCOPED_AUTHORIZATION_URL = AUTHORIZATION_URL + "&scope=%s";

    protected Instagram2Api() {
    }

    private static class InstanceHolder {

        private static final Instagram2Api INSTANCE = new Instagram2Api();
    }

    public static Instagram2Api instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    String getAccessTokenEndpoint() {
        return "https://api.instagram.com/oauth/access_token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZATION_BASE_URL
    }

}
