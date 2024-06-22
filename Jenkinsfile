pipeline {
    agent any

    stages {
        stage('Install AWS CLI') {
            steps {
                script {
                    // Check if pip3 is installed
                   sh """
                    curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
                        unzip awscliv2.zip
                         ./aws/install
                    """
                     // Verify the AWS CLI installation
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
                    def credentials = awsUtils.aws_login(accessKeyId, secretAccessKey, region)
                    def accessKeyId = credentials[0]
                    def secretAccessKey = credentials[1]
                    def region = credentials[2]

                    // Ensure ~/.local/bin is in the PATH
                    env.PATH = "${env.HOME}/.local/bin:${env.PATH}"

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
