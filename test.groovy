node {
  sh '''ls -lahR
  pwd'''
  jobDsl targets: 'seedJob.groovys',
    failOnSeedCollision: true,
    removedConfigFilesAction: 'DELETE',
    removedJobAction: 'DELETE',
    removedViewAction: 'DELETE',
    sandbox: false,
    unstableOnDeprecation: true
  
}
