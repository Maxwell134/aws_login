pipeline {
    agent any

    stages {
        stage('Setup AWS Login') {
            steps {
                script {
                    // Load the Groovy script
                    def awsUtils = load 'sample.groovy'

                    // Call the aws_login function
                    def credentials = awsUtils.aws_login(accessKeyId, secretAccessKey, region)
                    def accessKeyId = credentials[0]
                    def secretAccessKey = credentials[1]
                    def region = credentials[2]

                    // Configure AWS CLI with the credentials and region
                    sh """
                    aws configure set aws_access_key_id ${accessKeyId}
                    aws configure set aws_secret_access_key ${secretAccessKey}
                    aws configure set region ${region}
                    """

                    // Verify the AWS configuration
                    sh 'aws sts get-caller-identity'
                }
            }
        }
    }
}
