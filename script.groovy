def buildJar(){
    echo "building the application..."
    sh "mvn package "
}

def buildImage(){
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: "docker-hub-creds", passwordVariable: "PASS", usernameVariable: "USER")]){
        sh "docker build -t santana20095/demo-app:jma-2.0 ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push santana20095/demo-app:jma-2.0"
    }
}

def deployJar(){
    echo "deploying the application..."
    // echo "deploying version ${params.VERSION}"
}

return this