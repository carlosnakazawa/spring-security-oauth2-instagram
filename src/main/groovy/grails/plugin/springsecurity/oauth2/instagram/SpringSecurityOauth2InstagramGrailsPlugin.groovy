package grails.plugin.springsecurity.oauth2.instagram

import grails.plugin.springsecurity.ReflectionUtils
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.oauth2.SpringSecurityOauth2BaseService
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugins.*
import org.slf4j.LoggerFactory

class SpringSecurityOauth2InstagramGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.2.6 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Spring Security Oauth2 Instagram" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/spring-security-oauth2-instagram"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]
    def log

    Closure doWithSpring() { {->
        ReflectionUtils.application = grailsApplication
        if (grailsApplication.warDeployed) {
            SpringSecurityUtils.resetSecurityConfig()
        }
        SpringSecurityUtils.application = grailsApplication

        // Check if there is an SpringSecurity configuration
        def coreConf = SpringSecurityUtils.securityConfig
        boolean printStatusMessages = (coreConf.printStatusMessages instanceof Boolean) ? coreConf.printStatusMessages : true
        if (!coreConf || !coreConf.active) {
            if (printStatusMessages) {
                println("ERROR: There is no SpringSecurity configuration or SpringSecurity is disabled")
                println("ERROR: Stopping configuration of SpringSecurity Oauth2")
            }
            return
        }

        if (!hasProperty('log')) {
            log = LoggerFactory.getLogger(SpringSecurityOauth2InstagramGrailsPlugin)
        }

        if (printStatusMessages) {
            println("Configuring Spring Security OAuth2 instagram plugin...")
        }
        SpringSecurityUtils.loadSecondaryConfig('DefaultOAuth2InstagramConfig')
        if (printStatusMessages) {
            println("... finished configuring Spring Security Instagram\n")
        }
        }
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    @Override
    void doWithApplicationContext() {
        log.trace("doWithApplicationContext")
        def SpringSecurityOauth2BaseService oAuth2BaseService = grailsApplication.mainContext.getBean('springSecurityOauth2BaseService') as SpringSecurityOauth2BaseService
        def InstagramOAuth2ProviderService instagramOAuth2Service = grailsApplication.mainContext.getBean('instagramOAuth2ProviderService') as InstagramOAuth2ProviderService
        try {
            oAuth2BaseService.registerProvider(instagramOAuth2Service)
        } catch (OAuth2Exception exception) {
            log.error("There was an oAuth2Exception", exception)
            log.error("OAuth2 Google not loaded")
        }
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
