def gv

pipeline{
    
    agent any
    environment {
        NEW_VERSION = "1.3.0"
    }
    stages{

        stage("increment version"){
            script{
                echo "incrementing app version..."
                sh 'mvn build-hepler:parse-version versions:set \
                -DnewVersion=\\\${parsedVersion.majorVersions}.\\\${parsedVersion.minorVersions}.\\\${parsed.version.nextIncrementalVersions} \
                 versions:commit'
                def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                def version = matcher[0][1]
                env.IMAGE_NAME = "$version-$BUILD_NUMBER"
            }
        }
        
        stage("build App"){
            steps{
                echo "building the application..."
                echo "mvn clean package"
            }
        }
    
        stage("build image"){
            steps{
                echo "building the docker image"
                withCredentials([
                    usernamePassword(credentials: "server-credentials", usernameVariable: USER, passwordVariable: PWD)
                    sh "docker build -t santana20095/demo-app:${IMAGE_NAME} ."
                    sh 'echo $PASS | docker login -u $USER --password-stdin'
                    sh "docker push santana20095/demo-app:${IMAGE_NAME}"
                ]) {
                    sh "Some script ${USER} ${PWD}"
                }
            }
        }
        stage("deploy") {
            steps{
                script {
                    echo "deploying docker image"
                }
            }
        }
    }
}