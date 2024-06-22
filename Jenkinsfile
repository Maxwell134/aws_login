pipeline {
    agent any

    stages {
        stage('Install AWS CLI') {
            steps {
                script {
                    // Check if pip is installed
                    if (!sh(script: 'command -v pip', returnStatus: true)) {
                        echo "pip not found. Installing pip..."
                        sh 'curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py'
                        sh 'python get-pip.py --user'
                    }

                    // Ensure ~/.local/bin is in the PATH
                    env.PATH = "${env.HOME}/.local/bin:${env.PATH}"

                    // Install AWS CLI using pip
                    sh 'pip install --user awscli'

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
