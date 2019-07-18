#!/bin/groovy
pipeline {
    agent any
    tools {
        maven 'Maven 3.6.0'
        jdk 'OpenJDK_1.8.201'
    }
    environment {
        WORK_DIR=pwd()
        LOCAL_REPO="$WORK_DIR/it-local-repo"
        BUILD_NAME="DocTypeSystem"
        GITLAB_PROJECT_ID="525"
        GITLAB_TOKEN="Wsi9zpt5ymswyexns1Cd"
    }
    stages {
         stage ('Build') {
           steps {
               withCredentials([usernamePassword(credentialsId: 'bio-services-arty', passwordVariable: 'password', usernameVariable: 'user')]) {
                   // Download the current client configuration
                   sh "curl -L -u '${user}:${password}' -X GET https://arty.scai.fraunhofer.de/artifactory/libs-snapshot-local/settings.xml > settings.xml"
               }
               // Set some flags
               sh 'export MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"'
               // build
               sh "mvn clean install -am -fae -s settings.xml -B -U -V -Dmaven.test.failure.ignore=true -Dmaven.repo.local=$LOCAL_REPO"
           }
         }
        stage("Deploy") {
          // only for master branch
           when{
               branch 'master'
           }
           steps {
               withCredentials([usernamePassword(credentialsId: 'bio-services-arty', passwordVariable: 'password', usernameVariable: 'user')]) {
                   sh 'export MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"'
                   sh "mvn deploy -am -fae -s settings.xml -B -U -V -Dmaven.test.failure.ignore=true -Dmaven.repo.local=$LOCAL_REPO -Dusername=${user} -Dpassword=${password} -Dbuildnumber=${env.BUILD_NUMBER} -Dbuildname=${BUILD_NAME} -Dbuildurl=${env.BUILD_URL}"
               }
           }

        }
    }
    post {
         // Always runs. And it runs before any of the other post conditions.
        always {
            // collection JUnit Results
            junit '**/target/surefire-reports/*.xml'
            // Let's wipe out the workspace before we finish!
            deleteDir()
        }
        failure {
           // notify gitlab about a failure
           sh """curl --request POST --header 'PRIVATE-TOKEN: ${GITLAB_TOKEN}' 'https://gitlab.scai.fraunhofer.de/api/v4/projects/${GITLAB_PROJECT_ID}/issues?title=FAILED:%20Job%20${env.JOB_NAME}%20%23${env.BUILD_NUMBER}%20for%20Model:%20%27${MODEL_NAME}%27&description=See%20${env.BUILD_URL}&labels=critical' -v"""
        }
    }
}
