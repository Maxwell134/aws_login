import groovy.json.JsonSlurper

def aws_login(accessKeyId, secretAccessKey, region) {
    withCredentials([file(credentialsId: 'aws-credentials-id', variable: 'AWS_CREDENTIALS_FILE')]) {
        
        // Read and parse the JSON credentials file
        def jsonFile = readFile(env.AWS_CREDENTIALS_FILE)
        def credentials = new JsonSlurper().parseText(jsonFile)

        // Use passed parameters or fallback to JSON credentials if not provided
        accessKeyId =  credentials.accessKeyId
        secretAccessKey =  credentials.secretAccessKey
        region = credentials.region

        return [accessKeyId: accessKeyId , secretAccessKey:secretAccessKey , region: region]
    }
}
return this
