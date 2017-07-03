package grails.plugin.springsecurity.oauth2.instagram

import com.github.scribejava.core.model.OAuth2AccessToken
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken

/**
 * Created by carlo on 03/07/2017.
 */
class InstagramOauth2SpringToken extends OAuth2SpringToken {

    private String email
    private String providerId

    InstagramOauth2SpringToken(OAuth2AccessToken accessToken, String email, String providerId) {
        super(accessToken)
        this.email = email
        this.providerId = providerId
    }

    @Override
    String getProviderName() {
        return providerId
    }

    @Override
    String getSocialId() {
        return email
    }

    @Override
    String getScreenName() {
        return email
    }
}