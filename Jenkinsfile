pipeline {
   agent any

   tools {
      // Install the Maven version configured as "M3" and add it to the path.
      maven "maven"
      jdk "java"
   }
   stages {
      stage('Testing') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/GlebZhuk/qase.git'

            // Run Maven on a Unix agent.
            //sh "mvn clean test"

            // To run Maven on a Windows agent, use
            bat "mvn -Dbrowser=chrome -Dsurefire.suiteXmlFiles=src/test/resources/testng-smoke.xml clean test"
         }

         post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            success {
               junit '**/target/surefire-reports/TEST-*.xml'
            }
         }
      }
      stage('Reporting') {
         steps {
             script {
                     allure([
                             includeProperties: false,
                             jdk: '',
                             properties: [],
                             reportBuildPolicy: 'ALWAYS',
                             results: [[path: 'target/allure-results']]
                     ])
             }
         }
      }
   }
}
