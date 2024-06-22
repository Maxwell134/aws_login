pipeline {
    agent any

    stages {
        stage('Install AWS CLI') {
            steps {
                script {
                    // Download AWS CLI v2 ZIP archive
                    sh 'curl -o awscliv2.zip https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip'

                    // Unzip AWS CLI v2 archive
                    sh 'unzip -o awscliv2.zip'

                    // Install AWS CLI v2
                    sh './aws/install'

                    // Verify AWS CLI installation
                    sh 'aws --version'
                }
            }
        }

        stage('Configure AWS CLI') {
            steps {
                script {
                    // Load the Groovy script
                    def awsUtils = load 'sample.groovy'

                    // Call the aws_login function with parameters
                    def credentials = awsUtils.aws_login('your-access-key-id', 'your-secret-access-key', 'your-aws-region')
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
