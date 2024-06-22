pipeline {
    agent any

    stages {
        stage('Install and Configure AWS CLI') {
            steps {
                script {
                    // Load the aws_login function from sample.groovy
                    def awsUtils = load 'sample.groovy'

                    // Call the aws_login function and retrieve credentials
                    def credentials = awsUtils.aws_login(accessKeyId, secretAccessKey, region)

                    // Extract individual credentials from the returned map
                    def accessKeyId = credentials.accessKeyId
                    def secretAccessKey = credentials.secretAccessKey
                    def region = credentials.region

                    // Print out the retrieved credentials for verification
                    echo "aws_access_key_id: ${accessKeyId}"
                    echo "aws_secret_access_key: ${secretAccessKey}"
                    echo "region: ${region}"

                    // // Configure AWS CLI with retrieved credentials
                    // sh """
                    // aws configure set aws_access_key_id ${accessKeyId}
                    // aws configure set aws_secret_access_key ${secretAccessKey}
                    // aws configure set region ${region}
                    // """

                    // // Verify AWS CLI configuration
                    // sh "aws sts get-caller-identity"
                }
            }
        }
    }
}
