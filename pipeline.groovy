pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..${app.name}'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
