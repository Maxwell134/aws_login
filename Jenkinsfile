pipeline {
    agent any

    stages {
        stage('Install AWS CLI') {
            steps {
                sh '''
                # Check if pip is installed
                if ! command -v pip &> /dev/null
                then
                    echo "pip not found. Installing pip..."
                    curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py
                    python get-pip.py --user
                fi
                
                # Ensure ~/.local/bin is in the PATH
                export PATH=$HOME/.local/bin:$PATH

                # Install AWS CLI using pip
                pip install --user awscli

                # Verify the AWS CLI installation
                aws --version
                '''
            }
        }

        stage('Configure AWS CLI') {
            steps {
                def awsUtils = load 'sample.groovy'
                    // Call the aws_login function
                def credentials = awsUtils.aws_login(accessKeyId, secretAccessKey, region)
                def accessKeyId = credentials[0]
                def secretAccessKey = credentials[1]
                def region = credentials[2]
                sh '''
                # Ensure ~/.local/bin is in the PATH
                export PATH=$HOME/.local/bin:$PATH

                # Configure AWS CLI with the credentials and region
                aws configure set aws_access_key_id ${accessKeyId}
                aws configure set aws_secret_access_key ${secretAccessKey}
                aws configure set region ${region}

                # Verify the AWS configuration
                aws sts get-caller-identity
                '''
            }
        }
    }
}
