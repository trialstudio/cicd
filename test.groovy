pipeline {
  agent any
  stages {
    stage('one') {
      steps {
        sh 'ls -lahR'
        sh 'pwd'
        jobDsl targets: 'seedJob.groovy',
          failOnSeedCollision: true,
          removedConfigFilesAction: 'DELETE',
          removedJobAction: 'DELETE',
          removedViewAction: 'DELETE',
          sandbox: true,
          unstableOnDeprecation: true
      }
    }
  }
}
    
