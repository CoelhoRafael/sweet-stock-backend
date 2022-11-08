pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Dmaven.test.failure.ignore=true'
            }
            post {
                success {
                    junit 'target/surefire-reports/*.xml'
                    sh 'kill -9 `lsof -t -i:8080` || echo "nada na porta 8080"'
                }
            }
        }
        stage('Deliver') {
            steps {
               sh 'java -jar target/*.jar --server.port=8080'
            }
        }
        
    }
}
