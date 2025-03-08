def gv

pipeline {
    agent any
    environment {
        NEW_VERSION = "1.3.0"
    }
    stages {
        stage("Increment Version") {
            steps {
                script {
                    echo "Incrementing app version..."
                    sh '''
                    mvn build-helper:parse-version versions:set \
                    -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementalVersion} \
                    versions:commit
                    '''
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
                    withCredentials([usernamePassword(credentialsId: "server-credentials", usernameVariable: 'USER', passwordVariable: 'PWD')]) {
                        sh "docker build -t santana20095/demo-app:${IMAGE_NAME} ."
                        sh 'echo $PWD | docker login -u $USER --password-stdin'
                        sh "docker push santana20095/demo-app:${IMAGE_NAME}"
                    }
                }
            }
        }
        
        stage("Deploy") {
            steps {
                script {
                    echo "Deploying Docker image..."
                }
            }
        }
    }
}