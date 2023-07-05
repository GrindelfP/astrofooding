   pipeline {
       agent any

       tools {
           maven 'Maven 3'
           jdk 'Azul Zulu 17'
       }

       stages {
           stage('Build') {
               steps {
                   sh "mvn -B -DskipTests clean package"
               }
           }
           stage('Test'){
               steps{
                   sh "mvn test"
               }
           }
           stage('Deploy') {
               steps {
                   echo "Deploying..."
                   sh "mvn spring-boot:run"
               }
           }
       }
   }
