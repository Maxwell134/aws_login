pipeline {
    agent any

    stages {
        // stage('Setup AWS Login') {
        //     steps {
        //         script {
        //             // Install AWS CLI if not already installed
        //             sh """
        //             if ! command -v aws &> /dev/null
        //             then
        //                 echo "AWS CLI not found. Installing..."
        //                 curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
        //                 unzip awscliv2.zip
        //                 sudo ./aws/install
        //             fi
        //             """

                    // Load the Groovy script
                    def awsUtils = load 'sample.groovy'

                    // Call the aws_login function with or without parameters
                    // If parameters are not provided, pass `null` to use JSON file values
                    def providedAccessKeyId = null // Or provide a value: "your-access-key-id"
                    def providedSecretAccessKey = null // Or provide a value: "your-secret-access-key"
                    def providedRegion = null // Or provide a value: "your-aws-region"
                    def credentials = awsUtils.aws_login(providedAccessKeyId, providedSecretAccessKey, providedRegion)

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
