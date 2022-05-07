node {
  
  jobDsl targets: 'seedJob.groovy',
    failOnSeedCollision: true,
    removedConfigFilesAction: 'DELETE',
    removedJobAction: 'DELETE',
    removedViewAction: 'DELETE',
    sandbox: true,
    unstableOnDeprecation: true
  
}
