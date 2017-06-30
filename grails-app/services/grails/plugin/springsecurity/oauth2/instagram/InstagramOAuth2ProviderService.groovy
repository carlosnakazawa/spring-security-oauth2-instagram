package grails.plugin.springsecurity.oauth2.instagram

import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuth2AccessToken
import grails.plugin.springsecurity.oauth2.service.OAuth2AbstractProviderService
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken
import grails.transaction.Transactional

@Transactional
class InstagramOAuth2ProviderService extends OAuth2AbstractProviderService {

    @Override
    String getProviderID() {
        return 'instagram'
    }

    @Override
    Class<? extends DefaultApi20> getApiClass() {
        return null
    }

    @Override
    String getProfileScope() {
        return null
    }

    @Override
    String getScopes() {
        return null
    }

    @Override
    String getScopeSeparator() {
        return null
    }

    @Override
    OAuth2SpringToken createSpringAuthToken(OAuth2AccessToken accessToken) {
        return null
    }
}
