pipeline {
    agent any 

    stages {
        stage('Install and Configure AWS CLI') {
            steps {
                script {
                                    
                    // Verify AWS CLI installation
                    
                    // Call the aws_login function from sample.groovy if needed
                    def awsUtils = load 'sample.groovy'
                    def credentials = awsUtils.aws_login(accessKeyId, secretAccessKey, region)
                    def accessKeyId = credentials.accessKeyId
                    def secretAccessKey = credentials.secretAccessKey
                    def region = credentials.region

                    // Configure AWS CLI with the fetched credentials and region
                    
                    echo "aws_access_key_id: ${accessKeyId}"
                    echo "aws_secret_access_key: ${secretAccessKey}"
                    echo "region: ${region}"
                    
                }
            }
        }
    }
}
