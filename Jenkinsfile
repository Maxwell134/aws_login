pipeline {
    agent {
        docker {
            image 'amazonlinux:latest' // Replace with appropriate Docker image
            reuseNode true  // Reuse containers for subsequent steps
        }
    }

    stages {
        stage('Install and Configure AWS CLI') {
            steps {
                script {
                    // Install necessary dependencies if not already installed
                    sh 'yum install -y python3-pip'

                    // Install AWS CLI using pip3
                    sh 'pip3 install awscli --upgrade --user'

                    // Verify AWS CLI installation
                    sh 'aws --version'

                    // Call the aws_login function from sample.groovy if needed
                    def awsUtils = load 'sample.groovy'
                    def credentials = awsUtils.aws_login('your-access-key-id', 'your-secret-access-key', 'your-aws-region')
                    def accessKeyId = credentials[0]
                    def secretAccessKey = credentials[1]
                    def region = credentials[2]

                    // Configure AWS CLI with the fetched credentials and region
                    sh """
                    aws configure set aws_access_key_id ${accessKeyId}
                    aws configure set aws_secret_access_key ${secretAccessKey}
                    aws configure set region ${region}
                    """

                    // Verify AWS configuration
                    sh 'aws sts get-caller-identity'
                }
            }
        }
    }
}
