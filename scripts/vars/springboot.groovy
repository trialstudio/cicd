def addSpringbootPipelines(String app, Closure renderer, HashMap<String, String> defaultBindings) {
    utility.addMultiBranchPipelinePullRequestJobDsl("${app}__pull-request-check", renderer("argoDeployment.groovy", defaultBindings))
    utility.addPipelineJobDsl("${app}__build", renderer("springboot.groovy", defaultBindings + ["deployToDevJob": "${app}-deploy-to-dev"]))
    utility.addPipelineJobDsl("${app}__deploy-to-dev", renderer("argoDeployment.groovy", defaultBindings))
    utility.addPipelineJobDsl("${app}__deploy-to-prod", renderer("argoDeployment.groovy", defaultBindings))
}