pipeline {
  agent any
  stages {
    stage('one') {
      steps {
        sh 'ls -lahR'
        sh 'pwd'
      }
    }
  }
}
    
