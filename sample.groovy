import groovy.json.JsonSlurper

def aws_login(accessKeyId, secretAccessKey, region) {
    withCredentials([file(credentialsId: 'aws-credentials-id', variable: 'AWS_CREDENTIALS_FILE')]) {
        
        // Read and parse the JSON credentials file
        def jsonFile = readFile(env.AWS_CREDENTIALS_FILE)
        def credentials = new JsonSlurper().parseText(jsonFile)

        // Use passed parameters or fallback to JSON credentials if not provided
        accessKeyId = accessKeyId ?: credentials.accessKeyId
        secretAccessKey = secretAccessKey ?: credentials.secretAccessKey
        region = region ?: credentials.region

        return [accessKeyId, secretAccessKey, region]
    }
}
return this
