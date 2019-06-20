pipeline {
    agent { label 'docker' }
    stages {
        stage('Build') {
            steps {
                sh "docker build --tag ${GIT_COMMIT} ."
            }
        }
        stage('Publish to Docker Hub') {
            when {
                branch 'master'
            }
            steps {
                sh "docker tag ${GIT_COMMIT} rogfk/student-activation:${BUILD_NUMBER}"
                withDockerRegistry([credentialsId: 'rfkikt', url: '']) {
                    sh "docker push rogfk/student-activation:${BUILD_NUMBER}"
                }
            }
        }
    }
}
