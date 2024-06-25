// pipeline {
//     agent any

//     environment {
//         AWS_ACCESS_KEY_ID = ''  // Set your AWS access key ID
//         AWS_SECRET_ACCESS_KEY = ''  // Set your AWS secret access key
//         AWS_REGION = ''  // Set your AWS region
//     }

//     stages {
//         stage('Install and Configure AWS CLI') {
//             steps {
//                 script {
//                     // Load the aws_login function from sample.groovy
//                     def awsUtils = load 'sample.groovy'

//                     // Call the aws_login function and retrieve credentials
//                     def credentials = awsUtils.aws_login(env.AWS_ACCESS_KEY_ID, env.AWS_SECRET_ACCESS_KEY, env.AWS_REGION)

//                     // Extract individual credentials from the returned map
//                     def accessKeyId = credentials.accessKeyId
//                     def secretAccessKey = credentials.secretAccessKey
//                     def region = credentials.region

//                     // Print out the retrieved credentials for verification
//                     echo "aws_access_key_id: ${accessKeyId}"
//                     echo "aws_secret_access_key: ${secretAccessKey}"
//                     echo "region: ${region}"

//                     // Configure AWS CLI with retrieved credentials
//                     // Uncomment and adjust as needed
//                     // sh """
//                     // aws configure set aws_access_key_id ${accessKeyId}
//                     // aws configure set aws_secret_access_key ${secretAccessKey}
//                     // aws configure set region ${region}
//                     // """

//                     // // Verify AWS CLI configuration
//                     // sh "aws sts get-caller-identity"
//                 }
//             }
//         }

//         stage('Check Workspace') {
//             steps {
//                 script {
//                     // List files in workspace for verification
//                     sh 'ls -lrtha $WORKSPACE'
//                 }
//             }
//         }
//     }

//     post {
//         always {
//             // Clean up workspace at the end of the pipeline
//             cleanWs()
//         }
//     }
// }

 pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'docker.io'
    }

    stages {
        stage('Docker Login') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'Azure_cli', variable: 'DOCKER_CREDENTIALS')]) {
                        def dockerCredentials = readJSON text: DOCKER_CREDENTIALS
                        def username = dockerCredentials.username
                        def password = dockerCredentials.password

                        echo "Using username: ****"
                         def cmd = """
                          #!/bin/bash
                          az login -u ${username} -p ${password} --allow-no-subscriptions
                          """
                         sh cmd
                        // withEnv(["DOCKER_USERNAME=${username}", "DOCKER_PASSWORD=${password}"]) {
                        //     sh """
                        //         echo "\${DOCKER_PASSWORD}" | az login -u "\${DOCKER_USERNAME}" -p ${password} --allow-no-subscriptions
                                
                        //     """
                        // }
                    }
                }
            }
        }
        stage('Docker Images') {
            steps {
                script {
                   sh 'az account show'
                }
            }
        }
    }
}
