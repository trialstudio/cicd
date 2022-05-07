node {
  
  jobDsl targets: 'seedJob.groovys',
    failOnSeedCollision: true,
    removedConfigFilesAction: 'DELETE',
    removedJobAction: 'DELETE',
    removedViewAction: 'DELETE',
    sandbox: false,
    unstableOnDeprecation: true
  
}
