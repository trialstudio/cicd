pipeline {
  agent { label 'built-in' }

  stages {
    stage('create seed job') {
      steps {
        jobDsl targets: 'seedJob.groovy',
          failOnSeedCollision: true,
          ignoreMissingFiles: false,
          ignoreExisting: false,
          removedConfigFilesAction: 'DELETE',
          removedJobAction: 'DELETE',
          removedViewAction: 'DELETE',
          sandbox: false,
          unstableOnDeprecation: true
      }
    }
  }
}