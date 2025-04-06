#!/usr/bin/env groovy

library identifier: 'aws-react-shared-library@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/raouf21-dev/aws-react-shared-library.git',
    credentialsID: 'git-creds'
    ]
)

pipeline {
    agent any
    tools {
        maven 'maven-3.9.9'
    }
    environment {
        IMAGE_NAME = 'santana20095/java-maven:1.0'
    }
    stages {
        stage('build app') {
            steps {
                echo 'building application jar...'
                buildJar()
            }
        }
        stage('build image') {
            steps {
                script {
                    echo 'building the docker image...'
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(env.IMAGE_NAME)
                }
            }
        } 
        stage("deploy") {
            steps {
                script {
                    echo 'deploying docker image to EC2...'
                    def dockerCmd = "docker run -p 3080:3080 -d ${IMAGE_NAME}"
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@13.39.146.56 ${dockerCmd}"
                    }
                }
            }               
        }
    }
}

