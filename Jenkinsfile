def gv

pipeline {
    agent any
    tools {
        maven "maven-3.9.9"
    }
    environment {
        NEW_VERSION = "1.3.0"
        TEST_VAR = "test var"
    }
    stages {
        stage("test"){
            steps{
                script{
                    echo "$TEST_VAR"
                }
            }
        }
        stage("Increment Version") {
            steps {
                script {
                    echo "Incrementing app version..."
                    sh 'mvn build-helper:parse-version versions:set \
                    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
                    
                
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "${version}-${BUILD_NUMBER}"
                }
            }
        }
        
        stage("Build App") {
            steps {
                script {
                    echo "Building the application..."
                    sh "mvn clean package"
                }
            }
        }
    
        stage("Build Image") {
            steps {
                script {
                    echo "Building the Docker image..."
                    withCredentials([usernamePassword(credentialsId: "docker-hub-creds", usernameVariable: 'USER', passwordVariable: 'PWD')]) {
                        sh "docker build -t santana20095/demo-app:${IMAGE_NAME} ."
                        sh "echo $PWD | docker login -u $USER --password-stdin"
                        sh "docker push santana20095/demo-app:${IMAGE_NAME}"
                    }
                }
            }
        }
        
        stage("Deploy") {
            steps {
                script {
                    echo "Deploying Docker image...."
                }
            }
        }
        stage("commit version update"){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: "git-creds", usernameVariable: 'USER', passwordVariable: 'PWD') ]){
                        sh "git config --global user.email jenkins@example.com"
                        sh "git config --global user.name jenkins"

                        sh "git status"
                        sh "git branch"
                        sh "git config --list"

                        sh "git remote set-url origin https://'raouf21-dev':${PWD}@github.com/raouf21-dev/java-maven.git"
                        sh 'git add .'
                        sh 'git commit -m "ci: version bump"'
                        sh 'git push origin HEAD:jenkins-jobs'
                    }
                }
            }
        }
    }
}