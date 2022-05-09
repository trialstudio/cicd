pipeline {
  agent { label 'built-in' }

  stages {
    stage('create seed job') {
      steps {
        jobDsl failOnSeedCollision: true,
          ignoreMissingFiles: false,
          ignoreExisting: false,
          removedConfigFilesAction: 'DELETE',
          removedJobAction: 'DELETE',
          removedViewAction: 'DELETE',
          sandbox: false,
          unstableOnDeprecation: true,
          scriptText: '''
            @Library('job-dsl') _
            main.initialize()
            '''.stripIndent()
      }
    }
  }
}