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
                    def accessKeyId = credentials[0]
                    def secretAccessKey = credentials[1]
                    def region = credentials[2]

                    // Configure AWS CLI with the fetched credentials and region
                    
                    echo "aws_access_key_id: ${accessKeyId}"
                    echo "aws_secret_access_key: ${secretAccessKey}"
                    echo "region: ${region}"
                    
                }
            }
        }
    }
}
