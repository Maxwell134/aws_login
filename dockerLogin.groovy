import groovy.json.JsonSlurper

def dockerLogin(credentials) {
    def jsonSlurper = new JsonSlurper()
    def dockerCredentials = jsonSlurper.parseText(credentials)

    def username = dockerCredentials.username
    def password = dockerCredentials.password
    
    sh """
        echo $password | docker login  -u $username --password-stdin
    """
}

return this
