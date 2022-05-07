FROM jenkins/jenkins:2.332.3-lts-jdk11

COPY plugins.yml /

RUN jenkins-plugin-cli -f /plugins.yml

ARG GITHUB_COMMIT

LABEL GITHUB_COMMIT=$GITHUB_COMMIT

ENV CASC_JENKINS_CONFIG "/casc.yml"
ENV JAVA_OPTS "-Djava.awt.headless=true -server -XX:+AlwaysPreTouch -Djenkins.install.runSetupWizard=false"

CMD jenkins.sh