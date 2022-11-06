pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'kill -9 $(lsof -ti:8080) || echo "nada na porta 8080"'
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
                }
            }
        }
        stage('Deliver') {
            steps {
                timeout(1) {
                     
                        sh 'java -jar target/sweet-stock-api-0.0.1-SNAPSHOT.jar &'
                    
                }
            }
        }
        
    }
}
