pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..${app_name}'
                echo "Building..${app_name}"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..${APP_NAME}'
                echo "Testing..${APP_NAME}"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....$APP_NAME'
                echo "Deploying....$APP_NAME"
            }
        }
    }
}
