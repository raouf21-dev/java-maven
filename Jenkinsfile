def gv

pipeline{
    
    agent any
    tools {
        maven "maven-3.9.9"
    }
    stages{
        stage("build jar"){
            steps{
                script {
                    echo "building the application..."
                    sh "mvn package "
                }
            }
        }
        stage("build image"){
            steps{
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialId: "docker-hub-creds", passwordVariable: "PASS", usernameVariable: "USER")]){
                        sh "docker build -t santana20095/demo-app:jma-2.0 ."
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker push santana20095/demo-app:jma-2.0"
                    }
                }
            }
        }

        stage("test"){
            steps{
        }
    
        stage("deploy"){
            
            steps{
                }
            }
        }
    }
}
