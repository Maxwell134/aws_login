pipeline {
    agent any

    environment {
        AWS_ACCESS_KEY_ID = ''
        AWS_SECRET_ACCESS_KEY = ''
        AWS_REGION = ''
    }

    stages {
        stage('Install and Configure AWS CLI') {
            steps {
                script {
                    // Load the aws_login function from sample.groovy
                    def awsUtils = load 'sample.groovy'

                    // Call the aws_login function and retrieve credentials
                    def credentials = awsUtils.aws_login(env.AWS_ACCESS_KEY_ID, env.AWS_SECRET_ACCESS_KEY, env.AWS_REGION)

                    // Extract individual credentials from the returned map
                    def accessKeyId = credentials.accessKeyId
                    def secretAccessKey = credentials.secretAccessKey
                    def region = credentials.region

                    // Print out the retrieved credentials for verification
                    echo "aws_access_key_id: ${accessKeyId}"
                    echo "aws_secret_access_key: ${secretAccessKey}"
                    echo "region: ${region}"

                    // Configure AWS CLI with retrieved credentials
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
        stage('check workspace') {
            steps {
                script {
                    sh 'ls -lrtha $WORKSPACE'
                    
                }    
            }
        }
        
        post {
           always {
            // Clean up workspace at the end of the pipeline
            sh 'rm -rf $WORKSPACE'
        }
    }
}
}

}
