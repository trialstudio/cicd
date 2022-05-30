def addSpringbootPipelines(String app, Closure renderer, HashMap<String, String> defaultBindings) {
    utility.addMultiBranchPipelinePullRequestJobDsl("${app}::pull-request-check", renderer("argoDeployment.groovy", defaultBindings))
    utility.addPipelineJobDsl("${app}::build", renderer("springboot.groovy", defaultBindings + ["deployToDevJob": "${app}-deploy-to-dev"]))
    utility.addPipelineJobDsl("${app}::deploy-to-dev", renderer("argoDeployment.groovy", defaultBindings))
    utility.addPipelineJobDsl("${app}::deploy-to-prod", renderer("argoDeployment.groovy", defaultBindings))
}