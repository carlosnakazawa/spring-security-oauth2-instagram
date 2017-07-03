/**
 * Created by carlo on 03/07/2017.
 */
security {
    oauth2 {
        providers {
            instagram {
                successUri = "/oauth2/instagram/success"
                failureUri = "/oauth2/instagram/failure"
                callback = "/oauth2/instagram/callback"
                api_key = "changeme_apikey"
                api_secret = "changeme_apisecret"
            }
        }
    }
}
