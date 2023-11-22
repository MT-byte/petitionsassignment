pipeline {
    agent any

    stages {
        stage('GetProject') {
            steps {
                git 'https://github.com/MT-byte/petitionsassignment'
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean:clean"
                //sh "mvn dependency:copy-dependencies"
                sh "mvn compiler:compile"
            }
        }
        stage('Package') {
            steps {
                sh 'mvn war:war'
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true,
                    artifacts: '**/maryteresaspetitions*.war'
            }
        }
        stage('Test') {
            steps {
                sh 'echo Tests would be triggered from here'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -f Dockerfile -t myapp . '
                sh 'docker rm -f "myappcontainer" || true'
                sh 'docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
            }
        }
    }
}