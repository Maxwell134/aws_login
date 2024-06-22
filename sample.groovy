import groovy.json.JsonSlurper

def aws_login(accessKeyId, secretAccessKey, region){

withCredentials([file(credentialslId:'aws-credentials-id', variable: 'AWS_CREDENTIALS_FILE' )]){

// Read and parse the JSON credentials file

def jsonFile = readFile(env.AWS_CREDENTIALS_FILE)
def credentials = new JsonSlurper().parserText(jsonFile)

def accessKeyId = credentials.accessKeyId
def secretAccessKey = credentials.secretAccessKey
def region = credentials.region

return [accessKeyId, secretAccessKey, region]


}


}

