pipeline {
    agent any

    tools {
        maven 'Maven' // will configure this name in Jenkins later
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/aarvmankodi/devops.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying...'
                sh 'ls -l target'
            }
        }
    }

    post {
        always {
            echo 'Build finished.'
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
